/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Laurent
 */
public class DTO_Location {
    
    private String date;
    private long id;
    private double latitude;
    private double longitude;
    
    @JsonCreator
    public DTO_Location(
            @JsonProperty("date")String date,
            @JsonProperty("id")long id,
            @JsonProperty("latitude")double latitude,
            @JsonProperty("longitude")double longitude){
        
        this.date = date;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;        
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
}
