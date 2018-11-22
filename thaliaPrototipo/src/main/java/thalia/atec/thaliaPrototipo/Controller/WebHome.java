package thalia.atec.thaliaPrototipo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import thalia.atec.thaliaPrototipo.Service.CountryRepository;
import thalia.atec.thaliaPrototipo.model.District;
import thalia.atec.thaliaPrototipo.model.Country;

@Controller
public class WebHome {

	
	@Autowired
	CountryRepository crep;
	
	@GetMapping("/mockupdatacity")
	public String index() {
		
		Country country = new Country("Portugal");
		
		country.getDistricts().add(new District("Aveiro"));
		country.getDistricts().add(new District("Beja"));
		country.getDistricts().add(new District("Braga"));
		country.getDistricts().add(new District("Bragança"));
		country.getDistricts().add(new District("Castelo Branco"));
		country.getDistricts().add(new District("Coimbra"));
		country.getDistricts().add(new District("Évora"));
		country.getDistricts().add(new District("Faro"));
		country.getDistricts().add(new District("Guarda"));
		country.getDistricts().add(new District("Leiria"));
		country.getDistricts().add(new District("Lisboa"));
		country.getDistricts().add(new District("Portalegre"));
		country.getDistricts().add(new District("Porto"));
		country.getDistricts().add(new District("Santarém"));
		country.getDistricts().add(new District("Setúbal"));
		country.getDistricts().add(new District("Viana do Castelo"));
		country.getDistricts().add(new District("Vila Real"));
		country.getDistricts().add(new District("Viseu"));
		
		
		
		crep.save(country);
	    

		return "index.html";
		
		
		
	}
	
	
	
	
}
