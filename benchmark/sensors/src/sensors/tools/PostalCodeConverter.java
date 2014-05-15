package sensors.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import sensors.model.City;
import sensors.model.Municipality;
import sensors.model.Province;

public class PostalCodeConverter {

	public static void main(String[] args) throws IOException {
		// municipality, city, postalcode
		List<Province> provinces = new ArrayList<Province>();
		int linecount = 0;
		File csv = new File("/Users/uiterlix/Downloads/postcode_NL.csv");
		BufferedReader reader = new BufferedReader(new FileReader(csv));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(";");
			String postalCode = parts[1].replaceAll("\"", "");
			City city = new City(parts[9].replaceAll("\"", ""));
			Municipality municipality = new Municipality(parts[11].replaceAll("\"", ""));
			Province province = new Province(parts[13].replaceAll("\"", ""));
			
			if (!provinces.contains(province)) {
				provinces.add(province);
			} else {
				province = provinces.get(provinces.indexOf(province));
			}
			
			if (!province.getMunicipalities().contains(municipality)) {
				province.getMunicipalities().add(municipality);
			} else {
				municipality = province.getMunicipalities().get(province.getMunicipalities().indexOf(municipality));
			}
			
			if (!municipality.getCities().contains(city)) {
				municipality.getCities().add(city);
			} else {
				city = municipality.getCities().get(municipality.getCities().indexOf(city));
			}
			
			if (!city.getPostalCodes().contains(postalCode)) {
				city.getPostalCodes().add(postalCode);
			} else {
				// ignore odd postal code part
			}
			
			if (municipality.equals("Voorst")) {
				System.out.println(postalCode + ", " + city + ", " + municipality + ", " + province);
			}
			linecount ++;
		}
		System.out.println("Provinces: " + provinces.size());
		reader.close();
		
		// write as JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.defaultPrettyPrintingWriter().writeValue(new File("/Users/uiterlix/temp/postalcodes.json"), provinces);

	}

}
