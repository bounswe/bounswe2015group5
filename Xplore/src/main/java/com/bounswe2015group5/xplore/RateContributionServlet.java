/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
    static final String LIKE_TABLE = "Like";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("You need to log in in order to rate a contribution.");
            } else {
                String userId = (String) session.getAttribute("userId");
                if (userId == null) {
                    out.print("You need to log in in order to rate a contribution.");
                } else {
                    String contribId = request.getParameter("contribId");
                    String isLike = request.getParameter("isLike");
                    if (registerRate(userId, contribId, isLike)) {
                        out.print("Succesful!");
                    } else {
                        out.print("An error has occured while processing request!");
                    }
                }
            }
        }
    }
    
    private static boolean registerRate(String userId, String contribId, String rate) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO " + LIKE_TABLE + "(UserID,ContribID,Rate) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.setInt(2, Integer.parseInt(contribId));
            stmt.setString(3, rate.equalsIgnoreCase("True") ? "UP" : "DOWN");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
