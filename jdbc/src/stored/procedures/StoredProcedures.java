package stored.procedures;

import java.sql.*;

public class StoredProcedures {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement statement = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String department = "Engineering";
			int increaseAmount = 10000;
			statement = connection
					.prepareCall("call increase_salaries_for_department(?,?)");
			statement.setString(1, department);
			statement.setDouble(2, increaseAmount);
			statement.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//close method
		}
	}
}

