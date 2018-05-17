/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.implementations;

import com.poland.dao.interfaces.jpa.BasicDAO;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;

/**
 *
 * @author PC-YOERI
 * @param <T>
 */
public abstract class BasicDAOImpl<T> implements BasicDAO<T> {

    @PersistenceContext(unitName = "com.poland_RegistratiePU")
    protected EntityManager em;

    private Class<T> type;

    public BasicDAOImpl() {
        type = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T create(T entity) {
        try {
            em.persist(entity);
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return entity;
    }

    @Override
    public void remove(T entity) {
        try {
            em.remove(entity);
        } catch (Exception ise) {
            handleExceptions(ise);
        }
    }

    @Override
    public T find(long id) {
        T ret = null;
        try {
            ret = em.find(type, id);
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return ret;
    }

    @Override
    public T edit(T entity) {
        T newEntity = null;
        try {
            newEntity = em.merge(entity);
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return newEntity;
    }

    @Override
    public int count() {
        int ret = 0;
        try {
            Query q = em.createQuery("select count(a) from " + type.getSimpleName() + " as a", Integer.TYPE);
            ret = ((Long) q.getSingleResult()).intValue();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return ret;
    }

    @Override
    public void handleExceptions(Exception ex) {
        if (ex instanceof IllegalStateException) {
            // If isActive() is false
            System.out.println("The EntityManager is not active. ");
            // TODO handle exception
        } else if (ex instanceof RollbackException) {
            // If the commit fails
            System.out.println("The commit failed due to an unknown error. ");
            // TODO handle exception
        } else if (ex instanceof QueryTimeoutException) {
            // If the statement execution exceeds the query timeout value
            System.out.println("The query took too long to be executed. ");
        } else if (ex instanceof IllegalArgumentException) {
            System.out.println("Entity type is not valid");
        } else if (ex instanceof NoResultException) {
            System.out.println("Entity type is not valid");
        }
    }

    @Override
    public void cleanTable(boolean areYouSure) {
        if (!areYouSure) {
            return;
        }
        try {
            String entityName = em.getMetamodel().entity(type).getName();
            em.createQuery("delete from " + entityName).executeUpdate();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
    }

}
