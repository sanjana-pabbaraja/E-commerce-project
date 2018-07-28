package com.amazon.market.entity;
 
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
 
    private static final long serialVersionUID = -2054386655979281969L;
 
      
    public static final String ROLE_REGISTEREDUSER = "REGISTEREDUSER";
    public static final String ROLE_ADMIN = "ADMIN";
    
    private int id;
    private String userName;
    private String password;
    private boolean active;
    private String userRole;
    private String email;
    private String mobileno;
 
    public Account() {
	}

	public Account(String username, boolean active, String password, String userrole,String email,String phone) {
		this.userName = username;
		this.active = active;
		this.password = password;
		this.userRole = userrole;
		this.email = email;
		this.mobileno = phone;
	}
    
    @Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
    @Column(name = "User_Name", length = 20, nullable = false)
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    @Column(name = "Password", length = 20, nullable = false)
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    @Column(name = "Active", length = 1, nullable = false)
    public boolean isActive() {
        return active;
    }
 
    public void setActive(boolean active) {
        this.active = active;
    }
 
    @Column(name = "User_Role", length = 20, nullable = false)
    public String getUserRole() {
        return userRole;
    }
 
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    @Column(name = "Email", length = 20, nullable = false)
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "Mobileno", length = 20, nullable = false)
    public String getMobileno() {
        return mobileno;
    }
 
    public void setMobileno(String phone) {
        this.mobileno = phone;
    }
    
    @Override
    public String toString()  {
        return "["+ this.userName+","+ this.password+","+ this.userRole+","+ this.email+","+ this.mobileno+"]";
    }
    
}