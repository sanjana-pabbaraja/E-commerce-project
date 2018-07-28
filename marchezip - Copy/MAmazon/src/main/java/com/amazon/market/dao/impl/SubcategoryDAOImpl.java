package com.amazon.market.dao.impl;
 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazon.market.dao.SubcategoryDAO;
import com.amazon.market.entity.Subcategory;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.SubcategoryInfo;
 
public class SubcategoryDAOImpl implements SubcategoryDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    /*ProductInfo(applicant.getId(), applicant.getProductName(), //
    applicant.getCategoryId(), applicant.getProductDescription(), //
    applicant.getPrice(), null);
 */
 
   
    @SuppressWarnings("unchecked")
	@Override
    public List<SubcategoryInfo> listCategoryInfos() {
        String sql = "Select new " + SubcategoryInfo.class.getName()//
                + "(a.id, a.categoryName, a.categoryDescription) "//
                + " from " + Subcategory.class.getName() + " a ";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
    
    
    @Override
    public List<SubcategoryInfo> subCatListByCategory(int category) {
        String sql = "Select new " + SubcategoryInfo.class.getName()//
                + "(a.id, a.categoryName, a.categoryDescription) "//
                + " from " + Subcategory.class.getName() + " a where a.parentcatid="+category;
        System.out.println("{subCatListByCategory{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        System.out.println(query.list().toString());
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<Integer, List<SubcategoryInfo>> listSubCategoryInfos() {
        String sql = "Select new " + SubcategoryInfo.class.getName()//
                + "(a.id, a.parentcatid,a.categoryName, a.categoryDescription) "//
                + " from " + Subcategory.class.getName() + " a ";
        
        Map<Integer, List<SubcategoryInfo>>  finalList=new LinkedHashMap<Integer, List<SubcategoryInfo>>();
        
        System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        List<SubcategoryInfo> subcatList = query.list();
        List<SubcategoryInfo> subcategoryInfoList=null;
        System.out.println("subcatList.toString()"+subcatList.toString());
        System.out.println("{listSubCategoryInfos{");
        for(int i=0;i<subcatList.size();i++) {
        	subcategoryInfoList = this.subCatListByCategory(subcatList.get(i).getParentcatid());
        	System.out.println("inside for "+i);
        	System.out.println(subcategoryInfoList.toString());
        	System.out.println(subcatList.get(i).getParentcatid());
        	finalList.put(subcatList.get(i).getParentcatid(),subcategoryInfoList);
        }
        
        return finalList;
    }
 
    
   

	@Override
	public Subcategory findCategory(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Subcategory.class);
        crit.add(Restrictions.eq("id", id));
        return (Subcategory) crit.uniqueResult();
	}

	@Override
	public void saveCategory(SubcategoryInfo productInfo) {
		// TODO Auto-generated method stub
		long id = productInfo.getId();
		Subcategory subcategory = null;
        if (id>0) {
        	subcategory = this.findCategory(id);
        }
        boolean isNew = false;
        if (subcategory == null) {
            isNew = true;
            subcategory = new Subcategory();
            //applicant.setId(UUID.randomUUID().toString());
        }
       // applicant.setProductName(productInfo.getName());
        //applicant.setPrice(productInfo.getPrice());
        //applicant.setCategoryId(productInfo.getcategoryId());
       // applicant.setProductDescription(productInfo.getproductDescription());
       // applicant.setProductImage(productInfo.getimage());
              //
        /*if (productInfo.getimage() != null) {
            byte[] image = productInfo.getimage().getBytes();
            if (image != null && image.length > 0) {
            	applicant.setProductImage(image);
            }
        }*/
        if (isNew) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(subcategory);
        }
        this.sessionFactory.getCurrentSession().flush();
	}

	@Override
	public SubcategoryInfo findCategoryInfo(long id) {
		// TODO Auto-generated method stub
		Subcategory subcategory = this.findCategory(id);
	        if (subcategory == null) {
	            return null;
	        }
	        return new SubcategoryInfo(subcategory.getId(),subcategory.getCategoryName(),subcategory.getCategoryDescription());
	}

	@Override
	public void deleteCategory(long id) {
		// TODO Auto-generated method stub
		Subcategory subcategory = this.findCategory(id);
        if (subcategory != null) {
            this.sessionFactory.getCurrentSession().delete(subcategory);
        }
	}
 
}