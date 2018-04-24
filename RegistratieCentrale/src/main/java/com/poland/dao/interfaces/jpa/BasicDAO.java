/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.interfaces.jpa;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;

/**
 *
 * @author PC-YOERI
 */
public interface BasicDAO<T> {

    T create(T entity);

    void remove(T entity);

    T find(long id);

    T edit(T entity);
    
    int count();
    
    void handleExceptions(Exception ex);
    
    void cleanTable(boolean areYouSure);
}
