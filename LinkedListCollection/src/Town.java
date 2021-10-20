
public class Town implements Comparable<Town>{

	private String name;
	private int km;
	
	public Town(String name, int km) {
		this.name = name;
		this.km = km;
	}
	
	public Town() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + km;
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
		Town other = (Town) obj;
		if (km != other.km)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Town other) {
		if(this.km < other.km) {
			return -1;
		} else if(this.km > other.km) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return this.name + " " + this.km;
	}

}
