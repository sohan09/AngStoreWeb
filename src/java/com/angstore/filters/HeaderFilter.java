/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.angstore.filters;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
public class HeaderFilter implements Filter  {
   public void  init(FilterConfig config) 
                         throws ServletException{

   }
   public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {

       response.setContentType("application/json");
       response.setCharacterEncoding("UTF-8");
       

      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }
   
   public void destroy( ){
      /* Called before the Filter instance is removed 
      from service by the web container*/
   }
}
