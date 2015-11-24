/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import static com.bounswe2015group5.xplore.RegisterContributionServlet.CONTRIB_TABLE;
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
 * @author Can Guler
 */
public class RegisterCommentServlet extends HttpServlet {
    
    static final String COMMENT_TABLE = "Comment";

    /** 
     * Handles the request.
     * Gets user id from the session.
     * Gets contribId, content, type from the request.
     * Uses curdate of mysql as the date of the comment.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @author Can Guler
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("You need to log in in order to comment.");
            } else {
                String userId = (String) session.getAttribute("userId");
                if (userId == null) {
                    out.print("You need to log in in order to comment.");
                } else {
                    String contribId = request.getParameter("contribId");
                    String content = request.getParameter("content");
                    String type = request.getParameter("type");
                    if (registerComment(userId, contribId, content, type)) {
                        out.print("Succesful!");
                    } else {
                        out.print("An error has occured while processing request!");
                    }
                }
            }
        }
    }
    
    /**
     * Inserts a comment to database
     * @param userId
     * @param contribId
     * @param content
     * @param type
     * @return Success
     * @author Can Guler
     */
    private static boolean registerComment(String userId, String contribId, String content, String type) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO " + COMMENT_TABLE + "(UserID,ContribID,Content,Type,Date) VALUES(?,?,?,?,CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.setInt(2, Integer.parseInt(contribId));
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
