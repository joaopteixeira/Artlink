package thalia.atec.thaliaPrototipo.Functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import thalia.atec.thaliaPrototipo.Service.CategoryRepository;
import thalia.atec.thaliaPrototipo.model.Category;

public class FCategories {
	
	
	@Autowired
	static
	CategoryRepository catrep;
	
	
	
	public static List<Category> getCategories() {
		
		
		
		
		return catrep.findAll();
		

	}


}
