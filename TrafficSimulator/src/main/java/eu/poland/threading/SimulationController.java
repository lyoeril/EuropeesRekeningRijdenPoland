package eu.poland.threading;

import eu.poland.service.RouteService;
import com.google.maps.model.DirectionsRoute;
import eu.poland.domain.Ride;
import eu.poland.jms.Producer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class SimulationController {

    private Producer msgQueueProducer;
    private ExecutorService pool;
    private RouteService routeService;
    private Map<String, Future> simulations;

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
        this.msgQueueProducer = producer;
        this.pool = Executors.newCachedThreadPool();
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
        String trackerId = "INVALID";
        while (true) {
            trackerId = "PL-" + String.format("%010x", r.nextInt());
            if (!simulations.containsKey(trackerId)) {
                break;
            } else if (simulations.get(trackerId).isDone()) {
                break;
            }
        }
        DirectionsRoute route = null;
        try {
            route = routeService.getRandomRoute();
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            //Logger.getLogger(SimulationController.class.getName()).log(Level.SEVERE, null, aioobe);
            System.out.println("Unable to start this simulation thread, try again. (cars can't swim)");
            return;
        }
        Ride ride = new Ride(trackerId, route);
        Thread t = new Thread(new SimRunnable(msgQueueProducer, ride));
        simulations.put(trackerId, pool.submit(t));
    }
}
