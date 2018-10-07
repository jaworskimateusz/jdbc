package metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class ResultSetDemo {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		java.sql.ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			
			statement = connection.createStatement();
			resultSet =  statement.executeQuery("select id, last_name, first_name, salary from employees");
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			
			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("Column count: " + columnCount + "\n");
			
			for (int column=1; column <= columnCount; column++) {
				System.out.println("Column name: " + resultSetMetaData.getColumnName(column));
				System.out.println("Column type name: " + resultSetMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: " + resultSetMetaData.isNullable(column));
				System.out.println("Is Auto Increment: " + resultSetMetaData.isAutoIncrement(column) + "\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
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
