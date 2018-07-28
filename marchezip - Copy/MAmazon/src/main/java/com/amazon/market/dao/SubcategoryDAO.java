package com.amazon.market.dao;
 
import java.util.List;
import java.util.Map;

import com.amazon.market.entity.Subcategory;
import com.amazon.market.model.SubcategoryInfo;

 
public interface SubcategoryDAO {
 
    public Subcategory findCategory(long id);
 
    public List<SubcategoryInfo> listCategoryInfos();
    public List<SubcategoryInfo> subCatListByCategory(int category);
    public Map<Integer, List<SubcategoryInfo>> listSubCategoryInfos();
 
    public void saveCategory(SubcategoryInfo productInfo);
 
    public SubcategoryInfo findCategoryInfo(long id);
 
    public void deleteCategory(long id);
    
}