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

            
            
            add(new Product(
                    "psvita",
                    "Playstation Vita",
                    "Blur the lines between entertainment and reality with PlayStation® Vita. Transform the way you connect, game and share. A stunning 5-inch OLED screen and front and back cameras transport your physical world into your digital one. The biggest and best games for a social world. And with always-on Wi-Fi, you can engage nearby gamers with a virtual gift or some good ol' fashioned smack talk, anywhere, anytime. The action never ends - just store it to the cloud and pick up on the Vita or back on the PS3.",
                    50.0,
                    null),
                    null
            );
            
            
            add(new Product(
                    "nexus",
                    "Google Nexus",
                    "HTC Google Nexus One has a 5 MP camera. It has 512MB RAM, 512MB ROM Internal memory. Dimensions are 119 x 59.8 x 11.5 mm and Weight is 130 g.It supports GPRS, EDGE, Wi-Fi, 3G and USB.",
                    100.0,
                    null),
                    null
            );
            
            
            add(new Product(
                    "hmx",
                    "Samsung HMX-H104",
                    "Blur the lines between entertainment and reality with PlayStation® Vita. Transform the way you connect, game and share. A stunning 5-inch OLED screen and front and back cameras transport your physical world into your digital one. The biggest and best games for a social world. And with always-on Wi-Fi, you can engage nearby gamers with a virtual gift or some good ol' fashioned smack talk, anywhere, anytime. The action never ends - just store it to the cloud and pick up on the Vita or back on the PS3.",
                    50.0,
                    null),
                    null
            );
            
            
            add(new Product(
                    "kindle",
                    "Amazon Kindle Fire",
                    "Kindle Fire HDX tablets are the first tablets to be powered by the 2.2 GHz quad-core Snapdragon 800 processor, making them the first to clock in at over 2 GHz.",
                    100.0,
                    null),
                    null
            );
            
            
            add(new Product(
                    "lumix",
                    "Panasonic LUMIX DMC-FH25",
                    "The LUMIX DMC-FH25 shoots high-quality 16.1 megapixel images and combines a 28mm wide-angle*1 LEICA DC lens with a powerful 8x optical zoom to take amazing photos. Redesigned with an easy-to-hold grip and slim and stylish profile, it features a 2.7 Intelligent LCD, 720p High Definition (HD) video recording and advanced iA (Intelligent Auto) for ease of use.",
                    159.0,
                    null),
                    null
            );
            
            add(new Product(
                    "tomtom",
                    "TomTom Start XL - Automotive GPS receiver",
                    "The TomTom Start is specifically designed for occasional drivers and drivers who haven't had a chance to experience the benefits of car navigation. The simplified menu displays a two button user interface, 'Plan route' and 'Browse map'. The device is lightweight, has a compact 4.3 screen and has a semi-fixed EasyPort mount, making the whole navigation package fit into a pocket, small bag or glove compartment.",
                    30.0,
                    null),
                    null
            );

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

    private void add(Product c, Category category) {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            c.setCategorys(Arrays.asList(new Category[]{category}));
            em.persist(c);

            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();

        }

    }

    private void addAll(Product[] products, Category category) {

        EntityTransaction tx = em.getTransaction();     //Adding All
        try {
            tx.begin();

            for (Product c : products) {
                c.setCategorys(Arrays.asList(new Category[]{category}));
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
