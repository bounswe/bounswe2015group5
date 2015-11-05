package com.bounswe2015group5.xplore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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

    static final String USER_TABLE = "User2";

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
            String email = request.getParameter("email");
            if (checkEmail(email)) {
                String pass = request.getParameter("pass");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                if (addUser(email, pass, name, surname)) { // create user account
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("name", name);
                    session.setAttribute("surname", name);
                    out.print("Succesful!");
                } else { // if not successful
                    out.print("An error has occured while processing request!");
                }
            } else {
                out.print("This email is already in use!");
            }
        }
    }
    
    /**
     * Checks the database if the email address is in use.
     *
     * @author Mehmet Burak Kurutmaz.
     * @param email email address that will be searched in database
     * @return true if email is occupied
     */
    private static boolean checkEmail(String email) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "SELECT Email FROM" + USER_TABLE + " WHERE Email=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            boolean occupied = rs.next();
            rs.close();
            stmt.close();
            conn.close();
            return occupied;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Creates an account for a new user.
     *
     * @author Mehmet Burak Kurutmaz.
     * @param email Email address of user
     * @param pass Hashed password of the user
     * @param name Name of the user
     * @param surname Surname of the user
     * @return true if operation is succeeded
     */
    
    private static boolean addUser(String email, String pass, String name, String surname) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO " + USER_TABLE + "(Email,Pass,Name,Surname) VALUES(?,PASSWORD(?),?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            stmt.setString(3, name);
            stmt.setString(4, surname);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}