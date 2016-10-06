package trivera.core.employee;

import java.io.IOException;
import java.util.List;

public class TestCustomerDAO {

	public static void main(String[] args) {
		 CustomerDAO dao = new CustomerMockDAO();
//		CustomerDAO dao = new CustomerJDBCDAO();

		// findAll tests
		List<Customer> custs = dao.findAll();
		if (custs != null && custs.size() > 0) {
			System.out.println("Find All success");
//			for (Customer customer : custs) {
//				System.out.println(customer);
//			}
		} else {
			System.out.println("Find All Fail");
		}

		// findById tests
		Customer found = dao.findById(1L);
		if (found != null) {
			if (found.getFirstName() != null && found.getFirstName().equals("Harry") && found.getLastName() != null
					&& found.getLastName().equals("Potter")) {
				System.out.println("FindById success");
			} else {
				System.out.println("FindById failed... (not Harry!)");
			}
		} else {
			System.out.println("FindById failed... (nothing found!)");
		}

		// Test for insert
		Customer returned = dao.insert(new Customer("Neville", "Longbottom", "555-1212", "neville@hogwarts.ac.uk"));
		Customer checked = null;
		if (returned != null) {
			checked = dao.findById(returned.getCustomerId());
		}
		if (checked != null) {
			System.out.println("Insert Success");
		} else {
			System.out.println("Failed Insert");
		}

		// update test
		Customer g = dao.findById(7L);
		if (g != null) {
			g.setLastName("Weasley-Potter");
			dao.update(g);
			Customer g2 = dao.findById(7L);
			if (g2 != null && g2.getLastName().equals("Weasley-Potter")) {
				System.out.println("Update Success");
			} else {
				System.out.println("Update Failed!");
			}
		} else {
			System.out.println("Update Failed! - Fix FindByID first");
		}
		// reset DB!

		// delete tests

		Customer snape = dao.insert(new Customer("Severus", "Snape", "+44 0206 833-1212", "snape@hogwarts.ac.uk"));
		// System.out.println(snape);
		dao.delete(snape);
		if (snape != null) {
			Customer snapeFound = dao.findById(snape.getCustomerId());
			if (snapeFound == null) {
				System.out.println("Delete Success");
			} else {
				System.out.println("Delete Failed!");
			}
		} else {
			System.out.println("Delete Failed! - Fix FindByID first");
		}

		// findByEmail tests
		String emailToFind = "ginny@hogwarts.ac.uk";
		List<Customer> foundByEmail = dao.findByEmail(emailToFind);
		if (foundByEmail != null && foundByEmail.size() == 1 && foundByEmail.get(0).getEmail().equals(emailToFind)) {
			System.out.println("FindByEmail success");
		} else {
			System.out.println("FindByEmail fail");
		}

		// findByLastName tests
		String lastNameToFind = "Weasley";
		int numExpectedToFind = 3;
		List<Customer> foundByLastName = dao.findByLastName(lastNameToFind);
		if (foundByLastName != null && foundByLastName.size() == numExpectedToFind
				&& foundByLastName.get(0).getLastName().equals(lastNameToFind)) {
			System.out.println("FindByLastName success");
		} else {
			System.out.println("FindByLastName fail: ");
			boolean exists = foundByLastName != null;
			System.out.println("\tExists? " + exists);
			if (exists) {
				System.out.println("\tSize? " + foundByLastName.size());
				System.out.println("\tLastName? " + foundByLastName.get(0).getLastName());
			}
		}
		
		try {
			long numCusts = CustomerMockDAO.dumpData("customers.new.tab");
			System.out.println(numCusts + " written to customers.new.tab...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
