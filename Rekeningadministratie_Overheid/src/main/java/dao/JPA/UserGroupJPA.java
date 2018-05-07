package dao.JPA;

import dao.interfaces.IUserGroup;
import domain.UserGroup;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserGroupJPA implements IUserGroup {

    @PersistenceContext(unitName = "TODO")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UserGroup> findAll() {
        return em.createNamedQuery("UserGroup.findAll").getResultList();
    }

    @Override
    public UserGroup findByName(String userGroup) {
        return em.find(UserGroup.class, userGroup);
    }

}
