package com.amazon.market.dao.impl;
 
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazon.market.dao.ApplicantDAO;
import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.OrderStatusDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.LocationZones;
import com.amazon.market.entity.Products;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.OrderDetailInfo;
import com.amazon.market.model.OrderInfo;
import com.amazon.market.model.ProductInfo;
 
public class ProductDAOImpl implements ProductDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private LocationZonesDAO locationZonesDAO; 
 
    @Autowired
    private OrderDAO orderDAO;
    
    /*ProductInfo(applicant.getId(), applicant.getProductName(), //
    applicant.getCategoryId(), applicant.getProductDescription(), //
    applicant.getPrice(), null);
 */
 
    
    @SuppressWarnings("unchecked")
  	
    public List<ProductInfo> listLatestProducts() {
    	
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage) "//
                + " from " + Products.class.getName() + " a where a.availability=1 order by a.id desc";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setMaxResults(5);
        return query.list();
    }
    
    
    
    @SuppressWarnings("unchecked")
  	
    public List<ProductInfo> listRecommendedProducts(String email) {
    	
    	List<ProductInfo> ls=null;

        int page = 1;
        
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
       /*PaginationResult<OrderInfo> paginationResult //
        = orderDAO.listOrderInfoByEmail(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getEmail());*/
        
        List<OrderInfo> orderlist = orderDAO.listOrderInfoByEmail(page, MAX_RESULT, MAX_NAVIGATION_PAGE,email);
 
        if(orderlist.size()>0) {
        for(OrderInfo or: orderlist){
        	String orderid=or.getId();
            List<OrderDetailInfo> odetails = orderDAO.listOrderDetailInfos(orderid);
            for(OrderDetailInfo orde: odetails){
            long pid=orde.getProductId();
           ls =listProductInfosByCateogory((int) findProduct(pid).getCategoryId());
            
            }
           
        }
        }
        else {

	String sql = "Select new " + ProductInfo.class.getName()//
            + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage) "//
            + " from " + Products.class.getName() + " a where a.availability=1 order by a.price";
    System.out.println("{{{");System.out.println(sql);
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(sql);
    query.setMaxResults(5);
    ls= query.list();
        }
	
return ls;
        
        
    }
    
    
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> listProductInfos() {
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage) "//
                + " from " + Products.class.getName() + " a where a.availability=1 order by a.id desc";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> listProductInfosByUid(int uid) {
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage,a.availability,a.addedTime) "//
                + " from " + Products.class.getName() + " a where a.owner="+uid+" order by a.id desc";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> listProductInfosByLocation(String locations) {
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage,a.availability,a.addedTime) "//
                + " from " + Products.class.getName() + " a where a.location in ("+locations+") and  a.availability=1  order by a.id desc";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
   	@Override
       public List<ProductInfo> listProductInfosByName(String name,int sid,int locid) {
           
    	
    	String sql = "Select new " + ProductInfo.class.getName()//	   
                   + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage,a.availability,a.addedTime) "//
                   + " from " + Products.class.getName() + " a where a.productName like '%"+name+"%'   ";
          if(sid>0)
        	  sql+=" and a.categoryId="+sid+"";
          if(locid>1)
        	  sql+=" and a.location="+locid+"";
          
          sql+=" and a.availability=1 order by a.id desc";
    	
    	System.out.println("{{{");System.out.println(sql);
           Session session = sessionFactory.getCurrentSession();
           Query query = session.createQuery(sql);
           return query.list();
       }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> listProductInfosByCateogory(int cid) {
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage) "//
                + " from " + Products.class.getName() + " a where a.categoryId="+cid+" and a.availability=1 order by a.id desc";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> listProductInfosBySubateogory(int cid) {
        String sql = "Select new " + ProductInfo.class.getName()//
                + "(a.id, a.productName, a.categoryId, a.productDescription, a.price, a.productImage) "//
                + " from " + Products.class.getName() + " a where a.subCategoryId="+cid+" order by a.id desc";
        System.out.println("{listProductInfosBySubateogory{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
 
    
   

	@Override
	public Products findProduct(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Products.class);
        crit.add(Restrictions.eq("id", id));
        return (Products) crit.uniqueResult();
	}

	@Override
	public void saveProduct(ProductInfo productInfo,int owner) {
		// TODO Auto-generated method stub
		long id = productInfo.getId();
        Products product = null;
       
        if (id>0) {
        	product = this.findProduct(id);
        }
        boolean isNew = false;
        if (product == null) {
            isNew = true;
            product = new Products();
            //applicant.setId(UUID.randomUUID().toString());
        }
        product.setProductName(productInfo.getName());
        product.setPrice(productInfo.getPrice());
        product.setCategoryId(productInfo.getcategoryId());
        product.setSubCategoryId(productInfo.getsubcategoryId());//setCategoryId(productInfo.getcategoryId());
        product.setProductDescription(productInfo.getproductDescription());
        product.setAddedTime(new Date());
        product.setOwner(owner);
        product.setAvailability(productInfo.getAvailability());
        product.setLocation(this.locationZonesDAO.getLocationZone(productInfo.getLocation()));
        
       // applicant.setProductImage(productInfo.getimage());
              //
        if (productInfo.getimage() != null) {
            byte[] image = productInfo.getimage().getBytes();
            if (image != null && image.length > 0) {
            	product.setProductImage(image);
            }
        }
        if (isNew) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(product);
        }
        this.sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public ProductInfo findProductInfo(long id) {
		// TODO Auto-generated method stub
		 Products product = this.findProduct(id);
	        if (product == null) {
	            return null;
	        }
//	        ProductInfo(Long long1, String name, long cat,long subcat,int loc, String pd, double price,int availability)
	        return new ProductInfo(product.getId(), product.getProductName(), //
	        		product.getCategoryId(),product.getSubCategoryId(),product.getLocation().getId(), product.getProductDescription(), //
	        		product.getPrice(),product.getAvailability(),product.getOwner());
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		Products product = this.findProduct(id);
        if (product != null) {
            this.sessionFactory.getCurrentSession().delete(product);
        }
	}
 
}