package dao.interfaces;

import domain.UserGroup;
import java.util.List;

public interface IUserGroupDAO {

    public List<UserGroup> findAll();

    public UserGroup findByName(String userGroup);

}
