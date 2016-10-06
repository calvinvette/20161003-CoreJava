package trivera.core.employee;

import java.util.Comparator;

public class SortByNameComparator implements Comparator<Person> {
	public int compare(Person o1, Person o2) {
//		if (o1.getName() > o2.getName()) {
//			return 1;
//		} else if (o1.getName() < o2.getName()) {
//			return -1;
//		}
		
		return o1.getName().compareTo(o2.getName());
		
	};
}
