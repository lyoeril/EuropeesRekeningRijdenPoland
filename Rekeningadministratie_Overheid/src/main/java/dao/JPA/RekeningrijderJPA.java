/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.JPA;

import dao.interfaces.IRekeningrijderDAO;
import domain.Rekeningrijder;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laurent
 */
public class RekeningrijderJPA implements IRekeningrijderDAO{

    private static final Logger LOGGER = Logger.getLogger(RekeningrijderJPA.class.getName());

    @PersistenceContext(unitName = "TODO")
    private EntityManager em;

    public RekeningrijderJPA() {
    }

    public RekeningrijderJPA(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Rekeningrijder rekeningrijder) {
        em.persist(rekeningrijder);
    }

    @Override
    public void edit(Rekeningrijder rekeningrijder) {
        em.merge(rekeningrijder);
    }

    @Override
    public void delete(Rekeningrijder rekeningrijder) {
        em.remove(rekeningrijder);
    }

    @Override
    public Rekeningrijder findById(long id) {
        return em.find(Rekeningrijder.class, id);
    }
}
