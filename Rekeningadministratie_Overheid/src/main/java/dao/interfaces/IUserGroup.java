package dao.interfaces;

import domain.UserGroup;
import java.util.List;

public interface IUserGroup {

    public List<UserGroup> findAll();

    public UserGroup findByName(String userGroup);

}
