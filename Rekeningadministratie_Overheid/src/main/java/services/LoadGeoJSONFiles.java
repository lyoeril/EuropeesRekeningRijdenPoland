/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;

/**
 *
 * @author Laurent
 */
@Singleton
@Startup
public class LoadGeoJSONFiles {

    private GeoJsonReader json;
    private Map<String, Geometry> regions;
    private List<String> jsonfiles;

    public LoadGeoJSONFiles() {
    }

    private Geometry loadFile(String path) {
//        System.out.printf("Loading %s. . .\n", path);
        try {
            System.out.println("path: " + path);
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            System.out.println("is: " + is);
            InputStreamReader in = new InputStreamReader(is);
            Geometry ret = json.read(in);
            in.close();
            return ret;
        } catch (org.locationtech.jts.io.ParseException ex) {
            //Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Please verify the integrity of %s.\n", path);
            System.exit(1);
            return null;
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
            return null;
        }
    }

    public void loadAllFiles() {
        try {
            for (String regionString : jsonfiles) {
                System.out.println("Region inside Singleton: " + regionString);
                regions.put(regionString, loadFile(regionString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Geometry> getRegions() {
        return regions;
    }

    public void setRegions(Map<String, Geometry> regions) {
        this.regions = regions;
    }

    @PostConstruct
    private void startup() {
        json = new GeoJsonReader();
        regions = new HashMap<String, Geometry>();

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

        loadAllFiles();
    }

}
