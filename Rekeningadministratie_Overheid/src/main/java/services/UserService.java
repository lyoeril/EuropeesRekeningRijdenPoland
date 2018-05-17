package services;

import dao.interfaces.IUserDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import domain.UserGroup;
import domain.User;
import dao.interfaces.IUserGroupDAO;

/**
 *
 * @author Mastermouse
 */
@Stateless
public class UserService {

    @Inject
    private IUserDAO userDAO;

    @Inject
    private IUserGroupDAO rankGroupDAO;

    public List<User> allUsers(int offset, int limit) {
        List<User> users = userDAO.findAll();
        int toIndex = users.size() < (offset + limit) ? users.size() : (offset + limit);
        return users.subList(offset, toIndex);
    }
    
    public List<User> allUsers() {
        return allUsers(0, 20);
    }

    public List<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean register(User u) {
        return userDAO.insert(u);
    }

    public void addGroup(User user, UserGroup userGroup) {
        User persistedUser = userDAO.findById(user.getId());
        if (persistedUser == null) {
            return;
        }

        UserGroup persistedGroup = rankGroupDAO.findByName(userGroup.getGroupName());
        if (persistedGroup == null) {
            return;
        }

        persistedUser.addGroup(persistedGroup);
    }

    public void removeGroup(User user, UserGroup userGroup) {
        User persistedUser = userDAO.findById(user.getId());
        if (persistedUser == null) {
            return;
        }

        UserGroup persistedGroup = rankGroupDAO.findByName(userGroup.getGroupName());
        if (persistedGroup == null) {
            return;
        }

        persistedUser.removeGroup(persistedGroup);
    }
}
