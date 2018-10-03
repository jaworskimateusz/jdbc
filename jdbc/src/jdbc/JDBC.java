package jdbc;

import java.sql.*;

public class JDBC {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			System.out.println("Connection success.");
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select * from employees");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + 
						" " + resultSet.getString("first_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
