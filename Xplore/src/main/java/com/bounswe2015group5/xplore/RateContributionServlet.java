/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author burak
 */
public class RateContributionServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("You need to log in in order to rate a contribution.");
            } else {
                String email = (String) session.getAttribute("Email");
                User us = Query.getUserByEmail(email);
                Rate r = new Rate(Query.requestToJSONObject(request));
                r.setUserID(us.getID());
                Update.registerRate(r);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RateContributionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
