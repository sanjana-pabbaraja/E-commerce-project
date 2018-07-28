package com.amazon.market.model;

public class CategoryInfo {

	private long id;
	private String categoryName;
	
	private String categoryDescription;

	

	public CategoryInfo() {

	}

	
	// Do not change this constructor,
	// it is used in the Hibernate Query
	public CategoryInfo(Long long1, String name, String pd) {
		this.id = long1;
		this.categoryName = name;
		
		this.categoryDescription = pd;
		

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getcategoryName() {
		return categoryName;
	}

	public void setcategoryName(String name) {
		this.categoryName = name;
	}

	public String getcategoryDescription() {
		return categoryDescription;
	}

	public void setcategoryDescription(String email) {
		this.categoryDescription = email;
	}

	

}