///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package services;
//
//import com.google.maps.GeoApiContext;
//import com.google.maps.model.Geometry;
//import dao.interfaces.IInvoiceDAO;
//import dao.interfaces.IKMRateDAO;
//import domain.Location;
//import domain.Rekeningrijder;
//import domain.Ride;
//import enums.VehicleType;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javafx.scene.layout.Region;
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.locationtech.jts.io.geojson.GeoJsonReader;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
///**
// *
// * @author Laurent
// */
//@RunWith(MockitoJUnitRunner.class)
//public class TestInvoiceCalculationService {
//
//    @InjectMocks
//    private InvoiceCalculationService ics;
//
//    @Mock
//    InvoiceService invoiceService;
//
//    LoadGeoJSONFiles loadGeoJSONFiles;
//
//    Map<String, org.locationtech.jts.geom.Geometry> regions;
//
//    private GeoJsonReader json;
//    private PolygonService polyService;
//    private List<String> jsonfiles;
//
//    @Before
//    public void setUp() {
//        this.startup();
//        
//        
//        
//        loadGeoJSONFiles = new LoadGeoJSONFiles();
//        loadGeoJSONFiles.setRegions(regions);
//        regions = loadGeoJSONFiles.getRegions();
//        System.out.println("regions: " + regions);
//    }
//
//    @Test
//    public void test() {
//        Calendar c1 = new GregorianCalendar(2018, 01, 01);
//        Location l1 = new Location(1L, c1, 52.40533, 19.27417);
//        Location l2 = new Location(2L, c1, 52.23478, 19.17059);
//
//        List<Ride> rides = new ArrayList<Ride>();
//        Ride r = new Ride(1L, c1, c1);
//        List<Location> locations = new ArrayList<>();
//        locations.add(l1);
//        locations.add(l2);
//        r.setLocations(locations);
//        rides.add(r);
//
//        ics.calculateInvoice(3L, 1, 2018, new Rekeningrijder(), rides, VehicleType.VAN);
//    }
//    
//     private org.locationtech.jts.geom.Geometry loadFile(String path) {
////        System.out.printf("Loading %s. . .\n", path);
//        try {
//            System.out.println("path: " + path);
//            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
//            System.out.println("is: " + is);
//            InputStreamReader in = new InputStreamReader(is);
//            org.locationtech.jts.geom.Geometry ret = json.read(in);
//            in.close();
//            return ret;
//        } catch (org.locationtech.jts.io.ParseException ex) {
//            //Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.printf("Please verify the integrity of %s.\n", path);
//            System.exit(1);
//            return null;
//        } catch (IOException ex) {
//            System.out.println("IO Exception: " + ex.getMessage());
//            return null;
//        }
//     }
//
//    private void loadAllFiles() {
//        try {
//            for (String regionString : jsonfiles) {
//                System.out.println("Region inside Singleton: " + regionString);
//                regions.put(regionString, loadFile(regionString));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private void startup() {
//        json = new GeoJsonReader();
//        regions = new HashMap<String, org.locationtech.jts.geom.Geometry>();
//
//        this.jsonfiles = new ArrayList<>();
//        jsonfiles.add("/Regions/dolnoslaskie.json");
//        jsonfiles.add("/Regions/kujawsko-pomorskie.json");
//        jsonfiles.add("/Regions/lubelskie.json");
//        jsonfiles.add("/Regions/lubuskie.json");
//        jsonfiles.add("/Regions/malopolskie.json");
//        jsonfiles.add("/Regions/mazowieckie.json");
//        jsonfiles.add("/Regions/opolskie.json");
//        jsonfiles.add("/Regions/podkarpackie.json");
//        jsonfiles.add("/Regions/podlaskie.json");
//        jsonfiles.add("/Regions/pomorskie.json");
//        jsonfiles.add("/Regions/warminsko-mazurskie.json");
//        jsonfiles.add("/Regions/wielkopolskie.json");
//        jsonfiles.add("/Regions/zachodniopomorskie.json");
//        jsonfiles.add("/Regions/lodzkie.json");
//        jsonfiles.add("/Regions/slaskie.json");
//        jsonfiles.add("/Regions/swietokrzyskie.json");
//
//        loadAllFiles();
//    }
//
//}
