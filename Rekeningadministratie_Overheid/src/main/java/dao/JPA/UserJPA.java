package dao.JPA;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import domain.User;

import dao.interfaces.IUserDAO;

@Stateless
public class UserJPA implements IUserDAO{
    
    private static final Logger LOGGER = Logger.getLogger(UserJPA.class.getName());

    @PersistenceContext(unitName = "TODO")
    private EntityManager em ;

    public UserJPA(){   
    }
    
    public UserJPA(EntityManager em){
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail").setParameter("email", email).getResultList();
    }

    @Override
    public List<User> findByUsername(String username) {
        return em.createNamedQuery("User.findByUsername").setParameter("username", "%"+ username + "%").getResultList();
    }

    @Override
    public boolean insert(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean update(User user) {
        em.merge(user);
        return true;
    }   
}

