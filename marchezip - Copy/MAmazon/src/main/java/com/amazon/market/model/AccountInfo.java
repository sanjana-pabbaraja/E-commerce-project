package com.amazon.market.model;


public class AccountInfo {

	private int id;
	private String uname;
	private boolean active;
	private String password;
	private String userrole;
	private String email;
    private String mobileno;
	
	 private boolean valid;
	 
    public AccountInfo() {
    }
    
    
    public AccountInfo(int id,String name,String email,String phone) {
    	this.id = id;
    	this.uname = name;
    	this.email = email;
    	this.mobileno = phone;
    }
    
    
    public AccountInfo(int id,String name,boolean activeflag,String pass,String urole,String email,String phone) {
    	this.id = id;
    	this.uname = name;
		this.active = activeflag;
		this.password = pass;
		this.userrole = urole;
		this.email = email;
		this.mobileno = phone;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String userName) {
		this.uname = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userRole) {
		this.userrole = userRole;
	}
	
	public boolean isValid() {
        return valid;
    }
 
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileno() {
        return mobileno;
    }
 
    public void setMobileno(String phone) {
        this.mobileno = phone;
    }
    
    @Override
    public String toString()  {
        return "["+ this.uname+","+ this.password+","+ this.userrole+","+ this.email+","+ this.mobileno+"]";
    }
    
}
