/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.angstore.controllers;

import com.angstore.models.Category;
import com.angstore.models.Product;
import com.angstore.services.CategoryService;
import com.angstore.services.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sohan
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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
        String cat = request.getParameter("id") + "";
        long cat_id = parseLong(cat);

        Category category = categoryService.find(cat_id);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), category);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public int parseInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (NullPointerException ex) {
            
        } catch (NumberFormatException ex) {
            
        }
        return 0;
    }

    public long parseLong(String val) {
        try {
            return Long.parseLong(val);
        } catch (NullPointerException ex) {
            
        } catch (NumberFormatException ex) {
            
        }
        return 0;
    }
}
