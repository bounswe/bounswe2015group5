import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/Xplore";
        static final String USER = "root";
        static final String PASS = "";
        
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String operation=request.getParameter("operation");
                
                if(operation.equals("add_user")){
                    addUser(request.getParameter("username"),request.getParameter("password"));
                }
                else{
                    out.print(getUsers());
                }
            }
	}

    private static void addUser(String username,String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql= "INSERT INTO User (Username,Password) VALUES ('"+username+"','"+password+"')";
            stmt.executeUpdate(sql);
            //conn.commit();
            stmt.close();
            conn.close();
        }catch(Exception e){
        }
    }
    
    private static String getUsers() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User");
            String ans="";
            while(rs.next()){
                ans += ("<tr><th>"+rs.getString("Username")+"</th><th>"+rs.getString("Password")+"</th></tr>\n");
            }
            rs.close();
            stmt.close();
            conn.close();
            return ans;
        }catch(Exception e){
            return "";
        }
    }

}
