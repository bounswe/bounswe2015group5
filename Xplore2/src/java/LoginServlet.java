
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Contains login managing server operations. Created by Mehmet Burak Kurutmaz
 * on 04.11.2015.
 */
public class LoginServlet extends HttpServlet {

    static final String USER_TABLE = "User2";

    /**
     * Responds client by checking email address and password. 
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
            String pass = request.getParameter("pass");
            String names[] = checkUser(email, pass);
            if (names.length == 0) {
                out.print("Failed to login!");
            } else {
                out.print("Login Successful!");
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("name", names[0]);
                session.setAttribute("surname", names[1]);
            }
        }
    }

    /**
     * Checks the database if the user is registered and the password matches.
     *
     * @author Mehmet Burak Kurutmaz.
     * @param email email address that will be searched in database
     * @param pass password that will be searched in database
     * @return name and surname of the user
     */
    private static String[] checkUser(String email, String pass) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "SELECT Name,Surname FROM " + USER_TABLE + " WHERE Email=? AND Pass=PASSWORD(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            String[] names;
            if (rs.next()) {
                names = new String[]{rs.getString("Name"), rs.getString("Surname")};
            } else {
                names = new String[]{};
            }
            rs.close();
            stmt.close();
            conn.close();
            return names;
        } catch (Exception e) {
            return new String[]{};
        }
    }

}
