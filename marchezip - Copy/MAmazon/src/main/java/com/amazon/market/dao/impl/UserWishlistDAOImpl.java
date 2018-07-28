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
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.dao.UserWishlistDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.Products;
import com.amazon.market.entity.UserAddress;
import com.amazon.market.entity.UserWishlist;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.model.UserAddressInfo;



//Transactional for Hibernate
@Transactional
public class UserWishlistDAOImpl  implements UserWishlistDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private AccountDAO accountDAO;
 
    public boolean findUserWishlist(long productid,int userid) {
    	/*Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(UserWishlistDAO.class);
        
       
        crit.add(Restrictions.eq("productId", productid));
        crit.add(Restrictions.eq("userId", userid));
        //return (UserWishlistDAO) crit.uniqueResult();
        UserWishlistDAO userWishlistDAO = (UserWishlistDAO) crit.uniqueResult();
        
        if(userWishlistDAO==null) {
        	System.out.println("========userWishlistDAO=======");
        	System.out.println(userWishlistDAO);
        	return false;
        }else {
        	System.out.println("========userWishlistDAO.toString()=======");
        	System.out.println(userWishlistDAO.toString());
        	return true;
        }*/
    	Session session = sessionFactory.getCurrentSession();
    	try{

            List<UserWishlistDAO> userWishlists = new ArrayList<UserWishlistDAO>();

            userWishlists = session.createQuery("FROM UserWishlist WHERE  Userid ="+userid+" and Productid="+productid)
                    .list();
//.setParameter("productId", productid)
            System.out.println("FROM UserWishlist WHERE  Userid ="+userid);
			System.out.println("========userWishlists=======");
			System.out.println(userWishlists);
			
            if(userWishlists.size() > 0)
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
    public List<ProductInfo> listWishlistInfos(int userid) {
    	String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.product.id, a.product.productName, a.product.categoryId, a.product.productDescription, a.product.price, a.product.productImage,a.product.availability,a.product.addedTime) "//
                + " from " + UserWishlist.class.getName() + " a where a.user.id="+userid;
        Session session = sessionFactory.getCurrentSession();
        System.out.println(sql);
        Query query = session.createQuery(sql);
        return query.list();
    }
    
	public void save(long prodcutid,int userid) {
		
			boolean idno = this.findUserWishlist(prodcutid, userid);
			
			System.out.println(" idno "+idno);
			
			UserWishlist userWishlist = null;
	        boolean isNew = false;
	        if (!idno){
	            isNew = true;
	            userWishlist = new UserWishlist();
	            
				Products prodcut = productDAO.findProduct(prodcutid);
				Account account = accountDAO.findRegistration(userid);
					  	        
				if(prodcut != null && account != null) {
				    userWishlist.setProduct(prodcut);
				    userWishlist.setUser(account);
				    userWishlist.setLastupdated(new Date());
				}
				
				System.out.println(userWishlist.toString());
		        System.out.println(isNew);
		       
		        if (isNew) {
		            this.sessionFactory.getCurrentSession().persist(userWishlist);
		        }
		        this.sessionFactory.getCurrentSession().flush();
	        }
	        
	        
	        
	}
}
