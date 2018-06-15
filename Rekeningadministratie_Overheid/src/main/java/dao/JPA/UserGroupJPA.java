package dao.JPA;

import domain.UserGroup;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.interfaces.IUserGroupDAO;
import javax.ejb.Stateless;

@Stateless
public class UserGroupJPA implements IUserGroupDAO {

    @PersistenceContext(unitName = "Rekeningadministratie_Overheid")
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
