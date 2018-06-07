package eu.poland.domain;

import com.google.maps.model.LatLng;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author Robin
 */
public class LocationTimed implements Comparable<LocationTimed> {
    private LatLng location;
    private Calendar date;
    
    /**
     * Constructs a LocationTimed with a latitude/longitude pair, and the host
     * system's current date/time. 
     * 
     * @param location The latitude/longitude pair of this LocationTimed. 
     */
    public LocationTimed(LatLng location) {
        this(location, new GregorianCalendar());
    }

    /**
     * Constructs a LocationTimed with a latitude/longitude pair and a date/time. 
     * 
     * @param location The latitude/longitude pair of this LocationTimed. 
     * @param date The date/time pair of this LocationTimed. 
     */
    public LocationTimed(LatLng location, Calendar date) {
        this.location = location;
        this.date = date;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Compares the time values (millisecond offsets from the Epoch) represented 
     * by two LocationTimed objects.
     * 
     * @param other The LocationTimed to be compared. 
     * @return The value 0 if the time represented by the argument is equal to
     * the time represented by this LocationTimed; a value less than 0 if the
     * time of this LocationTimed is after the time represented by the 
     * argument; and a value greater than 0 if the time of this LocationTimed is
     * before the time represented by the argument. 
     */
    @Override
    public int compareTo(LocationTimed other) {
        return other.date.compareTo(this.date);
    }
    
    /**
     * Return a String representation of this LocationTimed. 
     * 
     * @return The String representation of this LocationTimed in the format: 
     * "[date] latitude, longitude"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", date.toInstant(), location);
    }
}
