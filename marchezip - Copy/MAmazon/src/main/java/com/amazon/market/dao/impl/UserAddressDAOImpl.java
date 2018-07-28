package com.amazon.market.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.UserAddress;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.UserAddressInfo;



//Transactional for Hibernate
@Transactional
public class UserAddressDAOImpl  implements UserAddressDAO {

	@Autowired
    private SessionFactory sessionFactory;
 
    public UserAddress findAddress(int addrid) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(UserAddress.class);
        crit.add(Restrictions.eq("id", addrid));
        return (UserAddress) crit.uniqueResult();
    }
    
    @Override
    public List<UserAddressInfo> listUserAddressInfos(int uid) {
        String sql = "Select new " + UserAddressInfo.class.getName()//
                + "(a.userid, a.firstname, a.lastname, a.doorno, a.street, a.area,a.city, a.state, a.pincode) "//
                + " from " + UserAddress.class.getName() + " a where a.userid="+uid;
        Session session = sessionFactory.getCurrentSession();
        System.out.println(sql);
        Query query = session.createQuery(sql);
        return query.list();
    }
    
	public void save(UserAddressInfo useraddressinfo) {
		int idno = useraddressinfo.getUserid();
		 System.out.println("userid is "+idno);
		UserAddress useraddress = null;
	 
	        boolean isNew = false;
	        /*if (idno > 0) {
	        	useraddress = this.findAddress(idno);
	        }*/
	        
	        if (useraddress == null) {
	            isNew = true;
	            useraddress = new UserAddress();
	            
	        }
	        
	        useraddress.setUserid(idno);
	        useraddress.setFirstname(useraddressinfo.getFirstname());
	        useraddress.setLastname(useraddressinfo.getLastname());
	        useraddress.setDoorno(useraddressinfo.getDoorno());
	        useraddress.setStreet(useraddressinfo.getStreet());
	        useraddress.setArea(useraddressinfo.getArea());
	        useraddress.setCity(useraddressinfo.getCity());
	        useraddress.setState(useraddressinfo.getState());
	        useraddress.setPincode(useraddressinfo.getPincode());
	       
	        if (isNew) {
	            this.sessionFactory.getCurrentSession().persist(useraddress);
	        }
	       
	        this.sessionFactory.getCurrentSession().flush();
	}
}
