package eu.poland.domain;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Robin
 */
public class Ride {
    private Long id;
    /** Time traveled, in seconds, with each update */
    private double defaultInterval = 5L;
    private DirectionsRoute plannedRoute;
    private DirectionsStep currentStep;
    private Queue<LocationTimed> travelledRoute;
    
    public Ride(DirectionsRoute plannedRoute) {
        // TODO robkor: ID?
        this.plannedRoute = plannedRoute;
        this.currentStep = this.plannedRoute.legs[0].steps[0];
        this.travelledRoute = new PriorityQueue();
        this.travelledRoute.add(new LocationTimed(currentStep.startLocation));
    }
    
    /**
     * Calculates the next step in the plannedRoute. 
     * 
     * @return The next step in this route. 
     * @throws IndexOutOfBoundsException When there are no more steps. 
     */
    private DirectionsStep calcNextStep() throws IndexOutOfBoundsException {
        DirectionsLeg currentLeg = plannedRoute.legs[0];
        for (int i = 0; i < currentLeg.steps.length; i++) {
            if (currentLeg.steps[i].startLocation == currentStep.endLocation) {
                return currentLeg.steps[i];
            }
        }
        throw new IndexOutOfBoundsException("The current route has been finished.");
    }
    
    public LocationTimed progress() {
        return null;
    }
    
    private double calcPythagoras(LatLng start, LatLng end) {
        // a
        double lat = end.lat - start.lat;
        // b
        double lng = end.lng - start.lng;
        // c
        double len = Math.sqrt((Math.pow(lat, 2) + Math.pow(lng, 2)));
        return len;
    }
    
    /**
     * Calculate the distance traveled of the currentStep in percentages. 
     * 
     * @return The distance traveled in percentages. Example: 0.83
     */
    private double calcStepProgression() {
        LatLng currentLoc = travelledRoute.peek().getLocation();
        double plannedLen = calcPythagoras(currentStep.startLocation, currentStep.endLocation);
        double travelledLen = calcPythagoras(currentStep.startLocation, currentLoc);
        double progression = travelledLen / plannedLen;
        return progression;
    }
    
    private LocationTimed calcNextLoc(double timeTravelled) throws IndexOutOfBoundsException {
        Long stepDuration = currentStep.duration.inSeconds;
        double timeTraveled = calcStepProgression() * stepDuration + timeTravelled;
        
        if (stepDuration < timeTraveled) {
            travelledRoute.add(new LocationTimed(currentStep.endLocation));
            
            // Throws IndexOutOfBoundsException
            currentStep = calcNextStep();
            
            calcNextLoc(timeTraveled - stepDuration);
            return travelledRoute.peek();
        }
        if (stepDuration == timeTraveled) {
            return travelledRoute.peek();
        }
        
        
        
        return null;
    }
}
