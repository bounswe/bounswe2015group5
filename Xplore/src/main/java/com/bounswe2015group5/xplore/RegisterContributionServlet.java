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
 * @author can
 */
public class RegisterContributionServlet extends HttpServlet {
   
    static final String CONTRIB_TABLE = "Contribution";
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            if (session == null) {
                out.print("You need to log in in order to contribute.");
            } else {
                String userId = (String) session.getAttribute("userId");
                if (userId == null) {
                    out.print("You need to log in in order to contribute.");
                } else {
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    String type = request.getParameter("type");
                    if (registerContrib(userId, title, content, type)) {
                        out.print("Succesful!");
                    } else {
                        out.print("An error has occured while processing request!");
                    }
                }
            }
        }
    }
    
    private static boolean registerContrib(String userId, String title, String content, String type) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO " + CONTRIB_TABLE + "(UserID,Title,Content,Type,Date) VALUES(?,?,?,?,CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.setInt(4, Integer.parseInt(type));
            System.out.print(stmt.toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
