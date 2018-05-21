package eu.poland.threading;

import com.google.maps.model.DirectionsRoute;
import eu.poland.domain.Ride;
import eu.poland.jms.Producer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
    private Map<String, Thread> simulations;
    
    public Set<String> getTrackerIds() {
        return simulations.keySet();
    }
    
    public int getSimCount() {
        AtomicInteger count = new AtomicInteger(0);
        simulations.keySet().iterator().forEachRemaining(s -> {
            if (simulations.get(s).isAlive()) {
                count.incrementAndGet();
            }
        });
        return count.intValue();
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
        String trackerId = String.format("%010x", r.nextInt());
        DirectionsRoute route = routeService.getRandomRoute();
        Ride ride = new Ride(trackerId, route);
        Thread t = new Thread(new SimRunnable(msgQueueProducer, ride));
        simulations.put(trackerId, t);
        pool.submit(t);
    }
}
