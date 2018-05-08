package eu.poland.trafficsimulator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import eu.poland.domain.LocationTimed;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\nSimulation is starting. . .\n");
        System.out.println(new LocationTimed(new LatLng(1.1, 2.2)));
        //printJsonRoute(new LatLng(52.663269, 20.659414), new LatLng(52.663269, 20.659414));
        System.out.println("\nSimulation is stopping. . .");
    }
    
    private static void printJsonRoute(LatLng origin, LatLng destination) {
        // API key RDK: ERP2018
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDd0sU9XIu6Mb8WtodJ92qSa_dG1W7yKcs")
                .build();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            DirectionsResult result = DirectionsApi.newRequest(context)
                    .origin(origin)
                    .destination(destination)
                    .await();
            System.out.println(gson.toJson(result));
        } catch (ApiException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
