package com.amazon.market.entity;
// Generated Mar 31, 2018 4:39:20 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name = "message")
public class Message implements java.io.Serializable {
	private static final long serialVersionUID = -2054386655979281969L;
	private int id;
	private Account sender;
	private Account recipient;
	private String message;
	private Date updateTime;
	private Products product;

	public Message() {
	}

	public Message(int id, Account sender, Account recipient, String message, Date updateTime, Products product) {
		this.id=id;
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.updateTime = updateTime;
	this.product=product;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	@OneToOne
	@JoinColumn(name = "Sender", nullable = false)
	public Account getSender() {
		return this.sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	@OneToOne
	@JoinColumn(name = "Recipient", nullable = false)
	public Account getRecipient() {
		return this.recipient;
	}

	public void setRecipient(Account recipient) {
		this.recipient = recipient;
	}

	@Column(name = "Message", nullable = false,length=999)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_Time", nullable = false)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@OneToOne
	@JoinColumn(name = "Product", nullable = false)
	public Products getProduct() {
		return this.product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", reciptent=" + recipient + ", message=" + message
				+ ", updateTime=" + updateTime + "]";
	}

}
