package eu.poland.domain;

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
    private Long interval = 5L;
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
    
    private LocationTimed calcNext() {
        if (currentStep.duration.inSeconds < interval) {
            travelledRoute.add(new LocationTimed(currentStep.endLocation));
        }
        return null;
    }
}
