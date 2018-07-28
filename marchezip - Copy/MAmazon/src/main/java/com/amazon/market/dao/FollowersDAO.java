package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.entity.Followers;
import com.amazon.market.entity.Products;
import com.amazon.market.model.AccountInfo;

public interface FollowersDAO {
	public boolean findUserFollower(int followerid,int userid);
    public void save(int followerid,int userid);
    public List<AccountInfo> listFollowersInfos(int userid);
    public List<AccountInfo> listFollowingInfos(int followerid);
    public void unfollow(int followerid,int userid);
    public Followers findFollow(int id);
    public int findUserFollowerID(int followerid,int userid);
    
}