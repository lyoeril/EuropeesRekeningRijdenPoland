package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "UserGroup.findAll", query = "SELECT ug FROM UserGroup ug")
public class UserGroup implements Serializable {

    @Id
    private String usergroup;

    @ManyToMany
    @JoinTable(name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "usergroup",
                    referencedColumnName = "usergroup"),
            inverseJoinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"))
    private Set<User> users = new HashSet<>();

    public String getGroupName() {
        return usergroup;
    }

    public void setGroupName(String usergroup) {
        this.usergroup = usergroup;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public UserGroup() {

    }

    public UserGroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return usergroup;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof UserGroup)) {
//            return false;
//        }
//
//        UserGroup otherGroup = (UserGroup) obj;
//        if (this.hashCode() == otherGroup.hashCode()) {
//            return true;
//        }
//
//        return this.usergroup.equals(otherGroup.usergroup);
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 59 * hash + Objects.hashCode(this.usergroup);
//        return hash;
//    }

}

