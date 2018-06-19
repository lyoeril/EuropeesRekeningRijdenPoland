/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import domain.Cartracker;
import domain.Location;
import domain.Rekeningrijder;
import domain.Ride;
import domain.Vehicle;
import dto.DTO_Location;
import dto.DTO_Ride;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.json.simple.JSONObject;

/**
 *
 * @author Laurent
 */
@Stateless
public class RideService {

    final static String BASE_URL = "http://192.168.25.35:8080/RegistratieCentrale/webapi/cartrackerdata/get/RideByCode/";

    @Inject
    private RegistrationService registrationService;

    public RideService() {
    }

    public List<Ride> getRides(long cartrackerId, int month, int year) {

        System.out.println("Registrationservice :" + registrationService.toString());
        //Vehicle v = registrationService.findVehicleById(vehicleId);
        // System.out.println("Vehicle: " + v);
        //long cartrackerId = v.getCartracker().getId();
        //Cartracker c = registrationService.findCartrackerById(cartrackerId);

        List<DTO_Ride> rides;
        List<Ride> realRides = new ArrayList<>();
        Cartracker cartracker = registrationService.findCartrackerById(cartrackerId);
        System.out.println("getRides: id + " + cartracker.getHardware());

        int endMonth = month + 1;
        if (month >= 13) {
            month = 1;
        }
        if (month == 0){
            month = 1;
        }
        String startDate = "01-" + month + "-" + year;
        String endDate = "01-" + endMonth + "-" + year;

//            String startDate = "16-" + "05" + "-" + year;
//            String endDate = "15-" + "06" + "-" + year;
//        String UUID = cartracker.getHardware();
        String cID = "PL-00ac0adf40";
//        if(cartracker != null){
//            cID = cartracker.getHardware();
//        }

        try {
            URL url = new URL(
                    BASE_URL
                    + cID
                    + "/" + startDate
                    + "/" + endDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            RequestConnection(conn, "GET");
            System.out.println("URL: " + url);

            if (conn == null) {
                throw new RuntimeException("Failed : No Connection");
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : Http error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            String huh = "";
            System.out.println("Output from RegistrationServer .... \n");
            while ((output = br.readLine()) != null) {
                huh += output;
                System.out.println("Output: " + output);
            }
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            //Object[] test = mapper.readValue(huh, new TypeReference<Object[]>(){});

            DTO_Ride[] ridesArray;
            ridesArray = mapper.readValue(huh, new TypeReference<DTO_Ride[]>() {
            });

            for (DTO_Ride r : ridesArray) {
                System.out.println("---------------------------------------------------");
                System.out.println("Dit is een Ride: " + r.getId());
                System.out.println("Met een aantal locaties: " + r.getLocations().size());
                System.out.println("---------------------------------------------------");
            }

            rides = (Arrays.asList(ridesArray));

            List<Location> locations;
            for (DTO_Ride dtoRides : rides) {
                locations = new ArrayList<>();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                Date startdate = sdf.parse(dtoRides.getStartDate());
                Date enddate = sdf.parse(dtoRides.getEndDate());

                Calendar startCal = Calendar.getInstance();
                startCal.setTime(startdate);
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(enddate);

                Ride r = new Ride(dtoRides.getId(), startCal, endCal);

                for (DTO_Location dtoLoc : dtoRides.getLocations()) {
                    Date date = sdf.parse(dtoLoc.getDate());
                    Calendar cDate = Calendar.getInstance();
                    cDate.setTime(date);
                    Location l = new Location(dtoLoc.getId(), cDate, dtoLoc.getLatitude(), dtoLoc.getLongitude());
                    locations.add(l);
                }
                r.setLocations(locations);
                realRides.add(r);
                System.out.println("r _++_+_+_+_+_+_+__");
                System.out.println("r.locs : " + r.getLocations().size());
            }

        } catch (MalformedURLException e) {
            System.out.println("exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("exception" + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(RideService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return realRides;
    }

    private boolean RequestConnection(
            HttpURLConnection httpURLConnection,
            String method) {

        if (httpURLConnection != null && !method.isEmpty()) {
            try {
                httpURLConnection.setRequestMethod(method);
                httpURLConnection.setRequestProperty("Accept", "application/json");;

            } catch (Exception e) {
            }
        }
        return true;
    }
}
