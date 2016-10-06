package trivera.core.simpleclass;

public class TestLoops {
	public static void main(String[] args) {
		for (int idx = 0; idx < args.length; idx++) {
			System.out.println("For Loop Argument #" + (idx + 1) 
					+ "=" + args[idx]);
			if (args[idx].equals("uno")) {
				continue;
			}
			if (args[idx].equals("dos")) {
				break;
			}
			System.out.println("End of For Loop iteration");
		}
		System.out.println("For loop complete");
		int idx = 0;
		while (idx < args.length) {
			System.out.println("While loop Argument #" + (idx + 1) 
					+ "=" + args[idx]);
			idx++;
		}
		idx--;
		do {
			System.out.println("Do loop Argument #" + (idx + 1) 
					+ "=" + args[idx]);
			idx--;
		} while (idx >= 0);
		
	}
}
