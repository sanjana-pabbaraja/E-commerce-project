package com.amazon.market.dao.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.DeliverypersonZoneDAO;
import com.amazon.market.entity.DeliverypersonZone;



 
// Transactional for Hibernate
@Transactional
public class DeliverypersonZoneDAOImpl implements DeliverypersonZoneDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public DeliverypersonZone getDeliverypersonZone(int id ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(DeliverypersonZone.class);
        crit.add(Restrictions.eq("userid", id));
        return (DeliverypersonZone) crit.uniqueResult();
    }
    
    
    
	
 
}