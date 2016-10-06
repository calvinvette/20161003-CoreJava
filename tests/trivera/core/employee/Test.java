package trivera.core.employee;

import java.util.Iterator;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC.
 * http://www.triveratech.com   
 * </p>
 * @author Trivera Technologies Tech Team.
 */

public class Test {

    public static void main(String[] args) {
    	System.out.println(Person.getNumberOfPeople());
    	System.out.println("LAST PERSON: " + Person.getLastPersonName());
    	
    	
    	
        Person pers = null;
        Employee emp = null;

        // Instantiate a person and an employee, using the two variables
        // supplied
        pers = new Person("Jennifer");
        try {
			emp = new Employee("Bob", 10);
		} catch (PersonNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("LAST PERSON: " + Person.getLastPersonName());

//        System.out.println("Person   : " + pers);
//        System.out.println("Employee : " + emp);
        Person pers2 = new Person("Bob");
        pers2.name = "Bobarella";

//        System.out.println("Bob is Jennifer? " + pers.equals(emp));
//        System.out.println("Bob(Pers) is Bob(Emp)? " + pers2.equals(emp));

        Employee emp2 = null;
        Employee emp3 = null;
		try {
			emp2 = new Employee("Jennifer(Emp)", 12);
			emp3 = new Employee("Bob", 10);
		} catch (PersonNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        System.out.println("Is Jennifer(emp) same as Jennifer(pers)? " + emp2.equals(pers));
//        System.out.println("Is Jennifer(pers) same as Jennifer(emp)? " + pers.equals(emp2));
//        System.out.println("Is Bob(emp3) same as Bob(emp)? " + emp3.equals(emp));
        
        Person[ ] peeps = new Person[6];
        peeps[0] = emp3;
        peeps[1] = emp2;
        peeps[5] = pers;
        peeps[4] = pers;
        
        for (Person person : peeps) {
        	if (person != null) {
        		System.out.println(person);
        		if (person instanceof Employee) {
//        			Employee eTmp = (Employee) person;
//        			System.out.println(eTmp.getEmpID());
        			System.out.println(((Employee) person).getEmpID());
        		} else {
        			System.out.println("You ain't an Employee!");
        		}
        	} else {
        		System.out.println("No person in this slot.");
        	}
		}
        
        
        Person[ ] morePeeps = {
        	new Person(),
        	new Employee(),
        	emp2,
        	null,
        	new Person(),
        	emp3,
        	new Employee()
        };
        processPeople(morePeeps);
        processPeople2(emp, emp2, emp3, pers, pers2);
        processPeople2(emp, emp2, pers, pers2);
        
        
        // verify the version of Java that is running
//        System.out.println(System.getProperty("java.version"));
    }
    
    
    public static void processPeople(Person[] peeps) {
    	for (Person p : peeps) {
    		System.out.println(p);
    	}
    }

    public static void processPeople2(Person ... peeps) {
    	for (Person p : peeps) {
    		System.out.println(p);
    	}
    }

}