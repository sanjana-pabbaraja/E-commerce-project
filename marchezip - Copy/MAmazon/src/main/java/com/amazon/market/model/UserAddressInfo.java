package com.amazon.market.model;

public class UserAddressInfo {

	int Userid;
	String Firstname;
	String Lastname;
	String Doorno;
	String Street;
	int Area;
	String City;
	String State;
	int Pincode;
	
	private boolean valid;

		public UserAddressInfo(){

    	}

	public UserAddressInfo(int Userid,String Firstname,String Lastname,String Doorno,String Street,int Area,String City,String State,int Pincode){
		this.Userid		=	Userid;
		this.Firstname	=	Firstname;
		this.Lastname	=	Lastname;
		this.Doorno		=	Doorno;
		this.Street		=	Street;
		this.Area		=	Area;
		this.City		=	City;
		this.State		=	State;
		this.Pincode	=	Pincode;
	}

	public int getUserid() {
		return Userid;
	}

	public void setUserid(int userid) {
		Userid = userid;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getDoorno() {
		return Doorno;
	}

	public void setDoorno(String doorno) {
		Doorno = doorno;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public int getArea() {
		return Area;
	}

	public void setArea(int area) {
		Area = area;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getPincode() {
		return Pincode;
	}

	public void setPincode(int pincode) {
		Pincode = pincode;
	}

	public boolean isValid() {
        return valid;
    }
 
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
	@Override
	public String toString() {
		return "" + Firstname + ", " + Lastname + ", "
				+ Doorno + ", " + Street + ", " + City + ", " + State
				+ ", " + Pincode + "";
	}
	
}
