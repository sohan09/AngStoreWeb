/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.core;

import com.angstore.models.Category;
import com.angstore.models.Product;
import com.angstore.services.CategoryService;
import com.angstore.services.ProductService;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 *
 * @author sohan
 */
public class InitAngStore {

    private EntityManagerFactory emf;
    private EntityManager em;

    public InitAngStore(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void init() {

        try {

            em = emf.createEntityManager();

            Category Desktop = new Category("Desktop", null);

            em.persist(Desktop);

            Category Laptop = new Category("Laptop", null);
            em.persist(Laptop);
            Category Tablet = new Category("Tablet", null);
            em.persist(Tablet);
            Category Software = new Category("Software", null);
            em.persist(Software);
            Category Accessories = new Category("Accessories", null);
            em.persist(Accessories);

            Category[] cat = new Category[]{
                new Category("Mac", Desktop),
                new Category("PC", Desktop),
                new Category("Linux", Desktop),};

            addAll(cat);

            addAll(new Category[]{
                new Category("Apple", Laptop),
                new Category("Samsung", Laptop),
                new Category("Toshiba", Laptop),
                new Category("Lenovo", Laptop),
                new Category("Dell", Laptop),});

            addAll(new Category[]{
                new Category("Apple", Tablet),
                new Category("Samsung", Tablet),
                new Category("Google", Tablet),
                new Category("Kindle", Tablet),});

            addAll(new Category[]{
                new Category("Microsoft", Software),
                new Category("Adobe", Software),
                new Category("Symantec", Software),});

            addAll(new Category[]{
                new Category("TVs", Accessories),
                new Category("Mice", Accessories),
                new Category("Keyboards", Accessories),
                new Category("Cameras", Accessories),
                new Category("Phones", Accessories),});

            //FOr Test
            CategoryService cs = new CategoryService(emf);

            List<Category> categorys = cs.findByParentIsNull();
            System.out.println(categorys);
            
            for (Category p : cs.findByParentIsNull()) {

                for (Category c : cs.findByParent(p)) {
                    int i = 1;
                    addAll(new Product[]{
                        
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                        new Product((i++) + "." + c.getSku(), 102.10, 10, Arrays.asList(new Category[]{c})),
                    });
                }

            }

        } finally {
            em.close();
        }

    }

    private void addAll(Category[] cats) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            for (Category c : cats) {
                em.persist(c);
            }

            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();

        }

    }

    private void addAll(Product[] products) {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            for (Product c : products) {
                em.persist(c);
            }

            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();

        }

    }
    
    public static void main(String[] args) {
        
    }
}
