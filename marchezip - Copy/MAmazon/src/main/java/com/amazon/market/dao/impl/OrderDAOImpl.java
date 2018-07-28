package com.amazon.market.dao.impl;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.amazon.market.dao.DeliverypersonZoneDAO;
import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.OrderStatusDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.entity.DeliverypersonZone;
import com.amazon.market.entity.Order;
import com.amazon.market.entity.OrderDetail;
import com.amazon.market.entity.OrderStatus;
import com.amazon.market.entity.Products;
import com.amazon.market.model.CartInfo;
import com.amazon.market.model.CartLineInfo;
import com.amazon.market.model.CustomerInfo;
import com.amazon.market.model.LocationzonesInfo;
import com.amazon.market.model.OrderDetailInfo;
import com.amazon.market.model.OrderInfo;
import com.amazon.market.model.PaginationResult;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.model.UserAddressInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.controller.*;
 
//Transactional for Hibernate
@Transactional
public class OrderDAOImpl implements OrderDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private OrderStatusDAO orderstatusDAO;
    
    @Autowired
    private LocationZonesDAO locationZonesDAO; 	
    
    @Autowired
    private DeliverypersonZoneDAO deliverypersonZoneDAO;
 
    private int getMaxOrderNum() {
        String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }
    
    public int updateOrdStatus(int orderid,int status,String pids) {
    	
    	String updatesql="UPDATE OrderDetail SET Order_Status="+status+" WHERE Order_Id="+orderid+"  and Product_Id in ("+pids+")";
    	Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(updatesql);		 
		System.out.println(pids);
		System.out.println(orderid);
		
		System.out.println(query.getQueryString());

	    int result = query.executeUpdate();
    	return result;
    }
    
    
   /* @Override
    public void saveConfirmOrder(OrderInfo orderinf,int status) {
    	sessionFactory.getCurrentSession().saveOrUpdate(this.findOrder(orderinf.getId()));
    	      
    	this.sessionFactory.getCurrentSession().persist(this.findOrder(orderinf.getId()));
    	
    	this.sessionFactory.getCurrentSession().createSQLQuery("UPDATE `Orders` SET `Confirm_Order` = '0' WHERE `Orders`.`Id` = '38c80850-32f5-44bf-b3ff-3d37f2e97066'");
    	this.sessionFactory.getCurrentSession().flush();
    	Session session3 = sessionFactory.openSession();
		Transaction tx3 = session3.beginTransaction();
		Order emp3 = this.findOrder(orderinf.getId());
		emp3.setConfirmOrder(status);
		System.out.println(emp3.getConfirmOrder());
		System.out.println(status);
		session3.saveOrUpdate(emp3);
		//emp3.setName("Kumar"); //will be saved into DB
		System.out.println("9. Before committing saveOrUpdate transaction. Id="+emp3.getId());
		tx3.commit();
		System.out.println("10. After committing saveOrUpdate transaction");
		System.out.println("*****");
		
    }*/
    
    @Override
    public void saveOrder(CartInfo cartInfo) {
        Session session = sessionFactory.getCurrentSession();
 
        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();
        System.out.println("this is save order");
        System.out.println(this.orderstatusDAO.getOrderStatus(1).toString());
        
 
        //order.setId(UUID.randomUUID().toString());
        
        
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());
        order.setOrderStatus(this.orderstatusDAO.getOrderStatus(1));
        //order.setOrderConfirmed(0);
        UserAddressInfo useraddressInfo = cartInfo.getUserAddressInfo();
        
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());
        order.setCustomerAddress(useraddressInfo.toString()+","+customerInfo.getPhone());
        
        session.persist(order);
 
        List<CartLineInfo> lines = cartInfo.getCartLines();
 
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            //detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setOrderStatus(this.orderstatusDAO.getOrderStatus(1));
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
 
            long code = line.getProductInfo().getId();
            Products product = this.productDAO.findProduct(code);
            detail.setProduct(product);
            
            session.persist(detail);
        }
 
        // Set OrderNum for report.
        // Set OrderNum Ä‘á»ƒ thÃ´ng bÃ¡o cho ngÆ°á»�i dÃ¹ng.
        cartInfo.setOrderNum(orderNum);
    }
 
    // @page = 1, 2, ...
    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
 
        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }
 
 // @page = 1, 2, ...
    public List<OrderInfo> listOrderInfoByEmail(int page, int maxResult, int maxNavigationPage,String email) {
    	List<OrderInfo> finalList=new ArrayList<OrderInfo>();
    	String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder,ord.orderStatus.systemStatus) " + " from "
                + Order.class.getName() + " ord where ord.customerEmail='"+email+"'"//
                + " order by ord.orderNum desc";
        
        
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        
        List<OrderInfo> orderinfolist = query.list();
 
        if(orderinfolist.size()==0) {
        	return finalList;
        }
        
        List<String> orderIdList = new ArrayList<String>();
        for (int i=0; i<orderinfolist.size(); i++){
        	orderIdList.add(""+orderinfolist.get(i).getOrderNum());
        }
        
        String orderIdListCommaSeparated = String.join(",", orderIdList);
        
       
        
        String sql2 = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.order.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id in ("+orderIdListCommaSeparated+")  order by d.order.id desc";
       
      // System.out.println(sql2);
        Query query1 = session.createQuery(sql2);
       
        //System.out.println(query1.list().get(0).toString());
        
        List<OrderDetailInfo> orderinfoList = query1.list();
        
       // System.out.println("orderinfoList");
       // System.out.println(orderinfoList.toString());
       // System.out.println(orderinfoList.size());
        
        if(orderinfoList.size()==0) {
        	return finalList;
        }
        
        List<String> ordersIdList = new ArrayList<String>();
        Map<String,String > orderinfoStatus = new HashMap<String,String>();
        OrderStatus orderStatus;
        for (int i=0; i<orderinfoList.size(); i++){
        	
        	OrderDetailInfo orderDetailObj = orderinfoList.get(i);
        	ordersIdList.add(""+orderDetailObj.getId());
        	
        	orderStatus = orderstatusDAO.getSystemStatus(orderDetailObj.getProductStatus());
        	if(orderStatus.getId()==1) {
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>1 && orderStatus.getId()<=4) {
        		
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>4) {
        		
        		       		
        		//if any one of the product status is not in-progress or order-placed then update the status
        		if(orderinfoStatus.get(orderDetailObj.getId())==null || (orderinfoStatus.size()>0 && !(orderinfoStatus.get(orderDetailObj.getId()).equals("In-Progress")||orderinfoStatus.get(orderDetailObj.getId()).equals("Order-Placed")))) {
        			orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        		}
        		
        	}
        }
        
        String ordersIdListCommaSeparated = String.join(",", ordersIdList);
        
        System.out.println("ordersIdList");
     //   System.out.println(ordersIdListCommaSeparated);
        
        String sql3 = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder,ord.orderStatus.systemStatus) " + " from "
                + Order.class.getName() + " ord where ord.id in ("+ordersIdListCommaSeparated+")  order by ord.orderNum desc"; //
        
        
      //  System.out.println(sql3);
        Query query2 = session.createQuery(sql3);
      //  System.out.println(" all products order info ");
        
             
        
       // System.out.println(" all products orders count "+query2.list().size());
      //  System.out.println(query2.list().get(0).toString());
 
        finalList = query2.list();
        
        
        //System.out.println("finalList");
        //System.out.println(finalList.toString());
       // System.out.println(finalList.size());
        
        
        if(finalList.size()==0) {
        	return finalList;
        }
        
        OrderInfo orderInfo;
        for(int i=0;i<finalList.size();i++){
            String tempOrderStatus = orderinfoStatus.get(finalList.get(i).getId());
            orderInfo = finalList.get(i);
            orderInfo.setSystemStatus(tempOrderStatus);
            finalList.set(i,orderInfo);
        } 
        return finalList;
        
        
        //return query.list();
        //return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }
    
    // @page = 1, 2, ...
    @Override
    public List<OrderInfo> listOrderInfoByOwner(int page, int maxResult, int maxNavigationPage,int Owner) {
    	List<OrderInfo> finalList=new ArrayList<OrderInfo>();



    	
    	String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder) " + " from "
                + Order.class.getName() + " ord where ord.id in (26,27,28) "; //
               // +" where orddt.product=46";
                //+ " order by ord.orderNum desc";
        
        System.out.println(sql);
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        
        
        /*String sql2 = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id =26 ";
        Query query1 = session.createQuery(sql2);*/
        
        List<ProductInfo> productinfolist = productDAO.listProductInfosByUid(Owner);
        
        
        if(productinfolist.size()==0) { return finalList; }
        
        List<String> productsIdList = new ArrayList<String>();
        for (int i=0; i<productinfolist.size(); i++){
        	productsIdList.add(""+productinfolist.get(i).getId());
        }
        
        String productsIdListCommaSeparated = String.join(",", productsIdList);


        System.out.println("listOrderInfoByOwner productsIdList");
        System.out.println(productsIdListCommaSeparated);

        String sql2 = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.order.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.product.id in ("+productsIdListCommaSeparated+")  order by d.order.id desc";
        
        System.out.println(sql2);
        Query query1 = session.createQuery(sql2);
        System.out.println("listOrderInfoByOwner 48 product order info ");
        //System.out.println(query1.list().get(0).toString());
        
        List<OrderDetailInfo> orderinfoList = query1.list();
        
        if(orderinfoList.size()==0) { return finalList; }
        
        List<String> ordersIdList = new ArrayList<String>();
        Map<String,String > orderinfoStatus = new HashMap<String,String>();
        OrderStatus orderStatus;
        for (int i=0; i<orderinfoList.size(); i++){
        	
        	OrderDetailInfo orderDetailObj = orderinfoList.get(i);
        	ordersIdList.add(""+orderDetailObj.getId());
        	
        	orderStatus = orderstatusDAO.getSystemStatus(orderDetailObj.getProductStatus());
        	if(orderStatus.getId()==1) {
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>1 && orderStatus.getId()<=4) {
        		
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>4) {
        		
        		//System.out.println("order id "+orderDetailObj.getId());
        		//if(orderinfoStatus.size()>0)
        		//System.out.println("order status "+orderinfoStatus.get(orderDetailObj.getId()));
        		
        		
        		//if any one of the product status is not in-progress or order-placed then update the status
        		if(orderinfoStatus.get(orderDetailObj.getId())==null || (orderinfoStatus.size()>0 && !(orderinfoStatus.get(orderDetailObj.getId()).equals("In-Progress")||orderinfoStatus.get(orderDetailObj.getId()).equals("Order-Placed")))) {
        			orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        		}
        		
        	}
        }


        
        String ordersIdListCommaSeparated = String.join(",", ordersIdList);
        
        System.out.println("ordersIdList");
        System.out.println(ordersIdListCommaSeparated);
        
        String sql3 = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder,ord.orderStatus.systemStatus) " + " from "
                + Order.class.getName() + " ord where ord.id in ("+ordersIdListCommaSeparated+")  order by ord.orderNum desc"; //
        
        
        System.out.println(sql3);
        Query query2 = session.createQuery(sql3);
        System.out.println(" all products order info ");
        //System.out.println(" all products orders count "+query2.list().size());
        //System.out.println(query2.list().get(0).toString());
 
        finalList = query2.list();
        
        if(finalList.size()==0) { return finalList; }
        
        OrderInfo orderInfo;
        for(int i=0;i<finalList.size();i++){
            String tempOrderStatus = orderinfoStatus.get(finalList.get(i).getId());
            orderInfo = finalList.get(i);
            orderInfo.setSystemStatus(tempOrderStatus);
            finalList.set(i,orderInfo);
        } 
        return finalList;
        //return new PaginationResult<OrderInfo>(query2, page, maxResult, maxNavigationPage);
    }
    
    public String getOrderedProductsByDeliveryBoy(int deliveryboyid) {
    	DeliverypersonZone deliverypersonZone=deliverypersonZoneDAO.getDeliverypersonZone(deliveryboyid);
    	 
        if(deliverypersonZone.getZoneid()<=0) {
        	return null;
        }
        List<LocationzonesInfo> locationslist = locationZonesDAO.listLocationsInfosByZone(deliverypersonZone.getZoneid());
		
        List<String> locationsIdList = new ArrayList<String>();
        for (LocationzonesInfo locationInfo : locationslist) {
        	locationsIdList.add(""+locationInfo.getId());
        }
        String locationsIdListCommaSeparated = String.join(",", locationsIdList);
				
        System.out.println(deliverypersonZone.toString());
        System.out.println(locationsIdListCommaSeparated);
        
       // return results;
        List<ProductInfo> productinfolist = productDAO.listProductInfosByLocation(locationsIdListCommaSeparated);
        
        List<String> productsIdList = new ArrayList<String>();
        for (int i=0; i<productinfolist.size(); i++){
        	productsIdList.add(""+productinfolist.get(i).getId());
        }
        
        String productsIdListCommaSeparated = String.join(",", productsIdList);


        System.out.println("productsIdList");
        System.out.println(productsIdListCommaSeparated);
        return productsIdListCommaSeparated;
    }
    // @page = 1, 2, ...
    @Override
 public List<OrderInfo> listOrderInfoByDeliverBoy(int deliveryboy,String status) {
        
    	List<OrderInfo> finalList=new ArrayList<OrderInfo>();
    	
        Session session = this.sessionFactory.getCurrentSession();
        
        String productsIdListCommaSeparated = getOrderedProductsByDeliveryBoy(deliveryboy);

        if(productsIdListCommaSeparated.isEmpty()) {
        	return finalList;
        }
        
        String sql2 = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.order.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.product.id in ("+productsIdListCommaSeparated+") and d.orderStatus.id in ("+status+")  order by d.order.id desc";
  
        System.out.println(sql2);
        Query query1 = session.createQuery(sql2);
        System.out.println(" 48 product order info listOrderInfoByDeliverBoy ");
        System.out.println(query1.list().size());
        if(query1.list().size()==0) {
        	return finalList;
        }
        List<OrderDetailInfo> orderinfoList = query1.list();
        
        List<String> ordersIdList = new ArrayList<String>();
        Map<String,String > orderinfoStatus = new HashMap<String,String>();
        OrderStatus orderStatus;
        for (int i=0; i<orderinfoList.size(); i++){
        	
        	OrderDetailInfo orderDetailObj = orderinfoList.get(i);
        	ordersIdList.add(""+orderDetailObj.getId());
        	
        	orderStatus = orderstatusDAO.getSystemStatus(orderDetailObj.getProductStatus());
        	if(orderStatus.getId()==1) {
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>1 && orderStatus.getId()<=4) {
        		
        		orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        	}
        	if(orderStatus.getId()>4) {
        		
        		//System.out.println("order id "+orderDetailObj.getId());
        		//if(orderinfoStatus.size()>0)
        		//System.out.println("order status "+orderinfoStatus.get(orderDetailObj.getId()));
        		
        		
        		//if any one of the product status is not in-progress or order-placed then update the status
        		if(orderinfoStatus.get(orderDetailObj.getId())==null || (orderinfoStatus.size()>0 && !(orderinfoStatus.get(orderDetailObj.getId()).equals("In-Progress")||orderinfoStatus.get(orderDetailObj.getId()).equals("Order-Placed")))) {
        			orderinfoStatus.put(orderDetailObj.getId(), orderStatus.getSystemStatus());
        		}
        		
        	}
        }


        
        String ordersIdListCommaSeparated = String.join(",", ordersIdList);
        
        System.out.println("ordersIdList");
        System.out.println(ordersIdListCommaSeparated);
        
        String sql3 = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.confirmOrder,ord.orderStatus.systemStatus) " + " from "
                + Order.class.getName() + " ord where ord.id in ("+ordersIdListCommaSeparated+")  order by ord.orderNum desc"; //
        
        
        
        
        System.out.println(sql3);
        Query query2 = session.createQuery(sql3);
        System.out.println(" all products order info ");
        System.out.println(" all products orders count "+query2.list().size());
        //System.out.println(query2.list().get(0).toString());
 
        finalList = query2.list();
        
        
        if(finalList.size()==0) {
        	return finalList;
        }
        
        OrderInfo orderInfo;
        for(int i=0;i<finalList.size();i++){
            String tempOrderStatus = orderinfoStatus.get(finalList.get(i).getId());
            orderInfo = finalList.get(i);
            orderInfo.setSystemStatus(tempOrderStatus);
            finalList.set(i,orderInfo);
        } 
        return finalList;
        
        
        
        //return query2.list();
        
        //return new PaginationResult<OrderInfo>(query2, page, maxResult, maxNavigationPage);
    }
    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }
 
    @Override
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        
        return new OrderInfo(order.getId(), order.getOrderDate(), //
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone(),order.getConfirmOrder());
    }
 
    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d  "//
                + " where d.order.id = :orderId order by d.id desc";
        System.out.println(sql);
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return query.list();
    }
    
    @Override
    public List<OrderDetailInfo> listReceivedOrderDetailInfos(String orderId,int uid) {
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId and d.product.owner = :uid  order by d.id desc";
        System.out.println(sql);
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
        query.setParameter("uid", uid);
 
        return query.list();
    }
    
    @Override
    public List<OrderDetailInfo> listDeliverOrderDetailInfos(String orderId,int uid) {
    	
    	String productsIdListCommaSeparated = getOrderedProductsByDeliveryBoy(uid);
    	
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.id, d.product.productName , d.quanity,d.price,d.amount,d.orderStatus.orderStatus,d.orderStatus.systemStatus,d.orderStatus.id) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId and d.product.id in  ("+productsIdListCommaSeparated+")  order by d.id desc ";
        System.out.println(sql);
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
        
 
        return query.list();
    }
 
}