/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.servlets;

/**
 *
 * @author rizzom
 */
// Import required java libraries
import java.io.*;
import javax.ejb.EJB;
//import java.sql.Connection;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
//import javax.sql.DataSource;
//import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;


// Extend HttpServlet class
public class StateServlet extends HttpServlet {
    @EJB
    SystemEJBLocal systemEJB;

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String prefix = systemEJB.getSetting("system-id", "");
      
        // Set response content type
        response.setContentType("text/plain");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println( prefix);
    }

}
