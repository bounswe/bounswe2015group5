/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.xplore;

import static com.bounswe2015group5.xplore.LoginServlet.USER_TABLE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet that serves all contributions with their creator's names and surnames.
 * @author Can Guler
 */
public class AllContributionsServlet extends HttpServlet {

    /**
     * Returns all contributions as an array of JSONObjects.
     * Each object includes title, content, and date of the contribution with 
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
            out.print(getAllContributions().toJSONString());
        }
    }

    private JSONArray getAllContributions() {
        try {
            JSONArray result = new JSONArray();
            DBConnection conn = new DBConnection();
            String sql = "select \n"
                    + "Contribution.Title, \n"
                    + "Contribution.Content, \n"
                    + "Contribution.Date, \n"
                    + "User2.Name, \n"
                    + "User2.Surname\n"
                    + "from Contribution\n"
                    + "inner join User2\n"
                    + "on Contribution.UserID = User2.ID;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject row = new JSONObject();
                row.put("title", rs.getString("Title"));
                row.put("content", rs.getString("Content"));
                row.put("date", rs.getString("Date"));
                row.put("name", rs.getString("Name"));
                row.put("surname", rs.getString("Surname"));
                result.add(row);
            }
            rs.close();
            stmt.close();
            conn.close();
            return result;
        } catch (Exception e) {
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
