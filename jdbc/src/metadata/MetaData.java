package metadata;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class MetaData {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			
			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion() + "\n");
			
			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}
	
	private static void close(Connection connection) throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
}
