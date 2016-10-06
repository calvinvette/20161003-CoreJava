package trivera.core.employee;

import java.util.List;

public interface CustomerDAO {
	public Customer insert(Customer c);
	public Customer update(Customer c);
	public Customer delete(Customer c);
	public Customer findById(Long customerId);
	public List<Customer> findAll();
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByEmail(String email);
}
