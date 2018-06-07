package domain;

import enums.VehicleType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Model
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll",
            query = "SELECT v FROM Vehicle v")
})
public class Vehicle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Rekeningrijder> ownersHistory;

    @OneToOne
    private Cartracker cartracker;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    
    private String licensePlate;
    
    //JPA
    public Vehicle(){
        
    }

    public Vehicle(VehicleType vehicleType, String licensePlate) {
        this.ownersHistory = new ArrayList<>();
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Rekeningrijder> getOwnersHistory() {
        return ownersHistory;
    }

    public void setOwnersHistory(List<Rekeningrijder> ownersHistory) {
        this.ownersHistory = ownersHistory;
    }

    public Cartracker getCartracker() {
        return cartracker;
    }

    public void setCartracker(Cartracker cartracker) {
        this.cartracker = cartracker;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    

}
