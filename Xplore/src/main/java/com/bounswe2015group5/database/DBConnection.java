package com.bounswe2015group5.database;

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Contains Database connection managing server operations. Created by Mehmet Burak Kurutmaz
 * on 29.10.2015.
 */
public class DBConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Xplore";
    static final String USER = "root";
    static final String PASS = "password";
    
    private Connection conn = null;
    
    /**
     * Connection object for Xplore database.
     * 
     * @author Mehmet Burak Kurutmaz.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public DBConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    /**
     * Prepares an statement to execute on Xplore database.
     * 
     * @author Mehmet Burak Kurutmaz.
     * @param sql prepared sql statement
     * @return prepared statement from parameter sql 
     * @throws SQLException 
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }
    
    
    
    /**
     * Closes database connection.
     * 
     * @author Mehmet Burak Kurutmaz.
     * @throws SQLException 
     */
    public void close() throws SQLException{
        conn.close();
    }
}
