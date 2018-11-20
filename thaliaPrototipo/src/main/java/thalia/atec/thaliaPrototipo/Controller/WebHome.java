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
		
		country.getCitys().add(new District("Aveiro"));
		country.getCitys().add(new District("Beja"));
		country.getCitys().add(new District("Beja"));
		country.getCitys().add(new District("Bragança"));
		country.getCitys().add(new District("Castelo Branco"));
		country.getCitys().add(new District("Coimbra"));
		country.getCitys().add(new District("Évora"));
		country.getCitys().add(new District("Faro"));
		country.getCitys().add(new District("Guarda"));
		country.getCitys().add(new District("Leiria"));
		country.getCitys().add(new District("Lisboa"));
		country.getCitys().add(new District("Portalegre"));
		country.getCitys().add(new District("Porto"));
		country.getCitys().add(new District("Santarém"));
		country.getCitys().add(new District("Setúbal"));
		country.getCitys().add(new District("Viana do Castelo"));
		country.getCitys().add(new District("Vila Real"));
		country.getCitys().add(new District("Viseu"));
		
		
		
		crep.save(country);
	    

		return "index.html";
		
		
		
	}
	
	
	
	
}
