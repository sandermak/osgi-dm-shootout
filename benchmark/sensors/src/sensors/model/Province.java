package sensors.model;

import java.util.ArrayList;
import java.util.List;

public class Province {

	private String name;
	private List<Municipality> municipalities = new ArrayList<>();
	
	public Province() {
	}
	
	public Province(String name) {
		this.name = name;
	}
	
	public List<Municipality> getMunicipalities() {
		return municipalities;
	}
	
	public String getName() {
		return name;
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
		Province other = (Province) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
