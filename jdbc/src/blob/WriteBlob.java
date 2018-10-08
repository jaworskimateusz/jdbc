package blob;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class WriteBlob {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		FileInputStream input = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			statement = connection.prepareStatement(sql);
			
			input = new FileInputStream(new File("resume.pdf"));
			statement.setBinaryStream(1, input);
			statement.executeUpdate();
			System.out.println("\n Compleated succesfully!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}
	
	private static void close(Connection connection, PreparedStatement statement) throws SQLException {
		
		if(connection != null) {
			connection.close();
		}
		
		if(statement != null) {
			statement.close();
		}
	}

}
