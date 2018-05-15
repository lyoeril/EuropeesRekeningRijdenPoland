package threading;

import com.google.maps.model.DirectionsRoute;
import eu.poland.domain.Ride;

/**
 *
 * @author Robin
 */
public class SimRunnable implements Runnable {
    
    private Ride ride;
    
    public SimRunnable(Ride ride) {
        this.ride = ride;
    }

    @Override
    public void run() {
        
    }
}
