package eu.poland.trafficsimulator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import eu.poland.domain.LocationTimed;
import eu.poland.jms.Producer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class Main {

    private static String activeMQIp = "192.168.25.14";

    public static void main(String[] args) throws JMSException {
        Producer msgQueueSender = new Producer("tcp://" + activeMQIp + ":61616", "admin", "secret");
        msgQueueSender.setup("TrafficQueue");

        System.out.println("\nSimulation is starting. . .\n");

        try {
            for (double i = 0; i < 3; i++) {
                double offset = i/10;
                String output = getJsonRoute(new LatLng(52.0828121 + offset, 17.0008908 + offset), new LatLng(51.8774911 + offset, 17.0028028 + offset));

                msgQueueSender.sendMessage(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            msgQueueSender.close();
        }

        System.out.println(
                "\nSimulation is stopping. . .");
    }

    private static String getJsonRoute(LatLng origin, LatLng destination) {
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
            String json = gson.toJson(result);
            return json;

        } catch (ApiException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "";

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "";

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
