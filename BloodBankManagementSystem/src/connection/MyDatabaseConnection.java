package connection;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class MyDatabaseConnection {

	private static Connection connection = null;
	private static MyDatabaseConnection dbConnection = null;

	private void initialize() throws Exception {
		Properties pro = new Properties();
		InputStream ins = getClass().getResourceAsStream("Connection.properties");
			pro.load(ins);
			String driver = pro.getProperty("driver");
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
	}

	public static Connection getConnection() throws Exception {
		dbConnection = new MyDatabaseConnection();
		dbConnection.initialize();
		return connection;
	}
}
