package trivera.core.employee;

/**
 * <p>
 * This component and its source code representation are copyright protected and
 * proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera
 * Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of Trivera
 * Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC. http://www.triveratech.com
 * </p>
 * 
 * @author Trivera Technologies Tech Team.
 */

public class Person extends Object {
	// The name private instance field
	private static int numberOfPeople = 0;
	protected String name = "No Name"; // default access == "package", protected == this package or derivative

	// init blocks get called in each CTOR
	// Do this when you want the same code to run in all CTORs
	{
//		System.out.println("init block!");
		Person.numberOfPeople++;
//		Person.setLastPerson(this);
	}

	public Person() {
		Person.numberOfPeople++;
		Person.setLastPerson(this);
	}

	public Person(String name) {
		// Saves the name argument into the instance field
		super();
		this.name = name;
//		System.out.println("CTOR!");
		Person.setLastPerson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	// trivera.core.employee.Person@293814d
	@Override
	public String toString() {
		// return the name of the Person
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws PersonNameException  {
		String msg = "Null Names are NOT allowed!";
		PersonNameException pne = null;
		if (name == null) {
			if (this instanceof Employee) {
				pne = new PersonNameException(((Employee) this).getEmpID(), msg, null);
			} else {
				pne = new PersonNameException(msg);
			}
			throw pne;
		}
		boolean force = true;
		if (force) {
			throw new RuntimeException("Forced Error!");
		}
		this.name = name;
	}

	public static int getNumberOfPeople() {
		return numberOfPeople;
	}
	
	private static String lastPersonName = null;
	public static String getLastPersonName() {
		return lastPersonName;
	}
	public static void setLastPerson(Person p) {
		Person.lastPersonName = p.getName();
	}
	

	public static void setNumberOfPeople(int numberOfPeople) {
		Person.numberOfPeople = numberOfPeople;
	}

}
