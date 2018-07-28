package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.model.CartInfo;
import com.amazon.market.model.OrderDetailInfo;
import com.amazon.market.model.OrderInfo;
import com.amazon.market.model.PaginationResult;

public interface OrderDAO {

	public void saveOrder(CartInfo cartInfo);

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

	public List<OrderInfo> listOrderInfoByEmail(int page, int maxResult, int maxNavigationPage,String email);
	
	public List<OrderInfo> listOrderInfoByOwner(int page, int maxResult, int maxNavigationPage,int owner);
	
	public List<OrderInfo> listOrderInfoByDeliverBoy(int deliveryboy,String status );

	public OrderInfo getOrderInfo(String orderId);

	//public void saveConfirmOrder(OrderInfo order,int status);

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
	
	public List<OrderDetailInfo> listReceivedOrderDetailInfos(String orderId,int uid);
	
	public List<OrderDetailInfo> listDeliverOrderDetailInfos(String orderId,int uid);
	public int updateOrdStatus(int orderid,int status,String pids);
	

}