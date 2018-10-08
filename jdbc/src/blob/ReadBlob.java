package blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class ReadBlob {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		InputStream input = null;
		FileOutputStream output = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			statement = connection.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			resultSet = statement.executeQuery(sql);
			
			output = new FileOutputStream(new File("new_resume.pdf"));
			
			if(resultSet.next()) {
				input = resultSet.getBinaryStream("resume");
				System.out.println("\n Reading resume from database.");
				System.out.println(sql);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				System.out.println("\n Compleated succesfully!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}
	
	private static void close(Connection connection, Statement statement) throws SQLException {
		
		if(connection != null) {
			connection.close();
		}
		
		if(statement != null) {
			statement.close();
		}
	}

}
