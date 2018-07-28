package com.amazon.market.model;

import java.util.Date;

import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products;

public class MessageInfo {

	private int id;
	private Account sender;
	private Account recipient;
	private String message;
	private Date updateTime;
	private Products product;
	 
    public MessageInfo() {
    }
    
    public MessageInfo(int id,Account sender, Account recipient, String message, Date updateTime,Products product) {
    	this.id = id;
    	this.sender = sender;
		this.recipient=recipient;
		this.message = message;
		this.updateTime = updateTime;
		this.product=product;
		
    }
    
    public MessageInfo(Account sender, Account recipient, Products product) {
    	this.sender = sender;
		this.recipient=recipient;
		this.product=product;
    }
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getRecipient() {
		return recipient;
	}

	public void setRecipient(Account recipient) {
		this.recipient = recipient;
	}
	
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setMessage(Date updateTime) {
		this.updateTime =updateTime;
	}
	public Products getProduct() {
		return this.product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "MessageInfo [id=" + id + ", sender=" + sender + ", recipient=" +recipient + ", message=" + message
				+ ", updateTime=" + updateTime + "]";
	}

}
