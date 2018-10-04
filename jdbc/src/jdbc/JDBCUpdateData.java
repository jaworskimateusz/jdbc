package jdbc;

import java.sql.*;

public class JDBCUpdateData {
	
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
			System.out.println("Updating a data to database. \n");
			
			// return number of appear
			int rowsAffected = statement.executeUpdate(
					"UPDATE employees " +
					"SET department = 'black pearl' " +
					"WHERE last_name = 'Sparrow' AND first_name = 'Jack'");
			System.out.println(rowsAffected + "\n");
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
