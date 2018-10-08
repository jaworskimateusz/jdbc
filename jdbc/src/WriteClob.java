import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteClob {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		FileReader input = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			statement = connection.prepareStatement(sql);
			
			input = new FileReader(new File("resume.pdf"));
			statement.setCharacterStream(1, input);
			statement.executeUpdate();
			System.out.println("\n Compleated succesfully!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(input);
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
	
	private static void close(FileReader input) throws IOException {
		if(input != null) {
			input.close();
		}
	}
	
}
