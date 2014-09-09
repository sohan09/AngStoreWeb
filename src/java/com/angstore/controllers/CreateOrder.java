/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.angstore.controllers;

import com.angstore.models.Address;
import com.angstore.models.Order;
import com.angstore.models.OrderStatus;
import com.angstore.models.User;
import com.angstore.services.CategoryService;
import com.angstore.services.OrderService;
import com.angstore.services.ProductService;
import com.angstore.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class CreateOrder extends HttpServlet {


    private UserService userService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        if (userService == null) {
            userService = new UserService(emf);
        }

        if (orderService == null) {
            orderService = new OrderService(emf);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        if (userService == null) {
            userService = new UserService(emf);
        }

        if (orderService == null) {
            orderService = new OrderService(emf);
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

        Order order = new Order();
        Address shippingAddress = new Address();
        
        User user = new User();
        
        shippingAddress.setApartment(null);
        shippingAddress.setCity(null);
        shippingAddress.setCountryName(null);
        shippingAddress.setFirstName(null);
        shippingAddress.setLastName(null);
        shippingAddress.setPostalcode(null);
        shippingAddress.setState(null);
        shippingAddress.setStreet(null);
        
        
        user.setAddress1(null);
        user.setAddress2(null);
        user.setBillingAddress(shippingAddress);
        
        
        order.setComment(null);
        order.setOrderDate(null);
        order.setOrderItems(null);
        order.setPaymentMethod(null);
        order.setShippingAddress(null);
        order.setStatus(OrderStatus.draft);
        order.setTotal(0);
        order.setUser(null);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), null);
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        for(Method m : Order.class.getDeclaredMethods()) {
            System.out.println(m.getName().startsWith("set") + " " + m.getName());
            
        }
        Order order = Order.class.newInstance();
        Object invoke = Order.class.getMethod("setId", Long.class).invoke(order, new Long(10));
        System.out.println(invoke);
    }
    
    public static Method getMethod(Class<?> c, String name) {
        for(Method m : c.getDeclaredMethods()) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
}
