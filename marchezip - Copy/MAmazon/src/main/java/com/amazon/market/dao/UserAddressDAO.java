package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.entity.UserAddress;
import com.amazon.market.model.UserAddressInfo;

public interface UserAddressDAO {
	public UserAddress findAddress(int addrid);
    public void save(UserAddressInfo useraddressinfo);
    public List<UserAddressInfo> listUserAddressInfos(int uid);
    
}
