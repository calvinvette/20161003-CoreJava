package trivera.core.employee;

public class EmployeeException extends Exception {
	private int empId = -1;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public EmployeeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(int empId, String message, Throwable cause) {
		super(message, cause);
		setEmpId(empId);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Trivera Employee Exception: " + super.toString();
	}
}
