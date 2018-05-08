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
    /**
     * Time traveled, in seconds, with each update
     */
    private double defaultInterval = 5L;
    private DirectionsRoute plannedRoute;
    private DirectionsStep currentStep;
    private Queue<LocationTimed> traveledRoute;

    public Ride(DirectionsRoute plannedRoute) {
        // TODO robkor: ID?
        this.plannedRoute = plannedRoute;
        this.currentStep = this.plannedRoute.legs[0].steps[0];
        this.traveledRoute = new PriorityQueue();
        this.traveledRoute.add(new LocationTimed(currentStep.startLocation));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationTimed progress() {
        LocationTimed nextLoc = calcNextLoc(defaultInterval);
        traveledRoute.add(nextLoc);
        return nextLoc;
    }

    private double calcPythagoras(double a, double b) {
        // c
        return Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
    }

    private double calcPythagoras(LatLng start, LatLng end) {
        // a
        double lat = end.lat - start.lat;
        // b
        double lng = end.lng - start.lng;
        return calcPythagoras(lat, lng);
    }

    /**
     * Calculate the distance traveled of the currentStep in percentages.
     *
     * @return The distance traveled in percentages. Example: 0.83
     */
    private double calcStepProgressionPercent() {
        LatLng currentLoc = traveledRoute.peek().getLocation();
        // The total length of the step between two lat/lng points
        double totalLen = calcPythagoras(currentStep.startLocation, currentStep.endLocation);
        // The traveled length of the step between two lat/lng points
        double traveledLen = calcPythagoras(currentStep.startLocation, currentLoc);
        // The traveled length divided by the total length for the percentage (%)
        double progression = traveledLen / totalLen;
        return progression;
    }

    private LatLng calcStepProgressionLocation(double percent) {
        double lat = currentStep.endLocation.lat - currentStep.startLocation.lat;
        double lng = currentStep.endLocation.lng - currentStep.startLocation.lng;
        double newLat = lat * percent;
        double newLng = lng * percent;
        LatLng newLoc = new LatLng(currentStep.startLocation.lat + newLat, currentStep.startLocation.lng + newLng);
        return newLoc;
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

    private LocationTimed calcNextLoc(double interval) throws IndexOutOfBoundsException {
        Long stepDuration = currentStep.duration.inSeconds;
        double timeTraveled = stepDuration * calcStepProgressionPercent() + interval;

        if (stepDuration <= timeTraveled) {
            // Throws IndexOutOfBoundsException
            currentStep = calcNextStep();
            traveledRoute.add(new LocationTimed(currentStep.startLocation));
            return calcNextLoc(timeTraveled - stepDuration);
        }
        
        LatLng newLoc = calcStepProgressionLocation(timeTraveled / stepDuration);
        return new LocationTimed(newLoc);
    }
}
