package com.amazon.market.dao;

import com.amazon.market.entity.OrderStatus;

public interface OrderStatusDAO {
 
    public OrderStatus getOrderStatus(int id);
    
    public OrderStatus getSystemStatus(String status);
    
}