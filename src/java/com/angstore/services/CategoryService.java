/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.services;

import com.angstore.models.Category;
import com.angstore.models.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sohan
 */
public class CategoryService {

    private EntityManagerFactory emf;

    public CategoryService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Category find(long id) {

        EntityManager em = emf.createEntityManager();
        try {

            return em.find(Category.class, id);

        } finally {
            em.close();
        }

    }
    
    public List<Category> findByParentIsNull() {
        return findByParentIsNull(0, 100);
    }
    
    public List<Category> findByParentIsNull(int offset, int max) {

        EntityManager em = emf.createEntityManager();
        try {
            String q = "SELECT p from Category p WHERE p.parent IS NULL";
            TypedQuery<Category> tq = em.createQuery(q, Category.class)
                    .setFirstResult(offset)
                    .setMaxResults(max);
            return tq.getResultList();
        } finally {
            em.close();
        }

    }

    public List<Category> findByParent(Category category) {
        return findByParent(category, 0, 100);
    }
    
    public List<Category> findByParent(Category category, int offset, int max) {

        EntityManager em = emf.createEntityManager();
        try {
            String q = "SELECT p from Category p WHERE p.parent=:cat";
            TypedQuery<Category> tq = em.createQuery(q, Category.class)
                    .setParameter("cat", category)
                    .setFirstResult(offset)
                    .setMaxResults(max);
            return tq.getResultList();
        } finally {
            em.close();
        }

    }

    public Category findByNameAndParent(String name, Category parent) {

        EntityManager em = emf.createEntityManager();
        try {
            String q = "SELECT p from Category p WHERE p.name=:nm AND p.parent=:cat";
            TypedQuery<Category> tq = em.createQuery(q, Category.class)
                    .setParameter("nm", name)
                    .setParameter("cat", parent);
            return tq.getSingleResult();
        } finally {
            em.close();
        }

    }

    public Category findByNameAndParent(String name, String parent) {

        EntityManager em = emf.createEntityManager();
        try {
            String q = "SELECT p from Category p WHERE p.name=:nm AND p.parent.name=:par";
            TypedQuery<Category> tq = em.createQuery(q, Category.class)
                    .setParameter("nm", name)
                    .setParameter("par", parent);
            List<Category> categorys = tq.getResultList();
            return categorys.get(0);
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngStorePU");
        CategoryService cs = new CategoryService(emf);
        
        cs.findByNameAndParent("Apple", "Laptop");
    }

}
