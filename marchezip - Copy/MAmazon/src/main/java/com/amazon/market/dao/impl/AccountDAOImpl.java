package com.amazon.market.dao.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.AccountDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.model.AccountInfo;
 
// Transactional for Hibernate
@Transactional
public class AccountDAOImpl implements AccountDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public Account findAccount(String userName ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("email", userName));
        return (Account) crit.uniqueResult();
    }
    
    public Account findRegistration(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("id", id));
        return (Account) crit.uniqueResult();
    }
    
    public boolean findUsername(AccountInfo registerinfo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("email", registerinfo.getEmail()));
        if((Account) crit.uniqueResult()==null) {
        	return false;
        }else {
        	return true;
        }
    }
    
    public int changePassword(int id,String cpwd,String npwd) {
    
    	Session session = sessionFactory.getCurrentSession();
		String hql="update Account set Password =:npwd where Password=:cpwd and id = :id";
		Query query = session.createQuery(hql);		 
		//System.out.println(id);
		query.setParameter("cpwd", cpwd);
		query.setParameter("npwd", npwd);
		query.setParameter("id", id);

	    int result = query.executeUpdate();
	    
	    
    	return result;
    }
    
	@Override
	public void save(AccountInfo registerinfo) {
		int idno = registerinfo.getId();
		 
		Account register = null;
	 
	        boolean isNew = false;
	        if (idno > 0) {
	        	register = this.findRegistration(idno);
	        }
	        if (register == null) {
	            isNew = true;
	            register = new Account();
	            
	        }
	        System.out.println(registerinfo.getEmail());
	        System.out.println(registerinfo.getMobileno());
	        register.setId(idno);
	        register.setUserName(registerinfo.getUname());
	        register.setPassword(registerinfo.getPassword());
	        register.setUserRole("REGISTEREDUSER");
	        register.setEmail(registerinfo.getEmail());
	        register.setMobileno(registerinfo.getMobileno());
	        register.setActive(true);
	       
	        if (isNew) {
	            this.sessionFactory.getCurrentSession().persist(register);
	        }
	       
	        this.sessionFactory.getCurrentSession().flush();
	}
 
}