package trivera.core.employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import trivera.core.simpleclass.VIPPerson;

public class TestCollections {

	public static void main(String[] args) {
//		Vector<Person> peepsV = new Vector<>();
//		ArrayList<Person> peepsA = new ArrayList<>();
//		List<Person> peepsAL = new ArrayList<>();
		
//		List<Person> peeps = new LinkedList<>();
		boolean userSaysSort = false;
		Set<Person> peeps;
		if (userSaysSort) {
			peeps = new TreeSet<>(new SortByNameComparator());
		} else {
			peeps = new HashSet<>();
		}
//		Set<Person> peeps = new HashSet<>();
		
		
		Person draco1 = new Person("Draco");
		Person draco2 = new Person("Draco");
		peeps.add(new Person("Harry"));
		peeps.add(new Person("Ron"));
		peeps.add(new Employee("Hermione", 1234));
		peeps.add(new Person("Neville"));
		peeps.add(new Person("Seamus"));
		peeps.add(new Person("Dean"));
		peeps.add(new VIPPerson("Ginny"));
		peeps.add(draco1);
		peeps.add(draco2);
//		
		for (Person person : peeps) {
			System.out.println(person);
		}
		
//		System.exit(0);
		
		Map<String, Person> peepHash = new HashMap<>();
		peepHash.put("Harry", new Person("Harry"));
		peepHash.put("Ron", new Person("Ron"));
		peepHash.put("Hermione", new Employee("Hermione", 1234));
		peepHash.put("Neville", new Person("Neville"));
		peepHash.put("Seamus", new Person("Seamus"));
		peepHash.put("Dean", new Person("Dean"));
		peepHash.put("Ginny", new VIPPerson("Ginny"));
		Person d = new VIPPerson("Draco");
		peepHash.put(d.getName(), d);
		peepHash.put("draco@malfoy.co.uk", d);
		
		System.out.println();
		System.out.println("Ginny By Hash: " + peepHash.get("Ginny"));
		peepHash.put("Ginny", new VIPPerson("Ginny Weasley-Potter"));
		System.out.println("Ginny By Hash: " + peepHash.get("Ginny"));
		
		System.out.println();
		for (String key : peepHash.keySet()) {
			System.out.println(peepHash.get(key));
		}
		System.out.println();
		for (Person p : peepHash.values()) {
			System.out.println(p);
		}
		System.out.println();
		for (Entry<String, Person> entry : peepHash.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		
//		Person neville = peeps.get(3);
//		System.out.println(neville);
//		System.out.println();
//		for(int i = 0; i < peeps.size(); i++) {
//			System.out.println("#" + (i+1) + ": " + peeps.get(i));
//		}
//		System.out.println();
////		Person ginny = peeps.get(6);
////		ginny.setName();
//		peeps.set(6, new Person("Ginny Weasley-Potter") );
//		
//		Person anotherGinny = peeps.get(6);
//		System.out.println(anotherGinny);
//		
//		peeps.remove(4);
//		System.out.println();
//		for(int i = 0; i < peeps.size(); i++) {
//			System.out.println("#" + (i+1) + ": " + peeps.get(i));
//		}
	}

}
