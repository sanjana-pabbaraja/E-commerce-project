package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.entity.UserWishlist;
import com.amazon.market.model.ProductInfo;



public interface UserWishlistDAO {
	public boolean findUserWishlist(long prodcutid,int userid);
    public void save(long productid,int userid);
    public List<ProductInfo> listWishlistInfos(int userid);
    
}
