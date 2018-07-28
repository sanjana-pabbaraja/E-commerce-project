package com.amazon.market.model;

public class SubcategoryInfo {

	private long id;
	private Integer parentcatid;
	private String categoryName;	
	private String categoryDescription;

	

	public SubcategoryInfo() {

	}

	
	// Do not change this constructor,
	// it is used in the Hibernate Query
	public SubcategoryInfo(Long long1, String name, String pd) {
		this.id = long1;
		this.categoryName = name;
		
		this.categoryDescription = pd;
		

	}
	
	public SubcategoryInfo(Long long1,int parentid, String name, String pd) {
		this.id = long1;
		this.categoryName = name;
		this.parentcatid = parentid;
		this.categoryDescription = pd;
		

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getParentcatid() {
		return this.parentcatid;
	}

	public void setParentcatid(Integer parentcatid) {
		this.parentcatid = parentcatid;
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