package properties;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("my.properties"));
			
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			String url = properties.getProperty("url");
			
			printProperties(user, url);
			
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection succesfull.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//close operations
		}
	}
	
	private static void printProperties(String user, String url) {
		System.out.println("Database URL: " + url);
		System.out.println("User: " + user);
	}

}
