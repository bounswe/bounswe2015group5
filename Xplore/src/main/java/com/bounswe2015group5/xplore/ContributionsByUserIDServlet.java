package com.bounswe2015group5.xplore;

import com.bounswe2015group5.database.Query;
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
 * @author Mehmet Burak Kurutmaz
 */
public class ContributionsByUserIDServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try(PrintWriter out=response.getWriter()){
            int UserID = Integer.parseInt(request.getParameter("UserID")); // get UserID from request
            HttpSession session = request.getSession(false); // get session without creating a new one
            if (session == null) { // if session is null then user is not logged in yet
                out.print(Query.getContributionsByUserID(UserID,-1)); // send a JSONArray containing Contribution objects
            } else { // if user is logged in
                String email = session.getAttribute("Email").toString(); // send a JSONArray containing Contribution objects
                User us = Query.getUserByEmail(email); //  get user
                out.print(Query.getContributionsByUserID(UserID, us.getID())); // send a JSONArray containing Contribution objects
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(CommentsByContributionIDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        return "Returns Contributions of an user by UserID";
    }

}
