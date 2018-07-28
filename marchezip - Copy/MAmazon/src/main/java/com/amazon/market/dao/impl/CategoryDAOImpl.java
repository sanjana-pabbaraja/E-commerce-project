package com.amazon.market.dao.impl;
 
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazon.market.dao.CategoryDAO;
import com.amazon.market.entity.Category;
import com.amazon.market.model.CategoryInfo;
 
public class CategoryDAOImpl implements CategoryDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    /*ProductInfo(applicant.getId(), applicant.getProductName(), //
    applicant.getCategoryId(), applicant.getProductDescription(), //
    applicant.getPrice(), null);
 */
 
   
    @SuppressWarnings("unchecked")
	@Override
    public List<CategoryInfo> listCategoryInfos() {
        String sql = "Select new " + CategoryInfo.class.getName()//
                + "(a.id, a.categoryName, a.categoryDescription) "//
                + " from " + Category.class.getName() + " a ";
        System.out.println("{{{");System.out.println(sql);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }
 
    
   

	@Override
	public Category findCategory(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Category.class);
        crit.add(Restrictions.eq("id", id));
        return (Category) crit.uniqueResult();
	}

	@Override
	public void saveCategory(CategoryInfo productInfo) {
		// TODO Auto-generated method stub
		long id = productInfo.getId();
        Category applicant = null;
        if (id>0) {
            applicant = this.findCategory(id);
        }
        boolean isNew = false;
        if (applicant == null) {
            isNew = true;
            applicant = new Category();
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
            session.persist(applicant);
        }
        this.sessionFactory.getCurrentSession().flush();
	}

	@Override
	public CategoryInfo findCategoryInfo(long id) {
		// TODO Auto-generated method stub
		 Category applicant = this.findCategory(id);
	        if (applicant == null) {
	            return null;
	        }
	        return new CategoryInfo(applicant.getId(),applicant.getCategoryName(),applicant.getCategoryDescription());
	}

	@Override
	public void deleteCategory(long id) {
		// TODO Auto-generated method stub
		Category applicant = this.findCategory(id);
        if (applicant != null) {
            this.sessionFactory.getCurrentSession().delete(applicant);
        }
	}
 
}