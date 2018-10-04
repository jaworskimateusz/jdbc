package jdbc;

import java.sql.*;

public class JDBCDeleteData {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			
			statement = connection.createStatement();
			System.out.println("Deleting a data from database. \n");

			statement.executeUpdate(
					"DELETE FROM employees " +
					"WHERE department = 'black pearl' ");
			
			resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY last_name");
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + ", " +resultSet.getString("first_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null ) {
				resultSet.close();
			} 
		} 
		
	}
	
}
