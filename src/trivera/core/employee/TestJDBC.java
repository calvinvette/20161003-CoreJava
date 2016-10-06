package trivera.core.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class TestJDBC {

	public static void main(String[] args) {
		List<Customer> customers = new Vector<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection(
						"jdbc:derby://localhost:1527/weasleydb;create=true",
						"sa",
						"sa"
					);
			try {
				Statement createTableStatement = connection.createStatement();
				createTableStatement.execute("CREATE TABLE customer ("
						+ "customerId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
						+ "firstName VARCHAR(25), "
						+ "lastName VARCHAR(25), "
						+ "phoneNumber VARCHAR(20), "
						+ "email VARCHAR(50)"
						+ ")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			PreparedStatement insertCustomerStatement = connection.prepareStatement(
					"INSERT INTO customer (firstName, lastName, phoneNumber, email) "
					+ "VALUES (?, ?, ?, ?)");
			
			insertCustomerStatement.setString(1, "Harry");
			insertCustomerStatement.setString(2, "Potter");
			insertCustomerStatement.setString(3, "555-1212");
			insertCustomerStatement.setString(4, "harry@hogwarts");
			
			insertCustomerStatement.executeUpdate();
			
			insertCustomerStatement.setString(1, "Ron");
			insertCustomerStatement.setString(2, "Weasley");
			insertCustomerStatement.setString(3, "555-1212");
			insertCustomerStatement.setString(4, "ron@hogwarts");
			
			insertCustomerStatement.executeUpdate();

			insertCustomerStatement.setString(1, "Hermione");
			insertCustomerStatement.setString(2, "Granger");
			insertCustomerStatement.setString(3, "555-1212");
			insertCustomerStatement.setString(4, "hermione@hogwarts");
			
			insertCustomerStatement.executeUpdate();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"SELECT customerId, firstName, lastName, phoneNumber, email FROM customer");
			ResultSetMetaData rsmd = resultSet.getMetaData();

			while (resultSet.next()) {
				customers.add(
					new Customer(
						resultSet.getLong("customerId"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("phoneNumber"),
						resultSet.getString("email")
					)
				); 
			}
			resultSet.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		
	}

}
