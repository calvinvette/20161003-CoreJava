package trivera.core.employee;

import java.util.List;

/**
 * CustomerDAO implements the DAO pattern for Customers
 * @author calvin
 *
 */
public interface CustomerDAO {
	/**
	 * insert takes a Customer, stores it, and returns
	 * a possibly slightly modified variant of it
	 * (typically adding an implementation-generated customerId)
	 * @param c - customer to store
	 * @return customer, possibly modified
	 */
	public Customer insert(Customer c);
	public Customer update(Customer c);
	public Customer delete(Customer c);
	public Customer findById(Long customerId);
	public List<Customer> findAll();
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByEmail(String email);
}
