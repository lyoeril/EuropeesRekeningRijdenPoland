/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.User;
import domain.UserGroup;
import java.util.HashSet;

/**
 *
 * @author Laurent
 */
public class DTO_User {
    
    private long id;
    private String username;
    private String email;
 //   private HashSet<UserGroup> groups =  new HashSet<>();
 //   private boolean km_prijs;

    public DTO_User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
      //  this.groups = user.getGroups();
//        this.km_prijs = user.getKm_prijs();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public HashSet<UserGroup> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(HashSet<UserGroup> groups) {
//        this.groups = groups;
//    }

//    public boolean isKm_prijs() {
//        return km_prijs;
//    }
//
//    public void setKm_prijs(boolean km_prijs) {
//        this.km_prijs = km_prijs;
//    }
}
