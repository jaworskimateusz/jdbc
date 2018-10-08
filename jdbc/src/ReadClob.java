

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class ReadClob {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		Reader input = null;
		FileWriter output = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			statement = connection.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			resultSet = statement.executeQuery(sql);
			
			output = new FileWriter(new File("new_resume_v2.pdf"));
			
			if(resultSet.next()) {
				input = resultSet.getCharacterStream("resume");
				System.out.println("\n Reading resume from database.");
				System.out.println(sql);
				byte[] buffer = new byte[1024];
				int character;
				while((character = input.read()) > 0) {
					output.write(character);
				}
				System.out.println("\n Compleated succesfully!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(input,output);
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
	private static void close(Reader input, FileWriter output) throws IOException {
		
		if(input != null) {
			input.close();
		}
		
		if(output != null) {
			output.close();
		}
		
	}

}
