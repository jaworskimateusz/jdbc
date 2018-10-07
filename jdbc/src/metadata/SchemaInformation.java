package metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaInformation {

	public static void main(String[] args) throws SQLException {
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;

		Connection connection = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);

			DatabaseMetaData databaseMetaData = connection.getMetaData();

			System.out.println("List of Tables");
			System.out.println("--------------");

			resultSet = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern,
					types);

			while (resultSet.next()) {
				System.out.println(resultSet.getString("TABLE_NAME"));
			}

			System.out.println("\n\nList of Columns");
			System.out.println("--------------");

			resultSet = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

			while (resultSet.next()) {
				System.out.println(resultSet.getString("COLUMN_NAME"));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(connection, resultSet);
		}
	}
	
	private static void close(Connection connection, ResultSet resultSet)
			throws SQLException {
	
		if (resultSet != null) {
			resultSet.close();
		}
	
		if (connection != null) {
			connection.close();
		}
	}
}
