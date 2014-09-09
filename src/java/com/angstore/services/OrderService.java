/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.services;

import com.angstore.models.Order;
import com.angstore.models.OrderStatus;
import com.angstore.models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 *
 * @author sohan
 */
public class OrderService {

    private EntityManagerFactory emf;

    public OrderService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Order create(Order order) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            em.persist(order);
            tx.commit();
            return order;

        } catch (PersistenceException e) {

            return null;
        } finally {
            em.close();
        }
    }

    public Order confirm(long id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Order order = em.find(Order.class, id);
            order.setStatus(OrderStatus.paid);
            tx.commit();
            return order;

        } catch (PersistenceException e) {

            return null;
        } finally {
            em.close();
        }

    }
    
    public Order deliver(long id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Order order = em.find(Order.class, id);
            order.setStatus(OrderStatus.delivered);
            tx.commit();
            return order;

        } catch (PersistenceException e) {

            return null;
        } finally {
            em.close();
        }

    }
}
