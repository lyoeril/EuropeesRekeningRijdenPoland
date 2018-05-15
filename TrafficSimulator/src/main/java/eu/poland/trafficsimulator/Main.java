package eu.poland.trafficsimulator;

import threading.SimulationController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import eu.poland.domain.LocationTimed;
import eu.poland.domain.Ride;
import eu.poland.jms.Producer;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class Main {

    private static Properties props;
    private static String activeMQIp = "192.168.25.14";
    
    private static SimulationController simulator;

    private static void initProps() {
        try {
            props = new Properties();
            InputStream in = ClassLoader.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (IOException ioe) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Please provide a properties file.");
            System.exit(1);
        }
        if (!props.containsKey("key")) {
            System.out.println("Please ensure that the properties file contains an API key.");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws JMSException {
        System.out.println("Loading properties file. . .");
        initProps();
        
        System.out.println("Connecting to ActiveMQ server. . .");
        Producer msgQueueSender = new Producer("tcp://" + activeMQIp + ":61616", "admin", "secret");
        msgQueueSender.setup("TrafficQueue");

        System.out.println("\nSimulator is starting. . .\n");
        simulator = new SimulationController();

        /*System.out.println(getJsonRoute(new LatLng(52.0828121, 17.0008908), new LatLng(51.8774911, 17.0028028)));
        DirectionsRoute route = getRoute(new LatLng(52.0828121, 17.0008908), new LatLng(51.8774911, 17.0028028)).routes[0];
        Ride ride = new Ride(route);
        System.out.println(ride.getCurrentLocation());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        System.out.println(ride.progress());
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        try {
            for (double i = 0; i < 3; i++) {
                double offset = i / 10;
                String output = getJsonRoute(new LatLng(52.0828121 + offset, 17.0008908 + offset), new LatLng(51.8774911 + offset, 17.0028028 + offset));

                msgQueueSender.sendMessage(output);
            }
        } catch (JMSException jex) {
            jex.printStackTrace();
        } finally {
            msgQueueSender.close();
        }*/

        System.out.println("\nSimulator is stopping. . .");
    }

    private static DirectionsResult getRoute(LatLng origin, LatLng destination) {
        // API key RDK: ERP2018
        GeoApiContext geoContext = new GeoApiContext.Builder()
                .apiKey(props.getProperty("key"))
                .build();
        try {
            DirectionsResult result = DirectionsApi.newRequest(geoContext)
                    .origin(origin)
                    .destination(destination)
                    .await();
            return result;
        } catch (ApiException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static String getJsonRoute(LatLng origin, LatLng destination) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        DirectionsResult result = getRoute(origin, destination);
        String json = gson.toJson(result);
        return json;
    }
}
