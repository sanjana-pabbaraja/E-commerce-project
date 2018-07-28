package com.amazon.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazon.market.dao.AccountDAO;
import com.amazon.market.dao.ApplicantDAO;
import com.amazon.market.dao.CategoryDAO;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.CartInfo;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.MessageInfo;
import com.amazon.market.model.OrderDetailInfo;
import com.amazon.market.model.OrderInfo;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.util.Utils;
import com.amazon.market.validator.AccountInfoValidator;
import com.amazon.market.validator.ApplicantValidator;
import com.amazon.market.validator.ProductInfoValidator;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need To use RedirectAttributes
@EnableWebMvc
@SessionAttributes("cartObj")
@javax.servlet.annotation.MultipartConfig
public class DeliveryboyController {

	@Autowired
	private ApplicantDAO applicantDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ApplicantValidator applicantValidator;

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MessageDAO messageDAO;

	private AccountInfoValidator accountInfoValidator;

	@Autowired
	private ProductInfoValidator productInfoValidator;
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
    private OrderDAO orderDAO;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		
	}
	
public Map<String, Integer> getAllOptionCounts(Account account) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
				
		
        List<OrderInfo> deliverables = orderDAO.listOrderInfoByDeliverBoy(account.getId(),"2,3");

		if(deliverables.size()>0) {
			map.put("deliverables",deliverables.size());
		}else {
		map.put("deliverables",0);
		}		
		
        List<OrderInfo> deliveryhistory = orderDAO.listOrderInfoByDeliverBoy(account.getId(),"5");
		if(deliveryhistory.size()>0) {
			map.put("deliveryhistory",deliveryhistory.size());
		}else {
		map.put("deliveryhistory",0);
		}	
		
		return map;
	}



	@RequestMapping("/deliveryboy/account")
	public String deliveryboyaccountPage(HttpServletRequest request,Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		model.addAttribute("account",account);
		model.addAttribute("optionCount",getAllOptionCounts(account));

		return "deliveryboy/profile";
	}

	@RequestMapping(value = { "/deliveryboy/deliverables" }, method = RequestMethod.GET)
	public String deliverablesPage(HttpServletRequest request,Model model) {
			Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
			System.out.println("i am in deliverables");
	       	 
	        
	        List<OrderInfo> paginationResult = orderDAO.listOrderInfoByDeliverBoy(account.getId(),"2,3");
			model.addAttribute("optionCount",getAllOptionCounts(account));

	        model.addAttribute("paginationResult", paginationResult);
	        

		return "deliveryboy/deliverables";
	}
	
	@RequestMapping(value = { "/deliveryboy/updateorderstatus" }, method = RequestMethod.GET)
	public String orderUpdatePage(HttpServletRequest request,Model model,@RequestParam("orderId") int orderid,@RequestParam("orderstatus") int status,@RequestParam("pids") String pid  ,final RedirectAttributes redirectAttributes) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());

		String sub=null,msg=null;
		int res=orderDAO.updateOrdStatus(orderid,status,pid);
		System.out.println(res);
		OrderInfo orderinfo=orderDAO.getOrderInfo(""+orderid);
		String customeremail=orderinfo.getCustomerEmail();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailMail mm = (MailMail) context.getBean("mailMail");
		ProductInfo product= productDAO.findProductInfo(Long.parseLong(pid));
		System.out.println("customeremail");
		redirectAttributes.addFlashAttribute("cmessage", "Order Status Updated");

		if(status==2) {
		sub="Marché Order Update:  "+product.getName() +" Order Confirmed!";
		msg= "Your Order of "+product.getName()+" is Confirmed by the seller.  Thank you for your order. We’ll send a confirmation when your order ships.";
		}
		else if(status==3) {
			sub="Marché Order Update:  "+product.getName() +" Picked up from seller!";

			msg="We'd like to let you know that we received your product from Marchant, and is preparing it for shipment.";
		}
		else if(status==4 || status==5) {
			sub="Marché Order Update:  "+product.getName() +"  has been dispatched! ";
			msg="We thought you'd like to know that we've dispatched your item(s). Your order is on the way.";
		}
		else if(status==6) {
			sub="Marché Order Update:  "+product.getName() +"  order cancelled by seller ";
			msg="Your Order of "+product.getName()+" is Cancelled by the seller.";
		}
		mm.sendMail(orderinfo.getCustomerName(),sub, msg,customeremail);


		model.addAttribute("optionCount",getAllOptionCounts(account));

		
		return "redirect:/deliveryboy/deliver-order?orderId="+orderid;
	}
	
	
	
	@RequestMapping(value = { "/deliveryboy/deliver-order" }, method = RequestMethod.GET)
    public String deliverOrderPage(HttpServletRequest request,Model model, @RequestParam("orderId") String orderId) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/deliveryboy/deliverables";
        }
        List<OrderDetailInfo> details = orderDAO.listDeliverOrderDetailInfos(orderId,account.getId());
        orderInfo.setDetails(details);
        model.addAttribute("cmessage", (String) request.getAttribute("cmessage"));

        model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("optionCount",getAllOptionCounts(account));

        //model.addAttribute("optionCount",getAllOptionCounts(account));
        return "/deliveryboy/deliver-order";
    }
	
	
	
	
	
	@RequestMapping(value = { "/deliveryboy/delivered-history" }, method = RequestMethod.GET)
	public String deliveredHistorysPage(HttpServletRequest request,Model model) {
		
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		System.out.println("i am in deliverables");
       	 
        
        List<OrderInfo> paginationResult = orderDAO.listOrderInfoByDeliverBoy(account.getId(),"5");
 
        model.addAttribute("paginationResult", paginationResult);
        
		model.addAttribute("optionCount",getAllOptionCounts(account));

		return "deliveryboy/delivered-history";
	}
	
	
	
	
	
	
	

	@RequestMapping("/deliveryboy/areas")
	public String deliveryareasPage(Model model) {

		return "deliveryboy/areas";
	}

	
		@RequestMapping(value = { "/deliveryboy/changepassword" }, method = RequestMethod.GET)
		public String deliveryboychangepwdPage(HttpServletRequest request,Model model) {
			

		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		model.addAttribute("optionCount",getAllOptionCounts(account));

		
		return "deliveryboy/changepassword";
	}
}

