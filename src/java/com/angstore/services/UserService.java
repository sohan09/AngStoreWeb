/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.services;

import com.angstore.models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 *
 * @author sohan
 */
public class UserService {

    private EntityManagerFactory emf;

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public User login(String email, String password) {

        EntityManager em = emf.createEntityManager();
        try {

            String q = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            User user = em.createQuery(q, User.class)
                    .setParameter("email", email)
                    .setParameter("password", password).getSingleResult();

            return user;

        } catch (PersistenceException e) {

            return null;
        } finally {
            em.close();
        }

    }

    public User register(String email, String pass) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            User user = new User(email, pass);

            tx.begin();
            em.persist(user);
            tx.commit();
            return user;

        } catch (PersistenceException e) {

            return null;
        } finally {
            em.close();
        }
    }
}
