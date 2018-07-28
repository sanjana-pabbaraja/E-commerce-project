package com.amazon.market.dao.impl;
 
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.entity.Category;
import com.amazon.market.entity.LocationZones;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.LocationzonesInfo;



 
// Transactional for Hibernate
@Transactional
public class LocationZonesDAOImpl implements LocationZonesDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public LocationZones getLocationZone(int id ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(LocationZones.class);
        crit.add(Restrictions.eq("id", id));
        return (LocationZones) crit.uniqueResult();
    }
    
    public  int listLocationidByname(String loc)
    {
    	String sql = "Select new " + LocationzonesInfo.class.getName()//
                + "(a.id, a.locationName, a.zoneid) "//
                + " from " + LocationZones.class.getName() + " a where a.locationName like '%"+loc+"%' ";
        System.out.println(" List LocationzonesInfo ");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        
        List<LocationzonesInfo> list = query.list();
        if(list.size()==0) {
        	return 0;
        }
        else {
        	return list.get(0).getId();
        }
        
        
    }
    
    @Override
    public List<LocationzonesInfo> listLocationZoneInfos(){
    	
    	String sql = "Select new " + LocationzonesInfo.class.getName()//
                + "(a.id, a.locationName, a.zoneid) "//
                + " from " + LocationZones.class.getName() + " a ";
        System.out.println(" List LocationzonesInfo ");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
        
    }
    
    @Override
    public List<LocationzonesInfo> listLocationsInfosByZone(int zone){
    	
    	String sql = "Select new " + LocationzonesInfo.class.getName()//
                + "(a.id, a.locationName, a.zoneid) "//
                + " from " + LocationZones.class.getName() + " a where a.zoneid="+zone;
        System.out.println(" List LocationzonesInfo ");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
        
    }
    
    
    
    
	
 
}