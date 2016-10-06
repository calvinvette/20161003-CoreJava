package trivera.core.employee;

public class PersonNameException extends EmployeeException {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonNameException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(int empId, String name, String message, Throwable cause) {
		super(empId, message, cause);
		setName(name);
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(int empId, String message, Throwable cause) {
		super(empId, message, cause);
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PersonNameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Name Exc: " + super.toString();
	}

}
