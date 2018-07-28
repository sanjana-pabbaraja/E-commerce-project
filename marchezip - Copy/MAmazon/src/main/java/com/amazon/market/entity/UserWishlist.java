package com.amazon.market.entity;
// Generated Apr 12, 2018 5:40:29 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserWishlist generated by hbm2java
 */
@Entity
@Table(name = "User_Wishlist")
public class UserWishlist implements java.io.Serializable {

	

	private Integer id;
	//private int userid;
	//private int productid;
	private Date lastupdated;
	
	private Account user;
    private Products product;

    public UserWishlist() {
	}

	public UserWishlist(Account userid, Products productid, Date lastupdated) {
		this.user = userid;
		this.product = productid;
		this.lastupdated = lastupdated;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Userid", nullable = false, //
    foreignKey = @ForeignKey(name = "USER_DETAIL_FK"))
	//@Column(name = "Userid", nullable = false)
	public Account getUser() {
		return this.user;
	}

	public void setUser(Account userid) {
		this.user = userid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Productid", nullable = false, //
    foreignKey = @ForeignKey(name = "PRODUCT_DETAIL_FK"))
	//@Column(name = "Productid", nullable = false)
	public Products getProduct() {
		return this.product;
	}

	public void setProduct(Products productid) {
		this.product = productid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Lastupdated", nullable = false, length = 19)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	@Override
	public String toString() {
		return "UserWishlist [id=" + id + ", lastupdated=" + lastupdated + ", userid=" + user.getId() + ", productid="
				+ product.getId() + "]";
	}

}
