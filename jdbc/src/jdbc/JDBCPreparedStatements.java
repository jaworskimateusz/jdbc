package jdbc;

import java.sql.*;

public class JDBCPreparedStatements {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			statement = connection
					.prepareStatement("SELECT * FROM employees WHERE salary > ? and department=?");
			statement.setDouble(1,10000);
			statement.setString(2, "HR");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + 
						" " + resultSet.getString("first_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
