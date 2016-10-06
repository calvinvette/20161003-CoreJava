package trivera.core.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class CustomerJDBCDAO implements CustomerDAO {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet;
	private static PreparedStatement findByEmailQuery;
	private static PreparedStatement findByCustomerId;
	private static PreparedStatement findByLastName;
	private static PreparedStatement insertCustomerStatement;
	private static PreparedStatement deleteCustomerStatement;
	private static PreparedStatement updateCustomerStatement;
	

	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection("jdbc:derby://localhost:1527/weasleydb;create=true", "sa", "sa");
			findByEmailQuery = connection.prepareStatement(
					"SELECT * FROM customer WHERE email = ?");
			findByCustomerId = connection.prepareStatement(
					"SELECT * FROM customer WHERE customerId = ?");
			findByLastName = connection.prepareStatement(
					"SELECT * FROM customer WHERE lastName = ?");
			insertCustomerStatement = connection.prepareStatement(
						"INSERT INTO customer (firstName, lastName, phoneNumber, email) "
						+ "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			deleteCustomerStatement = connection.prepareStatement(
					"DELETE FROM customer WHERE customerId = ?");
			updateCustomerStatement = connection.prepareStatement(
					"UPDATE customer SET firstName = ?, lastName = ?, phoneNumber = ?, email = ? WHERE customerId = ?");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static Statement getStatement() {
		if (statement == null) {
			try {
				statement = getConnection().createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statement;
	}

	@Override
	public Customer insert(Customer c) {
		try {
			insertCustomerStatement.setString(1, c.getFirstName());
			insertCustomerStatement.setString(2, c.getLastName());
			insertCustomerStatement.setString(3, c.getPhoneNumber());
			insertCustomerStatement.setString(4, c.getEmail());
			insertCustomerStatement.executeUpdate();
			ResultSet keys = insertCustomerStatement.getGeneratedKeys();
			if (keys != null && keys.next()) {
				c.setCustomerId(keys.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer update(Customer c) {
		try {
			updateCustomerStatement.setString(1, c.getFirstName());
			updateCustomerStatement.setString(2, c.getLastName());
			updateCustomerStatement.setString(3, c.getPhoneNumber());
			updateCustomerStatement.setString(4, c.getEmail());
			updateCustomerStatement.setLong(5, c.getCustomerId());
			updateCustomerStatement.executeUpdate();
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer delete(Customer c) {
		try {
			deleteCustomerStatement.setLong(1, c.getCustomerId());
			deleteCustomerStatement.executeUpdate();
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer findById(Long customerId) {
		try {
			findByCustomerId.setLong(1, customerId);
			ResultSet rs = findByCustomerId.executeQuery();
			if (rs.next()) {
				return rowToCustomer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Customer rowToCustomer(ResultSet rs) throws SQLException {
		return new Customer(
				rs.getLong("customerId"),
				rs.getString("firstName"),
				rs.getString("lastName"),
				rs.getString("phoneNumber"),
				rs.getString("email")
			);
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new Vector<>();
		try {
			resultSet = getStatement().executeQuery(
					"SELECT customerId, firstName, lastName, phoneNumber, email FROM customer");
			while (resultSet.next()) {
				customers.add(
					rowToCustomer(resultSet)
				); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		List<Customer> customers = new Vector<>();
		try {
			findByLastName.setString(1, lastName);
			resultSet = findByLastName.executeQuery();
			while (resultSet.next()) {
				customers.add(
					rowToCustomer(resultSet)
				); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public List<Customer> findByEmail(String email) {
		List<Customer> customers = new Vector<>();
		try {
			findByEmailQuery.setString(1, email);
			resultSet = findByEmailQuery.executeQuery();
			while (resultSet.next()) {
				customers.add(
					rowToCustomer(resultSet)
				); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

}
