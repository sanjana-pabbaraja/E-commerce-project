package com.amazon.market.model;
 
public class OrderDetailInfo {
    

	private String id;
 
    private Long productId;
    private String productName;
    private String productStatus;
    private String systemStatus;
    private int statusId;
    private String orderId;
 
    private int quanity;
    private double price;
    private double amount;
 
    public OrderDetailInfo() {
 
    }
 
    // Using for Hibernate Query.
  
    public OrderDetailInfo(String id, Long productId, //
            String productName, int quanity, double price, double amount) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quanity = quanity;
        this.price = price;
        this.amount = amount;
        
    }
    public OrderDetailInfo(String id, Long productId, //
            String productName, int quanity, double price, double amount,String orderId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quanity = quanity;
        this.price = price;
        this.amount = amount;
        this.orderId = orderId;
    }
    
    public OrderDetailInfo(String id, Long productId, //
            String productName, int quanity, double price, double amount,String pStatus,String sStatus,int statusId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quanity = quanity;
        this.price = price;
        this.amount = amount;
        this.productStatus = pStatus;
        this.systemStatus = sStatus;
        this.statusId = statusId;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public Long getProductId() {
        return productId;
    }
 
    public void setProductId(Long productId) {
        this.productId = productId;
    }
 
    public String getProductName() {
        return productName;
    }
 
    public void setProductName(String productName) {
        this.productName = productName;
    }
 
    public int getQuanity() {
        return quanity;
    }
 
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getProductStatus() {
        return productStatus;
    }
 
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
    
    public String getSystemStatus() {
        return systemStatus;
    }
 
    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }
    
    public int getStatusId() {
        return statusId;
    }
 
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    @Override
	public String toString() {
		return "OrderDetailInfo [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productStatus=" + productStatus + ", systemStatus=" + systemStatus + ", statusId=" + statusId
				+ ", quanity=" + quanity + ", price=" + price + ", amount=" + amount + "]";
	}
}