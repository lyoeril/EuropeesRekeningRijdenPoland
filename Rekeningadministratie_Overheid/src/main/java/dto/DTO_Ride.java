/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Location;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Laurent
 */
public class DTO_Ride {
    
    private long id;
    private String startDate;
    private String endDate;
    private List<DTO_Location> locations;
    
    @JsonCreator
    public DTO_Ride(
            @JsonProperty("id")long id, 
            @JsonProperty("startDate")String startDate, 
            @JsonProperty("endDate")String endDate,
            @JsonProperty("locations")List<DTO_Location> locations){
        
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<DTO_Location> getLocations() {
        return locations;
    }

    public void setLocations(List<DTO_Location> locations) {
        this.locations = locations;
    }
    
    
    
}
