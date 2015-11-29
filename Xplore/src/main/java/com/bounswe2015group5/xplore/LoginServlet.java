package com.bounswe2015group5.xplore;

import com.bounswe2015group5.database.*;
import com.bounswe2015group5.entities.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Contains login managing server operations. Created by Mehmet Burak Kurutmaz
 * on 04.11.2015.
 */
public class LoginServlet extends HttpServlet {

    /**
     * Responds client by checking email address and password. 
     *
     * @author Mehmet Burak Kurutmaz.
     * @param request Request object that is sent by client
     * @param response Response object that will be sent to client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("Email");
            String pass = request.getParameter("Password");
            User us = Query.getUserByEmailAndPassword(email, pass);
            if (us.isNull("Email")) {
                out.print("Failed to login!");
            } else {
                out.print("Login Successful!");
                HttpSession session = request.getSession();
                session.setAttribute("Email", us.getEmail());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
