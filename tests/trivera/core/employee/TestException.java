package trivera.core.employee;

public class TestException {

	public static void main(String[] args) {
		Person p = new Person();
		try {
			p.setName("Joe");
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		} catch (PersonNameException pne) {
			System.err.println(pne);
		} catch (RuntimeException e) {
			System.err.println("Caught in RuntimeException: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Caught in general Exception: " + e.getMessage());
		}
		System.out.println("Person: " + p);
		try {
			Employee e = new Employee("Hermione", 1234);
			System.out.println("Emp: " + e);
		} catch (PersonNameException pne) {
			System.err.println(pne);
//		} catch (Exception pne) {
//			System.err.println("Employee not instantiated!");
		} finally {
			System.out.println("Cleaning up anything from Employee handling.");
		}

		System.out.println("Done.");
	}

}
