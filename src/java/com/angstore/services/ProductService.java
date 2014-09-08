/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.services;

import com.angstore.models.Category;
import com.angstore.models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sohan
 */
public class ProductService {

    private EntityManagerFactory emf;

    public ProductService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Product> findAllByCategory(Category category, int offset, int max) {

        EntityManager em = emf.createEntityManager();
        try {
            
            String q = "SELECT p from Product p WHERE p.categorys=:cat";
            TypedQuery<Product> tq = em.createQuery(q, Product.class)
                    .setParameter("cat", category)
                    .setFirstResult(offset)
                    .setMaxResults(max);
            return tq.getResultList();

        } catch (NullPointerException ex) {

            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
    
    public Long countAllByCategory(Category category) {

        EntityManager em = emf.createEntityManager();
        try {
            
            String q = "SELECT COUNT( p ) from Product p WHERE p.categorys=:cat";
            TypedQuery<Long> tq = em.createQuery(q, Long.class)
                    .setParameter("cat", category);
            return tq.getSingleResult();

        } catch (NullPointerException ex) {

            return new Long(0);
        } finally {
            em.close();
        }
    }

    public Product find(long id) {

        EntityManager em = emf.createEntityManager();
        try {

            return em.find(Product.class, id);

        } finally {
            em.close();
        }

    }
    
    public List<Product> defaultProducts() {
        EntityManager em = emf.createEntityManager();
        
        ArrayList<Product> products = new ArrayList<>();
        for(long i = 26; i <= 31; i++) {
            products.add(em.find(Product.class, i));
        }
        return products;
    }

    public List<Product> productsByCategoryIsNull() {

        EntityManager em = emf.createEntityManager();
        try {

            String q = "SELECT p from Product p WHERE p.categorys IS NULL";
            TypedQuery<Product> tq = em.createQuery(q, Product.class);
            return tq.getResultList();

        } finally {
            em.close();
        }
    }
    
    public Product findByName(String name) {

        EntityManager em = emf.createEntityManager();
        try {

            String q = "SELECT p from Product p WHERE p.name=:nm";
            TypedQuery<Product> tq = em.createQuery(q, Product.class)
                    .setParameter("nm", name);
            return tq.getSingleResult();

        } finally {
            em.close();
        }
    }
    
    public Product findBySku(String sku) {

        EntityManager em = emf.createEntityManager();
        try {

            String q = "SELECT p from Product p WHERE p.sku=:sk";
            TypedQuery<Product> tq = em.createQuery(q, Product.class)
                    .setParameter("sk", sku);
            return tq.getSingleResult();

        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngStorePU");
        
        ProductService ps = new ProductService(emf);
        
        Product product = ps.find(10);
        
        System.out.println(product + "");
    }

}
