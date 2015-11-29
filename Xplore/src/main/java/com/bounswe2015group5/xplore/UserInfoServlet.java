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
 * Contains UserInfo managing server operations. Created by Mehmet Burak
 * Kurutmaz on 04.11.2015.
 */
public class UserInfoServlet extends HttpServlet {

    /**
     * Responds client by sending its user information.
     *
     * @author Mehmet Burak Kurutmaz.
     * @param request Request object that is sent by client
     * @param response Response object that will be sent to client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            String email = (String) session.getAttribute("Email");
            User us = new User(Query.getUserByEmail(email));
            out.print(us);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
