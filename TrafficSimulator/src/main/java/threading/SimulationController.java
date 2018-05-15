package threading;

import eu.poland.domain.Ride;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public class SimulationController {

    private ExecutorService pool;
    private Map<String, Thread> simulations;
    
    public Set<String> getTrackerIds() {
        return simulations.keySet();
    }

    public SimulationController() {
        pool = Executors.newCachedThreadPool();
        simulations = new HashMap();
    }

    public void stop(int timeout) {
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
        Ride ride = new Ride(trackerId, null);
        Thread t = new Thread(new SimRunnable(ride));
        simulations.put(trackerId, t);
        pool.submit(t);
    }
}
