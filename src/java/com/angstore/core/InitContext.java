/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.core;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author sohan
 */
@WebListener
public class InitContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngStorePU");
        sce.getServletContext().setAttribute("emf", emf);
        
//        InitAngStore ias = new InitAngStore(emf);
//        ias.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        try {
            EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
            emf.close();
        } catch (NullPointerException ex) {

        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngStorePU");
        //sce.getServletContext().setAttribute("emf", emf);
        
        InitAngStore ias = new InitAngStore(emf);
        ias.init();
    }
}
