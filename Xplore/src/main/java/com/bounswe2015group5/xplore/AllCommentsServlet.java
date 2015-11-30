/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import com.bounswe2015group5.database.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet that serves all comments of a contribution with 
 * their creator's names and surnames.
 * @author Can Guler
 */

public class AllCommentsServlet extends HttpServlet {

    /**
     * Returns all comments of a contribution as an array of JSONObjects.
     * Each object includes content, and date of the comment with 
     * its creator's name and surname.
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
            String contribId = request.getParameter("contribId");
            out.print(getAllComments(contribId).toString());
        }
    }
    
    private JSONArray getAllComments(String contribId) {
        try {
            JSONArray result = new JSONArray();
            DBConnection conn = new DBConnection();
            String sql = "select "
                    + "Comment.Content, "
                    + "Comment.Date, "
                    + "User2.Name, "
                    + "User2.Surname "
                    + "from Comment "
                    + "inner join User2 "
                    + "on Comment.UserID = User2.ID "
                    + "where Comment.ContribID = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(contribId));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject row = new JSONObject();
                row.put("content", rs.getString("Content"));
                row.put("date", rs.getString("Date"));
                row.put("name", rs.getString("Name"));
                row.put("surname", rs.getString("Surname"));
                result.put(row);
            }
            rs.close();
            stmt.close();
            conn.close();
            return result;
        } catch (SQLException | ClassNotFoundException | NumberFormatException | JSONException e) {
            return new JSONArray();
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
