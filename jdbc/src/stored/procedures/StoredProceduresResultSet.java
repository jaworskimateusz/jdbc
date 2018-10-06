package stored.procedures;

import java.sql.*;

public class StoredProceduresResultSet {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String department = "Engineering";
			statement = connection
					.prepareCall("{call get_employees_for_department(?)}");
			statement.setString(1, department);
			statement.execute();
			resultSet = statement.getResultSet();
			display(resultSet);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
	}
	
	private static void display(ResultSet result) throws SQLException {
		while (result.next()) {
			String lastName = result.getString("last_name");
			String firstName = result.getString("first_name");
			double salary = result.getDouble("salary");
			String department = result.getString("department");

			System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
		}
	}
	
	private static void close(Connection connection, Statement statement,
			ResultSet result) throws SQLException {
		if (result != null) {
			result.close();
		}

		if (statement != null) {
			statement.close();
		}

		if (connection != null) {
			connection.close();
		}
	}
}

