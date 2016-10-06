package trivera.core.simpleclass;

public class SimpleClass {
	private int salary;
	private String name;

	public SimpleClass() {
		this("John Doe");
	}

	public SimpleClass(String name) {
		this(name, 42000);
	}

	public SimpleClass(String name, int salary) {
		this.salary = salary;
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// return "SimpleClass [salary=" + salary + ", name=" + name + "]";
		return getName() + " is making $" + getSalary();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + salary;
		return result;
	}

	// if (sc1.equals(sc2))
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleClass other = (SimpleClass) obj;
		if (name.equals(other.name) && salary == other.salary) {
			return true;
		} else {
			return false;
		}
	}

}
