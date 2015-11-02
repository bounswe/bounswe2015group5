
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
public class SignupServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Xplore";
    static final String USER = "root";
    static final String PASS = "password";
    static final String USER_TABLE = "User2";

    /**
     * Responds client by checking an email address or creating a new user
     * account. Important: request object should contain a parameter named
     * "operation" that is either equal to "check_email" or "add_user"
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
        PrintWriter out = response.getWriter();

        String op = request.getParameter("operation"); // get the type of the operation

        if (op.equals("check_email")) { // if operation is checking email
            String email = request.getParameter("email");
            out.print(checkEmail(email)); // print true if email is occupied
        } else if (op.equals("add_user")) { // if operation is creating a new user account
            String email = request.getParameter("email");
            if (checkEmail(email)) {
                String pass = request.getParameter("pass");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                if (addUser(email, pass, name, surname)) { // create user account
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    out.print("Successful!");
                } else { // if not successful
                    out.print("An error has occured while processing request!");
                }
            } else {
                out.print("This email is already in use!");
            }
        }
        out.close();
    }

    /**
     * Checks the database if the email address is in use.
     * 
     * @author Mehmet Burak Kurutmaz.
     * @param email email address that will be searched in database
     * @return true if email is occupied
     */
    private static boolean checkEmail(String email) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Email FROM" + USER_TABLE + " WHERE Email=\"" + email + "\"";
            ResultSet rs = stmt.executeQuery(sql);
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
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO " + USER_TABLE
                    + "(Email,Pass,Name,Surname) VALUES(\"" + email + "\"," + "PASSWORD(\"" + pass + "\"),"
                    + "\"" + name + "\"," + surname + "\")";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
