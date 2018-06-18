package eu.poland.threading;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.model.DirectionsRoute;
import eu.poland.domain.LocationSerializable;
import eu.poland.domain.Ride;
import eu.poland.domain.RideFinishedException;
import eu.poland.jms.Producer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class SimRunnable implements Runnable {
    
    private Producer producer;
    private Ride ride;
    
    public SimRunnable(Producer producer, Ride ride) {
        this.producer = producer;
        this.ride = ride;
    }

    @Override
    public void run() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LocationSerializable loc = new LocationSerializable(ride.getCurrentLocation(), ride.getId());
        try {
            producer.sendMessage(gson.toJson(loc));
        } catch (JMSException ex) {
            Logger.getLogger(SimRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            try {
                Thread.sleep(10000);
                loc = new LocationSerializable(ride.progress(), ride.getId());
                producer.sendMessage(gson.toJson(loc));
            } catch (InterruptedException ie) {
                //Logger.getLogger(SimRunnable.class.getName()).log(Level.SEVERE, null, ie);
                break;
            } catch (RideFinishedException ioobe) {
                //System.out.printf("Simulation with ID %s has finished.\n", ride.getId());
                break;
            } catch (JMSException jmse) {
                Logger.getLogger(SimRunnable.class.getName()).log(Level.SEVERE, null, jmse);
            }
        }
    }
}
