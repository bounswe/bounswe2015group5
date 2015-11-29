/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import com.bounswe2015group5.database.Query;
import com.bounswe2015group5.database.Update;
import com.bounswe2015group5.entities.Contribution;
import com.bounswe2015group5.entities.User;
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
 *
 * @author Can Guler
 */
public class RegisterContributionServlet extends HttpServlet {
   
    /**
     * Handles the post request. 
     * Gets user id from the session.
     * Gets title, content, type from the post message.
     * Uses curdate of mysql as the date of the contribution.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @author Mehmet Burak Kurutmaz
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("You need to log in in order to comment.");
            } else {
                String email = (String) session.getAttribute("Email");
                User us = Query.getUserByEmail(email);
                Contribution cont = new Contribution(Query.requestToJSONObject(request));
                cont.setUserID(us.getID());
                cont.setName(us.getName());
                cont.setSurname(us.getSurname());
                //com.setType(request.getParameter("Content"))
                Update.registerContribution(cont);
                out.println("Your Contribution is saved!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RateContributionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
