/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angstore.controllers;

import com.angstore.models.User;
import com.angstore.services.CategoryService;
import com.angstore.services.ProductService;
import com.angstore.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sohan
 */
public class Register extends HttpServlet {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        if (productService == null) {
            productService = new ProductService(emf);
        }

        if (categoryService == null) {
            categoryService = new CategoryService(emf);
        }
        
        if (userService == null) {
            userService = new UserService(emf);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        if (productService == null) {
            productService = new ProductService(emf);
        }

        if (categoryService == null) {
            categoryService = new CategoryService(emf);
        }
        
        if (userService == null) {
            userService = new UserService(emf);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        User user = userService.register(email, pass);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), "Registered");

    }
}
