package trivera.core.employee;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import trivera.core.employee.Customer;
import trivera.core.employee.CustomerDAO;
import trivera.core.employee.CustomerJDBCDAO;

public class TestCustomerJDBCDAO {
	CustomerDAO dao = new CustomerJDBCDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		PopulateWithJDBC.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		Customer returned = dao.insert(new Customer("Neville", "Longbottom", "555-1212", "neville@hogwarts.ac.uk"));
		assertNotNull(returned);
		Customer checked = null;
		checked = dao.findById(returned.getCustomerId());
		assertNotNull(checked);
		assertEquals("Neville", checked.getFirstName());
		assertEquals("Longbottom", checked.getLastName());
	}

	@Test
	public void testUpdate() {
		Customer g = dao.findById(7L);
		assertNotNull(g);
		g.setLastName("Weasley-Potter");
		dao.update(g);
		Customer g2 = dao.findById(7L);
		assertNotNull(g2);
		assertEquals("Weasley-Potter", g2.getLastName());
		g2.setLastName("Weasley");
		dao.update(g2); // Set it back
	}

	@Test
	public void testDelete() {
		Customer snape = dao.insert(new Customer("Severus", "Snape", "+44 0206 833-1212", "snape@hogwarts.ac.uk"));
		assertNotNull(snape);
		assertTrue(snape.getCustomerId() > 0);
		dao.delete(snape);
		Customer snapeFound = dao.findById(snape.getCustomerId());
		assertNull(snapeFound);
	}

	@Test
	public void testFindById() {
		Customer found = dao.findById(1L);
		assertNotNull("FindByID Found nothing (null)", found);
		assertEquals("FirstName was not Harry!", "Harry", found.getFirstName());
		assertEquals("LastName was not Potter!", "Potter", found.getLastName());
	}

	@Test
	public void testFindAll() {
		List<Customer> custs = dao.findAll();
		assertNotNull(custs);
		assertTrue("Empty Customer List in FindAll()!", custs.size() > 0);
	}

	@Test
	public void testFindByLastName() {
		String lastNameToFind = "Weasley";
		int numExpectedToFind = 4;
		List<Customer> foundByLastName = dao.findByLastName(lastNameToFind);
		assertNotNull(foundByLastName);
		assertEquals("Customer List Not the right size in FindByLastName()!", numExpectedToFind, foundByLastName.size());
	}

	@Test
	public void testFindByEmail() {
		String emailToFind = "ginny@hogwarts.ac.uk";
		List<Customer> foundByEmail = dao.findByEmail(emailToFind);
		assertNotNull(foundByEmail);
		assertTrue("No customers found by that email!", foundByEmail.size() > 0);
		assertEquals("Didn't find Ginny's email in FindByEmail()!", emailToFind, foundByEmail.get(0).getEmail());
	}

}
