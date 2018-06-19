/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Laurent
 */
public class DTO_Ride_JSON {
     private long id;
    private Calendar startDate;
    private Calendar endDate;
    private List<DTO_Location_JSON> locations;
    
    @JsonCreator
    public DTO_Ride_JSON(
            @JsonProperty("id")long id, 
            @JsonProperty("startDate")Calendar startDate, 
            @JsonProperty("endDate")Calendar endDate,
            @JsonProperty("locations")List<DTO_Location_JSON> locations){
        
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locations = locations;        
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

    public List<DTO_Location_JSON> getLocations() {
        return locations;
    }

    public void setLocations(List<DTO_Location_JSON> locations) {
        this.locations = locations;
    }
    
    
}
