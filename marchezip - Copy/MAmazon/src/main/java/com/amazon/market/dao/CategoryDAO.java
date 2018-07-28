package com.amazon.market.dao;
 
import java.util.List;

import com.amazon.market.entity.Category;
import com.amazon.market.model.CategoryInfo;

 
public interface CategoryDAO {
 
    public Category findCategory(long id);
 
    public List<CategoryInfo> listCategoryInfos();
 
    public void saveCategory(CategoryInfo productInfo);
 
    public CategoryInfo findCategoryInfo(long id);
 
    public void deleteCategory(long id);
    
}