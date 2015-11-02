
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Contains contribution server operations. Created by Can Guler
 * on 02.11.2015.
 */
public class ContribServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Xplore";
    static final String USER = "root";
    static final String PASS = "password";
    static final String USER_TABLE = "Contribution";

    /**
     * Responds client by serving the content of a contribution or by adding
     * a contribution to the database. Important: request object should contain
     * a parameter named "operation" that is either equal to "read_contrib" or 
     * "save_contrib"
     *
     * @author Can Guler.
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
        
        if (op.equals("read_contrib")) {
            String contribId = request.getParameter("contrib_id");
            out.print(readContrib(contribId));
        } else if (op.equals("save_contrib")) {
            String userId = request.getParameter("user_id");
            String title = request.getParameter("contrib_title");
            String content = request.getParameter("contrib_content");
            String type = request.getParameter("contrib_type");
            String date = request.getParameter("contrib_date");
            out.print(saveContrib(userId, title, content, type, date));
        } 

        out.close();
    }
    
    /**
     * Adds a contribution to the table.
     * 
     * @author Can Guler
     * @param userId Id of the creator of the contribution
     * @param title Title of the contribution
     * @param content Content of the contribution
     * @param type Type of the contribution
     * @param date Date of the contribution
     * @return Message indicating the success.
     */
    private static String saveContrib(String userId, String title, String content, String type, String date) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO " + USER_TABLE
                    + "(UserID,Title,Content,Type,Date) VALUES(" + userId + "," +
                       "\"" + title + "\"," +
                       "\"" + content + "\"," +
                       type  + "," +
                       "\"" + date + "\")";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return "Contribution added.";
        } catch (Exception e) {
            return "Error occured.";
        }
        
    }
    
    /**
     * Returns the content of a contribution with the given id.
     * If it doesn't exist, returns an appropriate message.
     * 
     * @author Can Guler
     * @param contribId Id of the contribution
     * @return Content of the contribution, or an error.
     */
    private static String readContrib(String contribId) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Content FROM" + USER_TABLE + " WHERE ID=" + contribId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("Content");
            } else {
                return "No contribution with that id";
            }
        } catch (Exception e) {
            return "Database read error occured.";
        }
    }

}
