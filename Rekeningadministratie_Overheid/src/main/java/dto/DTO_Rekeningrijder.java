/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Rekeningrijder;
import java.io.Serializable;

/**
 *
 * @author Laurent
 */
public class DTO_Rekeningrijder implements Serializable{
    
    private long id;
    private String username;
    private String address;
    private String email;
    
    public DTO_Rekeningrijder(Rekeningrijder rekeningrijder){
        this.id = rekeningrijder.getId();
        this.username = rekeningrijder.getUsername();
        this.address = rekeningrijder.getAddress();
        this.email = rekeningrijder.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
