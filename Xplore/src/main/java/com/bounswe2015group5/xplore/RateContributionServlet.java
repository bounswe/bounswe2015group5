/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import com.bounswe2015group5.database.*;
import com.bounswe2015group5.entities.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author burak
 */
public class RateContributionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = br.readLine();
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("You need to log in in order to rate a contribution.");
            } else {
                String email = session.getAttribute("Email").toString();
                User us = Query.getUserByEmail(email);
                Rate r = Rate.stringToRate(json);
                r.setUserID(us.getID());
                try {
                    Update.registerRate(r);
                } catch (SQLException | ClassNotFoundException e) {
                }
                out.print("Your rate is saved.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RateContributionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
