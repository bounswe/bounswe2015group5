package com.bounswe2015group5.xplore;

import java.io.IOException;
import java.io.PrintWriter;
import com.bounswe2015group5.database.*;
import com.bounswe2015group5.entities.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Contains sign up managing server operations. Created by Mehmet Burak Kurutmaz
 * on 29.10.2015.
 */
public class RegisterUserServlet extends HttpServlet {

    /**
     * Responds client by checking an email address and creating a new user
     * account.
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
            User us = new User(Query.requestToJSONObject(request));
            if (!Query.checkUser(us.getEmail())) {
                Update.registerUser(us, (String) us.get("Password"));
                HttpSession session = request.getSession();
                session.setAttribute("Email", us.getEmail());
                out.print("Succesful!");
            } else {
                out.print("This email is already in use!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegisterUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
