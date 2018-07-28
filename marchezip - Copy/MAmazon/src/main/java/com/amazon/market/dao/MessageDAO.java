package com.amazon.market.dao;
import java.util.List;

import com.amazon.market.model.MessageInfo;
import com.amazon.market.entity.Message;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.entity.Account;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.entity.Products;
public interface MessageDAO {
/**
* This is the method to be used to send the message
* to a user by passing user's username as receiverName, message and logged-in username as senderName .
*/
public Message findRegistration(int id);
public void save(MessageInfo messageinfo,Account sid,Account rid,Products pid);
/**
* This is the method to be used to get the messages
* of a logged user by passing user's username as loggedUserName .
*/
public List<MessageInfo> getMyLatestMessages(int recipientId);
/**
* This is the method to be used to get the previous messages
* of a logged user by passing user's username as loggedUserName .
*/
public List<MessageInfo> getMyMessages(int recipientId,int senderId,long productId);
public List<MessageInfo> getMyPrevMessages(String loggedUserName, String minVal);
/**
* This is the method to be used to get the next messages
* of a logged user by passing user's username as loggedUserName .
*/
public List<MessageInfo> getMyNextMessages(String loggedUserName, String minVal);
}