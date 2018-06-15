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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Laurent
 */
public class RideService {
    
    final static String BASE_URL = "192.168.25.?";

    
    @Inject
    private RegistrationService registrationService;    
    

    public RideService() {
    }
    
    public void getRides(long vehicleId, int month, int year){
        
        Vehicle v = registrationService.findVehicleById(vehicleId);
        long cartrackerId = v.getCartracker().getId();
        //Cartracker c = registrationService.findCartrackerById(cartrackerId);
        

        List<Ride> rides = new ArrayList<Ride>();

        try {
            URL url = new URL(
                    BASE_URL
                    + cartrackerId
                    + year
                    + month);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            RequestConnection(conn, "GET");

            if(conn == null){
                throw new RuntimeException("Failed : No Connection");
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : Http error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            System.out.println("Output from RegistrationServer .... \n");
            while ((output = br.readLine()) != null) {
                output += output;
                System.out.println(output);
            }
            conn.disconnect();
            
            ObjectMapper mapper = new ObjectMapper();
            rides = mapper.readValue(output, new TypeReference<List<Ride>>(){});
            
            for(Ride r: rides){
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
