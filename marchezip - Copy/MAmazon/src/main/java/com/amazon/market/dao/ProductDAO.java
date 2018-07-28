package com.amazon.market.dao;
 
import java.util.List;

import com.amazon.market.entity.Products;
import com.amazon.market.model.ProductInfo;
 
public interface ProductDAO {
 
    public Products findProduct(long id);
 
    public List<ProductInfo> listProductInfos();
    
    public List<ProductInfo> listProductInfosByUid(int uid);
    
    public List<ProductInfo> listProductInfosByLocation(String locations);
    
    
    public List<ProductInfo> listProductInfosByName(String name,int cid,int locid);

    
    public List<ProductInfo> listProductInfosByCateogory(int cid);
 
    public List<ProductInfo> listProductInfosBySubateogory(int cid);

    public List<ProductInfo> listLatestProducts();
    
    public List<ProductInfo> listRecommendedProducts(String email);


    public void saveProduct(ProductInfo productInfo,int owner);
 
    public ProductInfo findProductInfo(long id);
 
    public void deleteProduct(long id);
    
}