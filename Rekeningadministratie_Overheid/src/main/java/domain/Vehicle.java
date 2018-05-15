package domain;

import enums.VehicleType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String autorisationNumber;
    private String serialNumber;

    @OneToMany
    private List<Rekeningrijder> ownersHistory;

    @OneToOne
    private Cartracker cartracker;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle(String autorisationNumber, String serialNumber, VehicleType vehicleType) {
        this.autorisationNumber = autorisationNumber;
        this.serialNumber = serialNumber;
        this.ownersHistory = new ArrayList<>();
        this.vehicleType = vehicleType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutorisationNumber() {
        return autorisationNumber;
    }

    public void setAutorisationNumber(String autorisationNumber) {
        this.autorisationNumber = autorisationNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

}
