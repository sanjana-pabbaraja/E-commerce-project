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
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.SellerratingDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.dao.UserWishlistDAO;

import com.amazon.market.entity.Account;
import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.Followers;
import com.amazon.market.entity.OrderDetail;

import com.amazon.market.entity.Products;
import com.amazon.market.entity.SellerRating;
import com.amazon.market.entity.UserAddress;
import com.amazon.market.entity.UserWishlist;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.ProductInfo;



//Transactional for Hibernate
@Transactional
public class SellerratingDAOImpl  implements SellerratingDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private OrderDAO orderDAO;
 
    public void save(int orderid,int productid,int sellerid,int userid,int rating) {
    	
    	 SellerRating sellerrating = new SellerRating();
         
		
				  	        
			if(orderid != 0 && productid != 0&& rating != 0&& sellerid != 0&& userid != 0) {
				sellerrating.setOrderid(orderid);
				sellerrating.setProductid(productid);
				sellerrating.setRating(rating);
				sellerrating.setUserid(userid);
				sellerrating.setSellerid(sellerid);
				String pid=""+productid;
				orderDAO.updateOrdStatus(orderid, 7, pid);
				//System.out.println(sellerrating.toString());
				this.sessionFactory.getCurrentSession().persist(sellerrating);
				 this.sessionFactory.getCurrentSession().flush();
			}
			
			
	       
    }
    
    @SuppressWarnings("unchecked")
	public String getRatingbyseller(int sellerid) {
    	String res="0-0";

    	Session session = sessionFactory.getCurrentSession();
    	try{

            List<SellerRating> sellerrating = new ArrayList<SellerRating>();


     sellerrating = session.createQuery(" FROM SellerRating WHERE  Sellerid="+sellerid).list();
            int sum=0;
            if(sellerrating.size()>0) {
            for(SellerRating sal:sellerrating) {
            	sum+=sal.getRating();
            }
			
            int ratingscount=sellerrating.size();
            int avg=sum/ratingscount;
           res=avg+"-"+ratingscount;
           System.out.println("Seller Rating"+res);
            }
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
            return res;
        }
    	
    	return res;
    }


	
}