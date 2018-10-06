package stored.procedures;

import java.sql.*;

public class StoredProceduresOUT {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		CallableStatement statement = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String department = "Engineering";
			statement = connection
					.prepareCall("{call get_count_for_department(?,?)}");
			statement.setString(1, department);
			statement.registerOutParameter(2, Types.INTEGER);
			statement.execute();
			System.out.println("Counted elements: " + statement.getInt(2));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}
	
	private static void close(Connection connection, Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
		}

		if (connection != null) {
			connection.close();
		}
	}	
}

