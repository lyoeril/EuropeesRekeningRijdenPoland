/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.KMRate;
import enums.VehicleType;
import java.util.Map;

/**
 *
 * @author Laurent
 */
public class DTO_KMRate {
    
    private long id;
    private Map<VehicleType, Double> rates;
    private String region;

    public DTO_KMRate(KMRate kMRate) {
        this.id = kMRate.getId();
        this.rates = kMRate.getRatePerVehicleType();
        this.region = kMRate.getRegion();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<VehicleType, Double> getRates() {
        return rates;
    }

    public void setRates(Map<VehicleType, Double> rates) {
        this.rates = rates;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
    
}
