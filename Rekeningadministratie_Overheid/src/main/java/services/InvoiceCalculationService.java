/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.maps.model.LatLng;
import domain.Invoice;
import domain.KMRate;
import domain.Location;
import domain.Rekeningrijder;
import domain.Ride;
import enums.VehicleType;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.locationtech.jts.algorithm.distance.DistanceToPoint;
import org.locationtech.jts.algorithm.distance.PointPairDistance;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.ParseException;

/**
 *
 * @author Laurent
 */
@Stateless
public class InvoiceCalculationService {

    private GeoJsonReader json;
    private PolygonService polyService;
    private List<String> jsonfiles;
    private List<KMRate> kmrates;

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private LoadGeoJSONFiles loadGeoJSONFiles;

    public InvoiceCalculationService() {
        this.json = new GeoJsonReader();

        this.jsonfiles = new ArrayList<>();
        jsonfiles.add("/Regions/dolnoslaskie.json");
        jsonfiles.add("/Regions/kujawsko-pomorskie.json");
        jsonfiles.add("/Regions/lubelskie.json");
        jsonfiles.add("/Regions/lubuskie.json");
        jsonfiles.add("/Regions/malopolskie.json");
        jsonfiles.add("/Regions/mazowieckie.json");
        jsonfiles.add("/Regions/opolskie.json");
        jsonfiles.add("/Regions/podkarpackie.json");
        jsonfiles.add("/Regions/podlaskie.json");
        jsonfiles.add("/Regions/pomorskie.json");
        jsonfiles.add("/Regions/warminsko-mazurskie.json");
        jsonfiles.add("/Regions/wielkopolskie.json");
        jsonfiles.add("/Regions/zachodniopomorskie.json");
        jsonfiles.add("/Regions/lodzkie.json");
        jsonfiles.add("/Regions/slaskie.json");
        jsonfiles.add("/Regions/swietokrzyskie.json");

    }

    public Invoice calculateInvoice(
            long cartrackerId,
            int month,
            int year,
            Rekeningrijder rekeningrijder,
            List<Ride> rides,
            VehicleType vehicleType) {

        //Prep Loading the rates        
        this.loadRates();

        //Last location for calculating distance
        Location lastLocation = null;

        double totalPrice = 0.0;

        /**
         * Looping through every ride in Month
         */
        Map<String, Geometry> regionsGeometry = new HashMap<>();
        regionsGeometry = loadGeoJSONFiles.getRegions();

        System.out.println("RegionsGEOMETRY TESTING: " + regionsGeometry);
        for (Map.Entry<String, Geometry> entry : regionsGeometry.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        for (Ride ride : rides) {
            List<Location> locations = ride.getLocations();
            try {
                for (Location l : locations) {
                    if (lastLocation == null) {
                        lastLocation = l;
                    }

                    double latitude = l.getLatitude();
                    double longitude = l.getLongitude();

                    String pointRegion = "";
                    for (Map.Entry<String, Geometry> entry : regionsGeometry.entrySet()) {
                        this.polyService = new PolygonService(entry.getValue());
                        boolean isInside = this.polyService.isInside(longitude, latitude);
                        if (isInside) {
                            pointRegion = entry.getKey();
                        }
                    }

                    //Calculating distance
                    PointPairDistance pp = new PointPairDistance();
                    pp.initialize(new Coordinate(lastLocation.getLongitude(), lastLocation.getLatitude()),
                            new Coordinate(longitude, latitude));

                    double distance = pp.getDistance() * 100;

                    double rate = 0.0;

                    //Substring to get Region
                    int minusDotJson = pointRegion.length() - 5;
                    String stringPointRegion = pointRegion.substring(9, minusDotJson);
                    System.out.println("Region: " + stringPointRegion);

                    //Calculating price per distance
                    if (pointRegion != "") {
                        if (invoiceService == null) {
                            KMRate kmr = invoiceService.findKMRateByRegion(stringPointRegion);
                            rate = kmr.getRateFromVehicleType(vehicleType);
                        } else {
                            for (KMRate tmpKMRate : kmrates) {
                                if (tmpKMRate.getRegion().equals(stringPointRegion)) {
                                    rate = tmpKMRate.getRateFromVehicleType(vehicleType);
                                    System.out.println("rate: " + rate);
                                }
                            }
                        }
                    }

                    double price = rate * distance;
                    System.out.println(
                            "Price: \t\t" + rate + " *"
                            + "\nDistance: \t" + distance
                            + "\nResult: \t" + price);

                    totalPrice += price;

                    System.out.println("*******************************************************");

                    //Resetting lastLocation for next ride
                    lastLocation = l;
                }
                System.out.println("Total Price: " + totalPrice);

                Invoice invoice = new Invoice(cartrackerId, totalPrice, year, month, rekeningrijder);
                System.out.println("New Invoice adding...");

                Invoice check = invoiceService.findInvoiceByCartrackerYearMonth(cartrackerId, year, month);
                if (check != null) {
                    invoiceService.updateInvoice(invoice);
                    rekeningrijder.getInvoices().remove(check);
                    rekeningrijder.getInvoices().add(invoice);
                    registrationService.updateRekeningrijder(rekeningrijder);
                    return invoice;
                }

                invoiceService.addInvoice(invoice);
                rekeningrijder.getInvoices().add(invoice);
                registrationService.updateRekeningrijder(rekeningrijder);
                return invoice;

            } catch (Exception e) {
                e.printStackTrace();
            }
            lastLocation = null;
        }

        return null;
    }

    private Geometry loadGeoJsonFile(String path) {
//        System.out.printf("Loading %s. . .\n", path);
        try {
            System.out.println("path: " + path);
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            System.out.println("is: " + is);
            InputStreamReader in = new InputStreamReader(ClassLoader.class.getResourceAsStream(path));
            Geometry ret = json.read(in);
            in.close();
            return ret;
        } catch (ParseException ex) {
            //Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Please verify the integrity of %s.\n", path);
            System.exit(1);
            return null;
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
            return null;
        }
    }

    private void loadRates() {
        KMRate k1 = new KMRate("dolnoslaskie");
        KMRate k2 = new KMRate("kujawsko-pomorskie");
        KMRate k3 = new KMRate("lubelskie");
        KMRate k4 = new KMRate("lubuskie");
        KMRate k5 = new KMRate("malopolskie");
        KMRate k6 = new KMRate("mazowieckie");
        KMRate k7 = new KMRate("opolskie");
        KMRate k8 = new KMRate("podkarpackie");
        KMRate k9 = new KMRate("podlaskie");
        KMRate k10 = new KMRate("pomorskie");
        KMRate k11 = new KMRate("warminsko-mazurskie");
        KMRate k12 = new KMRate("wielkopolskie");
        KMRate k13 = new KMRate("zachodniopomorskie");
        KMRate k14 = new KMRate("lodzkie");
        KMRate k15 = new KMRate("slaskie");
        KMRate k16 = new KMRate("swietokrzyskie");

        List<KMRate> list = new ArrayList<KMRate>();
        list.add(k1);
        list.add(k2);
        list.add(k3);
        list.add(k4);
        list.add(k5);
        list.add(k6);
        list.add(k7);
        list.add(k8);
        list.add(k9);
        list.add(k10);
        list.add(k11);
        list.add(k12);
        list.add(k13);
        list.add(k14);
        list.add(k15);
        list.add(k16);

        kmrates = new ArrayList<>();
        for (KMRate rate : list) {
            rate.addRatePerVehicleType(VehicleType.AUTOBUS, 0.1);
            rate.addRatePerVehicleType(VehicleType.PASSENGER_CAR, 0.01);
            rate.addRatePerVehicleType(VehicleType.TRUCK, 0.1);
            rate.addRatePerVehicleType(VehicleType.UNKNOWN, 0.1);
            rate.addRatePerVehicleType(VehicleType.VAN, 0.05);
            kmrates.add(rate);
        }
    }

}
