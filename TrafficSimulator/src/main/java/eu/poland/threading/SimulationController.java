package eu.poland.threading;

import eu.poland.service.RouteService;
import com.google.maps.model.DirectionsRoute;
import eu.poland.domain.Ride;
import eu.poland.jms.Producer;
import eu.poland.service.PolygonService;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;

/**
 *
 * @author Robin
 */
public class SimulationController {

    private Producer msgQueueProducer;
    private ExecutorService pool;
    private PolygonService polyPoland;
    private RouteService routeService;
    private Map<String, Future> simulations;
    private GeoJsonReader json;

    public Set<String> getTrackerIds() {
        return simulations.keySet();
    }

    public int getSimCount() {
        int count = 0;
        for (String s : simulations.keySet()) {
            if (!simulations.get(s).isDone()) {
                count++;
            }
        }
        return count;
    }
    
    public Set<String> getSimTrackerIds() {
        Set<String> ret = new HashSet();
        for (String s : simulations.keySet()) {
            if (!simulations.get(s).isDone()) {
                ret.add(s);
            }
        }
        return ret;
    }

    public SimulationController(Producer producer) {
        this.json = new GeoJsonReader();
        this.msgQueueProducer = producer;
        this.pool = Executors.newCachedThreadPool();
        this.polyPoland = new PolygonService(loadGeoJsonFile("/poland.json"));
        System.out.println(polyPoland.getRandomPoint());
        System.out.println(polyPoland.getRandomPoint());
        System.out.println(polyPoland.getRandomPoint());
        this.routeService = new RouteService();
        this.simulations = new HashMap();
    }

    public void stop(int timeout) {
        try {
            msgQueueProducer.close();
        } catch (JMSException ex) {
            Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pool.shutdownNow();
        try {
            pool.awaitTermination(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startNewSim() {
        Random r = new Random();
        String trackerId = String.format("%010x", r.nextInt());
        DirectionsRoute route = routeService.getRandomRoute();
        Ride ride = new Ride(trackerId, route);
        Thread t = new Thread(new SimRunnable(msgQueueProducer, ride));
        simulations.put(trackerId, pool.submit(t));
    }

    private Geometry loadGeoJsonFile(String path) {
        System.out.printf("Loading %s. . .\n", path);
        try {
            InputStreamReader in = new InputStreamReader(ClassLoader.class.getResourceAsStream(path));
            return json.read(in);
        } catch (ParseException ex) {
            //Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Please verify the integrity of %s.\n", path);
            System.exit(1);
            return null;
        }
    }
}
