package com.amazon.market.dao.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.OrderStatusDAO;
import com.amazon.market.entity.OrderStatus;

 
// Transactional for Hibernate
@Transactional
public class OrderStatusDAOImpl implements OrderStatusDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public OrderStatus getOrderStatus(int id ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(OrderStatus.class);
        crit.add(Restrictions.eq("id", id));
        return (OrderStatus) crit.uniqueResult();
    }
    


    @Override
    public OrderStatus getSystemStatus(String status ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(OrderStatus.class);
        crit.add(Restrictions.eq("orderStatus", status));
        return (OrderStatus) crit.uniqueResult();
    }

}