package com.amazon.market.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.market.dao.AccountDAO;
import com.amazon.market.dao.FollowersDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.dao.UserWishlistDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.Followers;
import com.amazon.market.entity.Products;
import com.amazon.market.entity.UserAddress;
import com.amazon.market.entity.UserWishlist;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.model.UserAddressInfo;



//Transactional for Hibernate
@Transactional
public class FollowersDAOImpl  implements FollowersDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private AccountDAO accountDAO;
 
    public boolean findUserFollower(int followerid,int userid) {
    	
    	Session session = sessionFactory.getCurrentSession();
    	try{

            List<Followers> followers = new ArrayList<Followers>();

            followers = session.createQuery("FROM Followers WHERE  Userid ="+userid+" and Followerid="+followerid)
                    .list();

            System.out.println("FROM Followers WHERE  Userid ="+userid);
			System.out.println("========findUserFollower=======");
			System.out.println(followers.get(0).getId());
			
            if(followers.size() > 0)
                return true;
            else
                return false;
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
            return false;
        }
    }
    
    
    
    @Override
    public List<AccountInfo> listFollowersInfos(int userid) {
    	String sql = "Select new " + AccountInfo.class.getName()//
                + "(a.follower.id,a.follower.userName,a.follower.email,a.follower.mobileno) "//
                + " from " + Followers.class.getName() + " a where a.user.id="+userid;
        Session session = sessionFactory.getCurrentSession();
        System.out.println(sql);
        
        Query query = session.createQuery(sql);
        System.out.println(query.list().toString());
        return query.list();
    }
    
    @Override
    public List<AccountInfo> listFollowingInfos(int followerid) {
    	String sql = "Select new " + AccountInfo.class.getName()//
                + "(a.user.id,a.user.userName,a.user.email,a.user.mobileno) "//
                + " from " + Followers.class.getName() + " a where a.follower.id="+followerid;
        Session session = sessionFactory.getCurrentSession();
        System.out.println(sql);
        
        Query query = session.createQuery(sql);
        System.out.println(query.list().toString());
        return query.list();
    }
    
	public void save(int followerid,int userid) {
		
			boolean idno = this.findUserFollower(followerid, userid);
			
			System.out.println(" idno "+idno);
			
			Followers followers = null;
	        boolean isNew = false;
	        if (!idno){
	            isNew = true;
	            followers = new Followers();
	            
				Account user = accountDAO.findRegistration(userid);
				Account follower = accountDAO.findRegistration(followerid);
					  	        
				if(user != null && follower != null) {
					followers.setUser(user);
					followers.setFollower(follower);
					followers.setLastupdated(new Date());
				}
				
				System.out.println(followers.toString());
		        System.out.println(isNew);
		       
		        if (isNew) {
		            this.sessionFactory.getCurrentSession().persist(followers);
		        }
		        this.sessionFactory.getCurrentSession().flush();
	        }
	}
	
	@Override
	public Followers findFollow(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Followers.class);
        crit.add(Restrictions.eq("id", id));
        return (Followers) crit.uniqueResult();
	}
	
	@Override
	public void unfollow(int followerid,int userid) {
		// TODO Auto-generated method stub
		int fid = findUserFollowerID(followerid,userid);
		if(fid>0) {
			Followers followers = findFollow(fid);
	        if (followers != null) {
	            this.sessionFactory.getCurrentSession().delete(followers);
	        }
		}
	}
	
public int findUserFollowerID(int followerid,int userid) {
    	
    	Session session = sessionFactory.getCurrentSession();
    	try{

            List<Followers> followers = new ArrayList<Followers>();

            followers = session.createQuery("FROM Followers WHERE  Userid ="+userid+" and Followerid="+followerid)
                    .list();

            System.out.println("FROM Followers WHERE  Userid ="+userid);
			System.out.println("========findUserFollower=======");
			System.out.println(followers.get(0).getId());
			
            if(followers.size() > 0)
                return followers.get(0).getId();
            else
                return 0;
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
            return 0;
        }
    }
	
	
}