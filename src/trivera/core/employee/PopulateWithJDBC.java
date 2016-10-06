package trivera.core.employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class PopulateWithJDBC {

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
				createTableStatement.execute("DROP TABLE customer");
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("customers.tab"));
				String s = null;
				String headerLine = br.readLine(); // Skip the header line
				while ((s = br.readLine()) != null) {
					String[ ] fields = s.split("\t");
						Customer c = new Customer(
							Long.parseLong(fields[0]), // customerId 
							fields[1], // firstName
							fields[2], // lastName
							fields[3], // phone
							fields[4]  // email
							);
						insertCustomerStatement.setString(1, c.getFirstName());
						insertCustomerStatement.setString(2, c.getLastName());
						insertCustomerStatement.setString(3, c.getPhoneNumber());
						insertCustomerStatement.setString(4, c.getEmail());
						insertCustomerStatement.executeUpdate();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"SELECT customerId, firstName, lastName, phoneNumber, email FROM customer");
			ResultSetMetaData rsmd = resultSet.getMetaData();

			while (resultSet.next()) {
				customers.add(
					rowToCustomer(resultSet)
				); 
			}
			resultSet.close();
			
			PreparedStatement findByEmailQuery = connection.prepareStatement(
					"SELECT * FROM customer WHERE email = ?");
			findByEmailQuery.setString(1, "harry@hogwarts.ac.uk");
			ResultSet found = findByEmailQuery.executeQuery();
			if (found.next()) {
				System.out.println("Found by email: " + rowToCustomer(found));
			}
			found.close();
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

	private static Customer rowToCustomer(ResultSet resultSet) throws SQLException {
		return new Customer(
			resultSet.getLong("customerId"),
			resultSet.getString("firstName"),
			resultSet.getString("lastName"),
			resultSet.getString("phoneNumber"),
			resultSet.getString("email")
		);
	}

}
