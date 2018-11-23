package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FCategory;
import thalia.atec.thaliaPrototipo.model.Category;

@RestController
@RequestMapping("mcategory")
public class RestCategory {

	
	@Autowired
	FCategory fcategory;
	
	
	
	@GetMapping("/get")
	public List<Category> getCategorys(){
		
		return fcategory.getCategorys();
	}
	
	/*
	@GetMapping("/mockupdata")
	public void mockupdata(){
		
		fcategory.mockupdata();
	}*/
}
