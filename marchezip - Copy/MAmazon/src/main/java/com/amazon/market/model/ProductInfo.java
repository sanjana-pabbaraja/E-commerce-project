package com.amazon.market.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazon.market.entity.LocationZones;
import com.amazon.market.entity.Products;

public class ProductInfo {

	private long id;
	private String name;
	private long categoryId;
	private long subcategoryId;
	private String productDescription;
	private int location;
	private long owner;
	private double price;

	private CommonsMultipartFile image;
	
	private boolean availability;
	private Date addedTime;

	public ProductInfo() {

	}

	/*
	 * ProductInfo(applicant.getId(), applicant.getProductName(), //
	 * applicant.getCategoryId(), applicant.getProductDescription(), //
	 * applicant.getPrice(), null);
	 */
	// Do not change this constructor,
	// it is used in the Hibernate Query
	public ProductInfo(Long long1, String name, long l, String pd, double i, byte[] skillsString) {
		this.id = long1;
		this.name = name;
		this.categoryId = l;
		this.productDescription = pd;
		this.price = i;
		//this.image = skillsString;
	}
	
	//get product constructor
	public ProductInfo(Long long1, String name, long cat,long subcat,int loc, String pd, double price,boolean availability,long owner) {
		this.id = long1;
		this.name = name;
		this.categoryId = cat;
		this.subcategoryId = subcat;
		this.location = loc;
		this.productDescription = pd;
		this.price = price;
		this.availability = availability;
		this.owner=owner;
	}
	
	public ProductInfo(Long long1) {
		this.id = long1;
	}
	
	
	//save product constructor
	public ProductInfo(Long long1, String name, long l, String pd, double i, byte[] skillsString, boolean flag,Date date) {
		this.id = long1;
		this.name = name;
		this.categoryId = l;
		this.productDescription = pd;
		this.price = i;
		this.availability = flag;
		this.addedTime = date;

	}

	public ProductInfo(Products product) {
        this.id = product.getId();
        this.name = product.getProductName();
        this.categoryId = product.getCategoryId();
        this.productDescription=product.getProductDescription();
        this.price = product.getPrice();
        this.subcategoryId = product.getSubCategoryId();
        //this.image = product.getProductImage();
    }

	
	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getcategoryId() {
		return categoryId;
	}

	public void setcategoryId(long cid) {
		this.categoryId = cid;
	}
	
	public long getsubcategoryId() {
		return subcategoryId;
	}

	public void setsubcategoryId(long cid) {
		this.subcategoryId = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getproductDescription() {
		return productDescription;
	}

	public void setproductDescription(String email) {
		this.productDescription = email;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double position) {
		this.price = position;
	}

	public CommonsMultipartFile getimage() {
		return image;
	}

	public void setimage(CommonsMultipartFile fileData) {
		this.image = fileData;
	}
	
	public boolean getAvailability() {
		return this.availability;
	}

	public void setAvailability(boolean flag) {
		this.availability = flag;
	}
	
	public int getLocation() {
		return this.location;
	}

	public void setLocation(int loc) {
		this.location = loc;
	}
	
	public Date getAddedTime() {
		return this.addedTime;
	}

	public void setAddedTime(Date date) {
		this.addedTime = date;
	}
	
	public String toString() {
		return this.id+" "+this.name+" "+this.categoryId+" "+this.productDescription+" "+this.price+" "+this.owner;
	}
	

}