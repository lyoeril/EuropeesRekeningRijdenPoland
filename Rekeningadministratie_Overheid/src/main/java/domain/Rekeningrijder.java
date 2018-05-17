/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Laurent
 */
@Entity
public class Rekeningrijder extends User implements Serializable {

    private String address;
    
    @OneToMany
    private List<Invoice> invoices = new ArrayList<>();
    
    @OneToMany      
    private List<Vehicle> ownedVehicles = new ArrayList<>();

    public Rekeningrijder() {
    }

    public Rekeningrijder(String username, String password, String address, String email) {
        super(username, password, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Vehicle> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(List<Vehicle> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }
    
    

}
