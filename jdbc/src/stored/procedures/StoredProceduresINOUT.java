package stored.procedures;

import java.sql.*;

public class StoredProceduresINOUT {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement statement = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String department = "Engineering";
			statement = connection
					.prepareCall("{call greet_the_department(?)}");
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setString(1, department);
			statement.execute();
			System.out.println("Result: " + statement.getString(1));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//close method
		}
	}
}

