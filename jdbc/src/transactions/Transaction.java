package transactions;

import java.sql.*;
import java.util.Scanner;

public class Transaction {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String password = "student";
		
		try {
			connection = DriverManager.getConnection(URL, user, password);
			connection.setAutoCommit(false);
			
			System.out.println("Salaries before\n");	
			showSalaries(connection, "HR");
			showSalaries(connection, "Engineering");
			
			statement = connection.createStatement();
			statement.executeUpdate("delete from employees where department='HR'");
			statement.executeUpdate("update employees set salary=300 where department='Engineering'");
			boolean isGood = askUserIfOkToSave();
			if(isGood) {
				connection.commit();
				System.out.println("\n Transaction COMMITED \n");
			} else {
				connection.rollback();
				System.out.println("\n Transaction ROLLED BACK \n");
			}
			
			System.out.println("Salaries after \n");	
			showSalaries(connection, "HR");
			showSalaries(connection, "Engineering");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
	}
	
	private static boolean askUserIfOkToSave() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Is it okay to save?  yes/no: ");
		String input = scanner.nextLine();

		scanner.close();

		return input.equalsIgnoreCase("yes");
	}

	private static void showSalaries(Connection connection, String theDepartment)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		System.out.println("Show Salaries for Department: " + theDepartment);

		try {
			statement = connection
					.prepareStatement("select * from employees where department=?");
			statement.setString(1, theDepartment);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				double salary = resultSet.getDouble("salary");
				String department = resultSet.getString("department");

				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName,
						department, salary);
			}

			System.out.println();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(statement, resultSet);
		}
	}

	private static void close(Connection connection, Statement statement,
			ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}

		if (statement != null) {
			statement.close();
		}

		if (connection != null) {
			connection.close();
		}
	}

	private static void close(Statement statement, ResultSet resultSet)
			throws SQLException {
		close(null, statement, resultSet);
	}
}
