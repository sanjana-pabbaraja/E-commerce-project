package com.amazon.market.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import com.amazon.market.dao.AccountDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products; 
import com.amazon.market.model.AccountInfo;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.dao.ProductDAO;
//import com.amazon.market.entity.Applicant;
import com.amazon.market.entity.Message;
//import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.MessageInfo;

public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	  public Message findRegistration(int id) {
	        Session session = sessionFactory.getCurrentSession();
	        Criteria crit = session.createCriteria(Message.class);
	        crit.add(Restrictions.eq("id", id));
	        return (Message) crit.uniqueResult();
	    }

	@Override
	public void save(MessageInfo messageinfo, Account sid,Account rid,Products pid) 
	{
		// TODO Auto-generated method stub
		int idno =messageinfo.getId();
		 
		Message message = new Message();;
	 boolean isNew=true;
	      
	//	Session session= this.sessionFactory.getCurrentSession();
		//Transaction tx3 = session.beginTransaction();
		//Message m=new Message();
	   //   Account account1= accountDAO.findRegistration(sid);
	     // Account account2= accountDAO.findRegistration(rid);
	     	//		Products product1=productDAO.findRegistration(pid);
	     			
		//	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(messageinfo.getId());
		System.out.println(messageinfo.getMessage());
		//System.out.println(name);
		message.setId(idno);
		message.setSender(sid);
		message.setRecipient(rid);
		message.setMessage(messageinfo.getMessage());
		message.setUpdateTime(new Date());
		message.setProduct(pid);
		 if (isNew) {
	            this.sessionFactory.getCurrentSession().persist(message);
	        }
	       
	        this.sessionFactory.getCurrentSession().flush();
		//tx3.
		 
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> getMyLatestMessages(int recipientId) {
		String sql = "Select new " + MessageInfo.class.getName()//
                + "(a.id, a.sender, a.recipient, a.message, a.updateTime,a.product) "
                + " from " + Message.class.getName()+" a " +" where a.recipient='"+recipientId+"'"+" group by a.sender,a.product";
		
Session session = this.sessionFactory.getCurrentSession();
/*Criteria criteria = session.createCriteria(Message.class);
criteria.add(Restrictions.eq("recipient.id", recipientId));*/
		
Query q=session.createQuery(sql);
return q.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> getMyMessages(int recipientId,int senderId,long productId) {
	
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Message.class);
		Criterion rid1 = Restrictions.eq("recipient.id", recipientId);
		Criterion sid1 = Restrictions.eq("sender.id", senderId);
		Criterion pid1 = Restrictions.eq("product.id", productId);
		Criterion rid2 = Restrictions.eq("recipient.id",senderId );
		Criterion sid2 = Restrictions.eq("sender.id",recipientId);
	//	Criterion pid2 = Restrictions.eq("product.id", productId);
		 //LogicalExpression addExp1 = Restrictions.and(rid1,sid1);
		 Conjunction addExp1 = Restrictions.and(rid1,sid1,pid1);
// LogicalExpression addExp3 = Restrictions.and(rid2,sid2);
 //LogicalExpression addExp4 = Restrictions.and(sid2,pid2);
		 Conjunction addExp2 = Restrictions.and(rid2,sid2,pid1);
 
		 LogicalExpression orExp = Restrictions.or(addExp1,addExp2);
		 criteria.add(orExp);
		    
		   
        return criteria.list();
		
		

	}
	
	
	@Override
	public List<MessageInfo> getMyPrevMessages(String loggedUserName, String minVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageInfo> getMyNextMessages(String loggedUserName, String minVal) {
		// TODO Auto-generated method stub
		return null;
	}


}
