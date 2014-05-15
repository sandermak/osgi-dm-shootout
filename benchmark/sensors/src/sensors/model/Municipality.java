package sensors.model;

import java.util.ArrayList;
import java.util.List;

public class Municipality {

	private String name;
	private List<City> cities = new ArrayList<>();
	
	public Municipality() {
	}
	
	public Municipality(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<City> getCities() {
		return cities;
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
		Municipality other = (Municipality) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
