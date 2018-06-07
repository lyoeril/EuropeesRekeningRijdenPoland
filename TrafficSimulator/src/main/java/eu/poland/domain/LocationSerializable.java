package eu.poland.domain;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Robin
 */
public class LocationSerializable extends LocationTimed implements Serializable {
    
    private String trackerId;

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }
    
    public LocationSerializable(LocationTimed locationTimed, String trackerId) {
        super(locationTimed.getLocation(), locationTimed.getDate());
        this.trackerId = trackerId;
    }
    
    public LocationSerializable(LatLng location, String trackerId) {
        super(location);
        this.trackerId = trackerId;
    }
    
    public LocationSerializable(LatLng location, Calendar date, String trackerId) {
        super(location, date);
        this.trackerId = trackerId;
    }
}
