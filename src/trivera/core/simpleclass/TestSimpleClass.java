package trivera.core.simpleclass;

public class TestSimpleClass {
	public static void main(String[] args) {
		SimpleClass sc = new SimpleClass();
		// System.out.println(sc);
		sc.setSalary(30000);
		sc.setName("Harry Potter");
		System.out.println(sc);

		SimpleClass sc2 = new SimpleClass("Harry Potter", 30000);
		System.out.println(sc2);
		if (sc2.equals(sc)) {
			// if (sc2 == sc) {
			System.out.println("Yup, they're the same.");
		} else {
			System.out.println("No, they're NOT the same.");
		}

//		String s2 = new String("hello");
		String s2 = "hello";
		String s = "hello"; // 

		if (s.equals(s2)) {
			System.out.println("Yup, they're the same.");
		} else {
			System.out.println("No, they're NOT the same.");
		}
	}
}
