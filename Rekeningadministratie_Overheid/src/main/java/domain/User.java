package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Model
@NamedQueries({
    @NamedQuery(name = "User.findAll",
            query = "SELECT u FROM User u")
    ,
    @NamedQuery(name = "User.findByEmail",
            query = "SELECT u FROM User u WHERE u.email LIKE :email")
    ,
    @NamedQuery(name = "User.findByUsername",
            query = "SELECT u FROM User u WHERE u.username LIKE :username")
})
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private boolean km_prijs;
    
    
    @ManyToMany(mappedBy = "users", cascade = ALL)
    private List<UserGroup> groups = new ArrayList<>();
    
    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.km_prijs = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public void addGroup(UserGroup group) {
        this.groups.add(group);
        group.addUser(this);
    }
    
    public void removeGroup(UserGroup group) {
        this.groups.remove(group);
        group.removeUser(this);
    }  

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isKm_prijs() {
        return km_prijs;
    }

    public void setKm_prijs(boolean km_prijs) {
        this.km_prijs = km_prijs;
    }
    
    
}
