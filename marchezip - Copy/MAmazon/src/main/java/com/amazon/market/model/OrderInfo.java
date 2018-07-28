package com.amazon.market.model;
 
import java.util.Date;
import java.util.List;
 
public class OrderInfo {
 
    private String id;
    private Date orderDate;
    private int orderNum;
    private int confirmOrder;
    private double amount;
    private String systemStatus;
 
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
 
    private List<OrderDetailInfo> details;
 
    public OrderInfo() {
 
    }
 
    // Using for Hibernate Query.
    public OrderInfo(String id, Date orderDate, int orderNum, //
            double amount, String customerName, String customerAddress, //
            String customerEmail, String customerPhone,int confirmorder) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.amount = amount;
 
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.confirmOrder=confirmorder;
    }
    
    public OrderInfo(String id, Date orderDate, int orderNum, //
            double amount, String customerName, String customerAddress, //
            String customerEmail, String customerPhone,int confirmorder,String systemStatus) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.amount = amount;
 
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.confirmOrder=confirmorder;
        this.systemStatus = systemStatus;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
 
    public String getCustomerName() {
        return customerName;
    }
 
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
 
    public String getCustomerAddress() {
        return customerAddress;
    }
 
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
 
    public String getCustomerEmail() {
        return customerEmail;
    }
 
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
 
    public String getCustomerPhone() {
        return customerPhone;
    }
 
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
 
    public List<OrderDetailInfo> getDetails() {
        return details;
    }
 
    public void setDetails(List<OrderDetailInfo> details) {
        this.details = details;
    }
    public int getConfirmOrder() {
    	return this.confirmOrder;
    }
    public void setConfirmOrder(int confirm) {
        this.confirmOrder = confirm;
    }
    
    public String getSystemStatus() {
        return systemStatus;
    }
 
    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }
 
}