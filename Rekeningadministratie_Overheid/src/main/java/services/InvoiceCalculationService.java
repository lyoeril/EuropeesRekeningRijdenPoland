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
import enums.VehicleType;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    @Inject
    private InvoiceService invoiceService;

    public InvoiceCalculationService() {
        this.json = new GeoJsonReader();

        this.jsonfiles = new ArrayList<>();
        jsonfiles.add("/Regions/dolnośląskie.json");
        jsonfiles.add("/Regions/kujawsko-pomorskie.json");
        jsonfiles.add("/Regions/lubelskie.json");
        jsonfiles.add("/Regions/lubuskie.json");
        jsonfiles.add("/Regions/malopolskie.json");
        jsonfiles.add("/Regions/mazowieckie.json");
        jsonfiles.add("/Regions/opolskie.json");
        jsonfiles.add("/Regions/podkarpackie.json");
        jsonfiles.add("/Regions/podlaskie.json");
        jsonfiles.add("/Regions/pomorskie.json");
        jsonfiles.add("/Regions/warmińsko-mazurskie.json");
        jsonfiles.add("/Regions/wielkopolskie.json");
        jsonfiles.add("/Regions/zachodniopomorskie.json");
        jsonfiles.add("/Regions/łódzkie.json");
        jsonfiles.add("/Regions/śląskie.json");
        jsonfiles.add("/Regions/świętokrzyskie.json");

    }

    public Invoice calculateInvoice(
            long cartrackerId,
            int month,
            int year,
            Rekeningrijder rekeningrijder,
            List<Location> locations,
            VehicleType vehicleType) {

        double latitude;
        double longitude;

        double totalPrice = 0.0;
        try {
            for (Location l : locations) {
                latitude = l.getLatitude();
                longitude = l.getLongitude();

                String pointRegion = "";
                for (String region : jsonfiles) {
                    this.polyService = new PolygonService(loadGeoJsonFile(region));
                    boolean isInside = this.polyService.isInside(longitude, latitude);
                    if (isInside) {
                        pointRegion = region;
                        System.out.println("Is inside: " + region);
                    }
                }

                PointPairDistance pp = new PointPairDistance();
                pp.initialize(new Coordinate(longitude, latitude),
                        new Coordinate(longitude, latitude));

                double distance = pp.getDistance() * 100;
                System.out.println("distance is : " + distance);

                double rate = 0.0;
                if (pointRegion != "") {
                    KMRate kmr = invoiceService.findKMRateByRegion(pointRegion);
                    rate = kmr.getRateFromVehicleType(vehicleType);
                }

                double price = rate * distance;
                System.out.println("price: " + rate + "*" + distance + " = " + price);

                totalPrice += price;
            }

            Invoice invoice = new Invoice(cartrackerId, totalPrice, year, month, rekeningrijder);
            invoiceService.addInvoice(invoice);
            return invoice;

//            double firstieTude = 18.598400;
//            double lastiTude = 53.013755;
//
//            double firstOfsec = 18.600551;
//            double lastOfSec = 53.014604;
            //find point inside json files
            //add price to invoicetotal
            //if last location -> return new invoice
//            invoiceService.addInvoice(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Geometry loadGeoJsonFile(String path) {
        System.out.printf("Loading %s. . .\n", path);
        try {
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

}
