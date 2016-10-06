package trivera.core.employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

public class CustomerMockDAO implements CustomerDAO {

	private static File customerFile;
	private static Map<Long, Customer> custsByCustomerId = new Hashtable<>();
	private static Long lastCustomerId = 0L;
	
	public static File getCustomerFile() {
		return customerFile;
	}

	public static void setCustomerFile(File customerFile) {
		CustomerMockDAO.customerFile = customerFile;
	}

	public static Map<Long, Customer> getCustsByCustomerId() {
		return custsByCustomerId;
	}

	public static void setCustsByCustomerId(Map<Long, Customer> custsByCustomerId) {
		CustomerMockDAO.custsByCustomerId = custsByCustomerId;
	}

	public static Long dumpData(String fileName) throws IOException {
		Long numberOfRecordsWritten = 0L;
		PrintWriter out =
				new PrintWriter(
						new FileWriter(
								//new OutputStreamWriter(
										//new GZIPOutputStream(
												new File(fileName)
										//)
								//)
						)
				);
		for (Customer c : getCustsByCustomerId().values()) {
			out.println(c.getCustomerId()
					+ "\t" + c.getFirstName()
					+ "\t" + c.getLastName()
					+ "\t" + c.getPhoneNumber()
					+ "\t" + c.getEmail()
					);
			numberOfRecordsWritten++;
		}
		out.flush();
		out.close();
		return numberOfRecordsWritten;
	}
	
	static {
		// Placeholder customers...
		getCustsByCustomerId().put(1L, new Customer(1L, "Harry", "Potter", "555-1212", "info@hogwarts.ac.uk"));
		getCustsByCustomerId().put(2L, new Customer(2L, "Ron", "Weasley", "555-1212", "info@hogwarts.ac.uk"));
		getCustsByCustomerId().put(3L, new Customer(3L, "Hermione", "Granger", "555-1212", "info@hogwarts.ac.uk"));
		
		if (getCustomerFile() == null) {
			setCustomerFile(new File("customers.tab"));
		}
//		System.out.println("File: " + getCustomerFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(getCustomerFile()));
			String s = null;
			setCustsByCustomerId(new HashMap<>());
			String headerLine = br.readLine(); // Skip the header line
			while ((s = br.readLine()) != null) {
//			String fields[ ] = s.split("\t");
				String[ ] fields = s.split("\t");
				try {
					getCustsByCustomerId().put(++lastCustomerId, 
							new Customer(
									Long.parseLong(fields[0]), // customerId 
									fields[1], // firstName
									fields[2], // lastName
									fields[3], // phone
									fields[4]  // email
											));
				} catch (NumberFormatException e) {
					System.err.println("Can't convert CustomerID! " + fields[0]);
				}
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
	}

	@Override
	public Customer insert(Customer c) {
		getCustsByCustomerId().put(++lastCustomerId, c);
		c.setCustomerId(lastCustomerId);
		return c;
	}

	@Override
	public Customer update(Customer c) {
		getCustsByCustomerId().put(c.getCustomerId(), c);
		return c;
	}

	@Override
	public Customer delete(Customer c) {
		getCustsByCustomerId().remove(c.getCustomerId());
		return c;
	}

	@Override
	public Customer findById(Long customerId) {
		return getCustsByCustomerId().get(customerId);
	}

	@Override
	public List<Customer> findAll() {
		return new Vector<Customer>(getCustsByCustomerId().values());
	}

	// Consider adding another hash table for indexing by lastName
	@Override
	public List<Customer> findByLastName(String lastName) {
		List<Customer> results = new Vector<>();
		for(Customer c : getCustsByCustomerId().values()) {
			if (c.getLastName().equals(lastName)) {
				results.add(c);
			}
		}
		return results;
	}

	// Consider adding another hash table for indexing by email
	@Override
	public List<Customer> findByEmail(String email) {
		List<Customer> results = new Vector<>();
		for(Customer c : getCustsByCustomerId().values()) {
			if (c.getEmail().equals(email)) {
				results.add(c);
			}
		}
		return results;
	}

}
