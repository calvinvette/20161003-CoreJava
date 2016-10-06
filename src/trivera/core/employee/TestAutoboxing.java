package trivera.core.employee;

public class TestAutoboxing {

	public static void main(String[] args) {
		int x = 4;
		Integer y = new Integer(4); // auto boxing/unboxing
		if (x == y) {
			System.out.println("same");
		} else {
			System.out.println("Not same!");
		}
		System.out.println(addValues(new Integer(7), 9));
		System.out.println(addValues(7, 9));
		System.out.println(addValues(7, new Integer(9)));
		System.out.println(addValues(new Integer(7), new Integer(9)));
		if (y.equals(x)) {
			System.out.println("same");
		} else {
			System.out.println("Not same!");
		}
		float pi = 3.14159265F;
		double piDouble = 3.14159265;
		long foo = 132454245245L;
		Long bar= 132454245245L; // new Long(132454245245L);
		printObject(foo);
	}
	
	public static void printObject(Object o) {
		System.out.println(o.toString());
	}
	
	public static Integer addValues(Integer a, int b) {
		return a + b;
	}

}
