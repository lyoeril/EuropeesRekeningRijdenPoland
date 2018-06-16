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
import domain.Rekeningrijder;
import domain.Ride;
import domain.Vehicle;
import dto.DTO_Ride;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.json.simple.JSONObject;

/**
 *
 * @author Laurent
 */

@Stateless
public class RideService {
    
    final static String BASE_URL = "http://192.168.25.14:8080/RegistratieCentrale/webapi/cartrackerdata/get/RideByCode/";

    
    @Inject
    private RegistrationService registrationService;    
    

    public RideService() {
    }
    
    public void getRides(long vehicleId, int month, int year){
        
        System.out.println("Registrationservice :" + registrationService.toString());
        //Vehicle v = registrationService.findVehicleById(vehicleId);
       // System.out.println("Vehicle: " + v);
        //long cartrackerId = v.getCartracker().getId();
        //Cartracker c = registrationService.findCartrackerById(cartrackerId);
        long cartrackerId = 1L;

        List<DTO_Ride> rides = new ArrayList<DTO_Ride>();
        
//            String startDate = "01-" + month + "-" + year;
//            String endDate = "01-" + (month + 1) + "-" + year;
            
            String startDate = "16-" + "05" + "-" + year;
            String endDate = "15-" + "06" + "-" + year;
            String cID = "00f524061d";

        try {
            URL url = new URL(
                    BASE_URL
//                    + cartrackerId 
                    + cID
                    + "/" + startDate
                    + "/" + endDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            RequestConnection(conn, "GET");
            System.out.println("URL: " + url);

            if(conn == null){
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
            ridesArray = mapper.readValue(huh, new TypeReference<DTO_Ride[]>(){});
            
            for(DTO_Ride r: ridesArray){
                System.out.println("ride: " + r.getId());
            }

        } catch (MalformedURLException e) {
            System.out.println("exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("exception" + e.getMessage());
        }

    }
    
    private boolean RequestConnection(
            HttpURLConnection httpURLConnection,
            String method){
        
        if(httpURLConnection != null && !method.isEmpty()){
            try {
                httpURLConnection.setRequestMethod(method);
                httpURLConnection.setRequestProperty("Accept", "application/json");;
                
            } catch (Exception e) {
            }
        }        
        return true;
    } 
}
