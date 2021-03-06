package jdbc;

import java.sql.*;

public class JDBC {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			System.out.println("Connection success. \n");
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM employees");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + 
						" " + resultSet.getString("first_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
