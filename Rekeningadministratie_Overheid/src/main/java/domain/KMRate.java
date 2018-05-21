/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.VehicleType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.inject.Model;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Laurent
 */

@Entity
@Model
@NamedQueries({
    @NamedQuery(name = "KMRate.findAll",
            query = "SELECT k FROM KMRate k")
    ,
    @NamedQuery(name = "KMRate.findByRegion",
            query = "SELECT k FROM KMRate k WHERE k.region LIKE :region")
})
public class KMRate implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String region;
    
    @ElementCollection
    @CollectionTable(name = "KMRATE_VehicleType")
    @MapKeyJoinColumn(name = "KMRATE_VALUE")
    @Column(name = "KMRATE")
    private Map<VehicleType, Double> ratePerVehicleType;
    
    //JPA
    public KMRate(){
        
    }

    public KMRate(long id, String region) {
        this.id = id;
        this.region = region;
        ratePerVehicleType = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<VehicleType, Double> getRatePerVehicleType() {
        return ratePerVehicleType;
    }

    public void setRatePerVehicleType(Map<VehicleType, Double> ratePerVehicleType) {
        this.ratePerVehicleType = ratePerVehicleType;
    }
    
    public void addRatePerVehicleType(VehicleType vehicleType, double rate){
        this.ratePerVehicleType.put(vehicleType, rate);
    }
    
    public double getRateFromVehicleType(VehicleType vehicleType){
        return this.ratePerVehicleType.get(vehicleType);
    }
    
    
    
}
