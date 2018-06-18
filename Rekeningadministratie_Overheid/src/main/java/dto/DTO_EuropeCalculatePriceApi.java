/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Cartracker;

/**
 *
 * @author Laurent
 */
public class DTO_EuropeCalculatePriceApi {

    private String uuid;
    private Double tripTotalKM;
    private Double tripPrice;

    public DTO_EuropeCalculatePriceApi(Cartracker cartracker) {
        this.uuid = "at1q1243";
        this.tripPrice =50.1;
        this.tripTotalKM= 55.0;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Double getTripTotalKM() {
        return tripTotalKM;
    }

    public void setTripTotalKM(Double tripTotalKM) {
        this.tripTotalKM = tripTotalKM;
    }

    public Double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(Double tripPrice) {
        this.tripPrice = tripPrice;
    }

    
}
