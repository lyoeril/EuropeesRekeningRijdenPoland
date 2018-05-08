package domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.enterprise.inject.Model;
import javax.json.bind.annotation.JsonbTransient;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.apache.commons.codec.digest.DigestUtils;

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
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    
    private Long id;
    
    @Column(unique = true)
    private String username;
    private String picture;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    
    private String password;
    @ManyToMany(mappedBy = "users", cascade = ALL)
    private Set<UserGroup> groups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @JsonbTransient
    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha512Hex(password);
    }
    
    public String getStringGroups() {
        String[] array = new String[groups.size()];
        int index = 0;
        for(UserGroup ug : groups) {
            array[index] = ug.toString();
            index++;
        }
        return Arrays.toString(array);
    }

    public User() {

    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = DigestUtils.sha512Hex(password);
    }

    public User(String email, String username, String password, String picture, String firstName, String lastName, String location) {
        this(email, username, password);
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;

    }

    public void addGroup(UserGroup group) {
        this.groups.add(group);
        group.addUser(this);
    }
    
    public void removeGroup(UserGroup group) {
        this.groups.remove(group);
        group.removeUser(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.picture);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }
}

