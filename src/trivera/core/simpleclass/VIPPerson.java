package trivera.core.simpleclass;

import org.apache.derby.jdbc.ClientDriver;

import trivera.core.employee.Employee;
import trivera.core.employee.Person;
import trivera.core.employee.Test;


public class VIPPerson extends Person {
	private String vipID = "1234";
	private Employee[] contactsInTheCompany;
	private Test t;
//	private ClientDriver driver;
	
	public VIPPerson() {
	}

	public VIPPerson(String name) {
		this.name = name;
	}
	
	

}
