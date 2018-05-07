package dao.interfaces;

import domain.User;
import java.util.List;

public interface IUserDAO {

    List<User> findAll();

    User findById(long id);

    List<User> findByEmail(String email);

    List<User> findByUsername(String username);

    boolean insert(User user);

    boolean update(User user);

}
