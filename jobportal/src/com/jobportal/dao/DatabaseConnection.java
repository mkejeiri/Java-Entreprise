package com.jobportal.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;    

    private DatabaseConnection() {
    	final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";		
		final String DB_USR="root";
		final String DB_PWD="root";	
		final String DB_URL="jdbc:mysql://localhost/JobPortal?user="+DB_USR+"&password="+DB_PWD+"&useSSL=false";		
		try {
			Class.forName(JDBC_DRIVER).newInstance();					
    		 connection =  DriverManager.getConnection(DB_URL);
		} catch (InstantiationException | IllegalAccessException e) {				
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException{
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}