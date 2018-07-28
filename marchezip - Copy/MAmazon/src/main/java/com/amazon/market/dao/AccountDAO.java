package com.amazon.market.dao;
 
import com.amazon.market.entity.Account;
import com.amazon.market.model.AccountInfo;
 
public interface AccountDAO {
 
    
    public Account findAccount(String userName );
    public Account findRegistration(int id);
    public void save(AccountInfo registerinfo);
    public boolean findUsername(AccountInfo registerinfo);
    public int changePassword(int id,String cpwd,String npwd);
}