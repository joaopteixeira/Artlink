package thalia.atec.thaliaPrototipo.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

public class Category {
	
	
	
	@Id
	private String id;
	
	private String description;
	private ArrayList<Category> subCategory;
	
	
	public Category(String description) {
		super();
		this.description = description;
		this.subCategory = new ArrayList<>();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Category> getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(ArrayList<Category> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	
	

}
