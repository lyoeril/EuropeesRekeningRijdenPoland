/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Laurent
 */
public class Ride {

    private long id;
    private Calendar startDate;
    private Calendar endDate;

    private List<Location> locations;

    public Ride(long id, Calendar startDate, Calendar endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locations = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}
