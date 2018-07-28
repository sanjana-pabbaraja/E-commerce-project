package com.amazon.market.entity;
 
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable {
 
    private static final long serialVersionUID = 7550745928843183535L;
 
    private String id;
    
    private Order order;
    private Products product;
    private OrderStatus orderStatus;
    
    
    private int quanity;
    private double price;
    private double amount;
 
    @Id
	@GeneratedValue(strategy = IDENTITY)
    
    @Column(name = "Id", length = 50, nullable = false)
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Id", nullable = false, //
    foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK") )
    public Order getOrder() {
        return order;
    }
 
    public void setOrder(Order order) {
        this.order = order;
    }
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_Id", nullable = false, //
    foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK") )
    public Products getProduct() {
        return product;
    }
 
    public void setProduct(Products product) {
        this.product = product;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Status", nullable = false, //
    foreignKey = @ForeignKey(name = "ORDER_DETAIL_STATE_FK") )
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
 
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
 
    @Column(name = "Quanity", nullable = false)
    public int getQuanity() {
        return quanity;
    }
 
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
 
    @Column(name = "Price", nullable = false)
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    @Column(name = "Amount", nullable = false)
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}