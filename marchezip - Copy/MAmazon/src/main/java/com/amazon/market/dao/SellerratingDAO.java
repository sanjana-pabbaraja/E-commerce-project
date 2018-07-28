package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.entity.Followers;
import com.amazon.market.entity.Products;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.entity.SellerRating;


public interface SellerratingDAO {
	
    public void save(int orderid,int productid,int sellerid,int userid,int rating);
    public String getRatingbyseller(int sellerid);

}