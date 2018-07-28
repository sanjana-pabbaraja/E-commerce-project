package com.amazon.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.MessagingException;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import com.amazon.market.model.OrderInfo;
import com.amazon.market.model.PaginationResult;

import com.amazon.market.dao.OrderDAO;

import com.amazon.market.controller.MailMail;
import com.amazon.market.model.OrderDetailInfo;
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
import com.amazon.market.dao.FollowersDAO;
import com.amazon.market.dao.SellerratingDAO;

import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.SubcategoryDAO;
import com.amazon.market.dao.UserWishlistDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.CartInfo;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.LocationzonesInfo;
import com.amazon.market.model.MessageInfo;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.model.SubcategoryInfo;
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
public class CustomerController{

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
	
	@Autowired
	UserWishlistDAO userWishlistDAO;
 
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
    private LocationZonesDAO locationZonesDAO; 	
	
	@Autowired
	private FollowersDAO followersDAO;
	
	@Autowired
	private SellerratingDAO sellerratingDAO;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == ProductInfo.class) {
			dataBinder.setValidator(productInfoValidator);
			// For upload Image.
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
		
	}
	
	


	@RequestMapping("/customer/followers")
	public String custfollowersPage(HttpServletRequest request,Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		List<AccountInfo> followerslist = null;
		if(request.getUserPrincipal()!=null) {
			followerslist = followersDAO.listFollowersInfos(account.getId());
			System.out.println(followerslist.toString());
			}
		model.addAttribute("optionCount",getAllOptionCounts(account));
		model.addAttribute("followerslist", followerslist);
		return "customer/followers";
	}

	@RequestMapping("/customer/followings")
	public String custfollowingsPage(HttpServletRequest request,Model model) {
		
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		List<AccountInfo> followinglist = null;
		if(request.getUserPrincipal()!=null) {
			followinglist = followersDAO.listFollowingInfos(account.getId());
			System.out.println(followinglist.toString());
			}
		model.addAttribute("optionCount",getAllOptionCounts(account));
		model.addAttribute("followinglist", followinglist);

		return "/customer/followings";
	}
	
	
	@RequestMapping("/customer/changepassword")
	public String custchangepwdPage(Model model) {

		return "/customer/changepassword";
	}
	@RequestMapping("/customer/contact")
	public String contact() {
		return "customer/contact";
	}
	@RequestMapping("/customer/FAQ")
	public String FAQ() {
		return "customer/FAQ";
	}
	@RequestMapping("/customer/termsandc")
	public String termsandc() {
		return "customer/termsandc";
	}

	
	
	@RequestMapping("/customer/account")
	public String customeraccountPage(HttpServletRequest request,Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		
		model.addAttribute("optionCount",getAllOptionCounts(account));
			
		model.addAttribute("account",account);
		return "customer/account";
	}
	
	
public Map<String, Integer> getAllOptionCounts(Account account) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<ProductInfo> list = productDAO.listProductInfosByUid(account.getId());
		if(list.size()>0) {
			map.put("myproducts",list.size());
		}else {
		map.put("myproducts",0);
		}
				
		int page = 1;
        final int MAX_RESULT = 50;
        final int MAX_NAVIGATION_PAGE = 10;
        List<OrderInfo> paginationResult  //
        = orderDAO.listOrderInfoByEmail(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getEmail());
		
		if(paginationResult.size()>0) {
			map.put("placedorders",paginationResult.size());
		}else {
		map.put("placedorders",0);
		}
		
		List<OrderInfo> paginationResult1 //
        = orderDAO.listOrderInfoByOwner(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getId());
		if(paginationResult1.size()>0) {
			map.put("receivedorders",paginationResult1.size());
		}else {
		map.put("receivedorders",0);
		}		
		
		List<ProductInfo> wishlist = userWishlistDAO.listWishlistInfos(account.getId());
		if(wishlist.size()>0) {
			map.put("wishlistcount",wishlist.size());
		}else {
		map.put("wishlistcount",0);
		}	
		
		List<AccountInfo> followinglist = followersDAO.listFollowingInfos(account.getId());
		if(followinglist.size()>0) {
			map.put("followinglistcount",followinglist.size());
		}else {
		map.put("followinglistcount",0);
		}
		
		List<AccountInfo> followerslist = followersDAO.listFollowersInfos(account.getId());
		if(followerslist.size()>0) {
			map.put("followerslistcount",followerslist.size());
		}else {
		map.put("followerslistcount",0);
		}
		
		return map;
	}
	
	/* products related code  start */
	
	private Map<Long, String> dataForCategory() {
		List<CategoryInfo> list = categoryDAO.listCategoryInfos();
		System.out.println(list.size());
		Map<Long, String> categoryMap = new LinkedHashMap<Long, String>();
		for (CategoryInfo categoryInfo : list) {
			categoryMap.put(categoryInfo.getId(), categoryInfo.getcategoryName());
		}

		// positionMap.put("Leader", "Leader");
		// positionMap.put("Tester", "Tester");
		return categoryMap;
	}
	
	private Map<Long, String> dataForSubcategory() {
		List<SubcategoryInfo> list = subcategoryDAO.listCategoryInfos();
		System.out.println(list.size());
		Map<Long, String> categoryMap = new LinkedHashMap<Long, String>();
		for (SubcategoryInfo categoryInfo : list) {
			categoryMap.put(categoryInfo.getId(), categoryInfo.getcategoryName());
		}
		return categoryMap;
	}
	
	
	private Map<Long, String> dataForLocationZones() {
		List<LocationzonesInfo> list = locationZonesDAO.listLocationZoneInfos();
		System.out.println(list.size());
		Map<Long, String> locationMap = new LinkedHashMap<Long, String>();
		for (LocationzonesInfo locationInfo : list) {
			locationMap.put((long) locationInfo.getId(), locationInfo.getLocationName());
		}
		return locationMap;
	}
	
	@RequestMapping("/customer/myproducts")
	public String custproductsPage(HttpServletRequest request,Model model) {

		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		List<ProductInfo> list = productDAO.listProductInfosByUid(account.getId());
		model.addAttribute("productInfos", list);
		Map<Long, String> categoryMap = this.dataForCategory();
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("optionCount",getAllOptionCounts(account));
		return "customer/myproducts";
	}
	
	private String formProduct(Model model, ProductInfo productInfo) {
		model.addAttribute("productForm", productInfo);

		Map<Long, String> categoryMap = this.dataForCategory();
		Map<Long, String> subcategoryMap = this.dataForSubcategory();
		Map<Long, String> locationMap = this.dataForLocationZones();

		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("subcategoryMap", subcategoryMap);
		model.addAttribute("locationMap", locationMap);
		/*
		 * List<String> list = dataForSkills(); model.addAttribute("skills", list);
		 */

		if (productInfo.getId() > 0) {
			model.addAttribute("formTitle", "Edit Product");
		} else {
			model.addAttribute("formTitle", "Create Product");
		}

		return "customer/product";
	}
	
	@RequestMapping("/customer/addproduct")
	public String createProduct(HttpServletRequest request,Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		ProductInfo productInfo = new ProductInfo();
		model.addAttribute("optionCount",getAllOptionCounts(account));

		return this.formProduct(model, productInfo);
	}
	
	@RequestMapping("/customer/editProduct")
	public String editProduct(HttpServletRequest request,Model model, @RequestParam("id") long id) {
		
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());

		ProductInfo productInfo = null;
		if (id > 0) {
			productInfo = this.productDAO.findProductInfo(id);
		}
		if (productInfo == null) {
			return "redirect:/customer/myproducts";
		}
		model.addAttribute("optionCount",getAllOptionCounts(account));

		return this.formProduct(model, productInfo);
	}
	
	@RequestMapping("/customer/deleteProduct")
	public String deleteProduct(Model model, @RequestParam("id") long id) {
		if (id > 0) {
			this.productDAO.deleteProduct(id);
		}
		return "redirect:/customer/myproducts";
	}
	
	
	@SuppressWarnings("null")

	@RequestMapping(value ="/customer/rateSellerProduct" , method = RequestMethod.POST)
	public String followUser(HttpServletRequest request, Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		int rating=Integer.parseInt(request.getParameter("rating"));
		int productid=Integer.parseInt(request.getParameter("productid"));
		int orderid=Integer.parseInt(request.getParameter("orderid"));
		int sellerid=this.productDAO.findProduct(productid).getOwner();
		int userid=account.getId();
		  // System.out.println(account.getId());
        this.sellerratingDAO.save(orderid, productid, sellerid, userid, rating);
		// Redirect to shoppingCart page.
        return "redirect:/customer/placedorder?orderId="+orderid;
	}	
	// Save or update Product
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator

		@SuppressWarnings("null")
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/customer/saveProduct", method = RequestMethod.POST)

		public String saveProduct(HttpServletRequest request, HttpServletResponse response, Model model, //
				@ModelAttribute("productForm") @Validated ProductInfo productInfo, //
				BindingResult result, //
				final RedirectAttributes redirectAttributes) throws MessagingException {
			Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
			if (result.hasErrors()) {
				return formProduct(model, productInfo);
			}

			this.productDAO.saveProduct(productInfo,account.getId());

			// Important!!: Need @EnableWebMvc
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("message", "Save Product Successful");
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
			MailMail mm = (MailMail) context.getBean("mailMail");
			List<AccountInfo>	followersinfo=	followersDAO.listFollowersInfos(account.getId());
			String[] followersemails=new String[followersinfo.size()];int i=0;
			for(AccountInfo acc:followersinfo) {
				followersemails[i++]=acc.getEmail();
			}
		if(i!=0) {
			
			//System.out.println("Test Mail From Add Product "+followersemails[0]);
			mm.sendBulkMail("Customer", "Marche : New Product Added By Ram", account.getId(),followersemails );
		}
			return "redirect:/customer/myproducts";

		}
		
	
	
	
	/* products related code  end */
	
	@RequestMapping(value = { "/customer/wishlist" }, method = RequestMethod.GET)
	public String custwishlistPage(HttpServletRequest request,Model model) {

		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		List<ProductInfo> list = userWishlistDAO.listWishlistInfos(account.getId());
		model.addAttribute("productInfos", list);
		System.out.println("userWishlist productInfos");
		System.out.println(list.toString());
		model.addAttribute("optionCount",getAllOptionCounts(account));
        
        
		return "/customer/wishlist";
	}
	
	
	@RequestMapping(value = { "/customer/updateorderstatus" }, method = RequestMethod.GET)
	public String orderstatusUpdatepage(HttpServletRequest request,Model model,@RequestParam("orderId") int orderid,@RequestParam("orderstatus") int status,@RequestParam("pids") String pid ,final RedirectAttributes redirectAttributes) {
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

			msg="Thank you for shopping with us. We'd like to let you know that we received your product from, and is preparing it for shipment.";
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



		
		/*if(res>0)
			request.setAttribute("cmessage", "Order Confirmed");
			else
				request.setAttribute("cmessage", "Unable to Confirm Order");*/
			
		return "redirect:/customer/receivedorder?orderId="+orderid;
	}
	
/* orders functionality code  start */
	
	@RequestMapping(value = { "/customer/orders-placed" }, method = RequestMethod.GET)
	public String customerorderplacedPage(HttpServletRequest request,Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
			Account account = accountDAO.findAccount(request.getUserPrincipal().getName());

	        int page = 1;
	        try {
	            page = Integer.parseInt(pageStr);
	        } catch (Exception e) {
	        }
	        final int MAX_RESULT = 5;
	        final int MAX_NAVIGATION_PAGE = 10;
	 
	       /*PaginationResult<OrderInfo> paginationResult //
	        = orderDAO.listOrderInfoByEmail(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getEmail());*/
	        
	        List<OrderInfo> paginationResult = orderDAO.listOrderInfoByEmail(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getEmail());
	 
	        model.addAttribute("paginationResult", paginationResult);
	        model.addAttribute("optionCount",getAllOptionCounts(account));
	        		
		return "/customer/orders-placed";
	}
	
	
	@RequestMapping(value = { "/customer/orders-received" }, method = RequestMethod.GET)
	public String custorderrecPage(HttpServletRequest request,Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
			Account account = accountDAO.findAccount(request.getUserPrincipal().getName());

	        int page = 1;
	        try {
	            page = Integer.parseInt(pageStr);
	        } catch (Exception e) {
	        }
	        final int MAX_RESULT = 5;
	        final int MAX_NAVIGATION_PAGE = 10;
	 
	        List<OrderInfo> paginationResult = orderDAO.listOrderInfoByOwner(page, MAX_RESULT, MAX_NAVIGATION_PAGE,account.getId());
	 
	        model.addAttribute("paginationResult", paginationResult);
	        model.addAttribute("optionCount",getAllOptionCounts(account));

		return "/customer/orders-received";
	}
	
	@RequestMapping(value = { "/customer/receivedorder" }, method = RequestMethod.GET)
    public String receivedorderView(HttpServletRequest request,Model model, @RequestParam("orderId") String orderId) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/customer/orders-received";
        }
        List<OrderDetailInfo> details = orderDAO.listReceivedOrderDetailInfos(orderId,account.getId());
        orderInfo.setDetails(details);
        model.addAttribute("cmessage", (String) request.getAttribute("cmessage"));

        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("optionCount",getAllOptionCounts(account));
        return "/customer/receivedorder";
    }
	
	@RequestMapping(value = { "/customer/placedorder" }, method = RequestMethod.GET)
    public String orderView(HttpServletRequest request,Model model, @RequestParam("orderId") String orderId) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/customer/orders-placed";
        }
        List<OrderDetailInfo> details = orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
       
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("optionCount",getAllOptionCounts(account));
        return "/customer/placedorder";
    }
	
	
	@RequestMapping(value = { "/customer/orderstatus" }, method = RequestMethod.GET)
	public String customerorderstatusPage(HttpServletRequest request,Model model, @RequestParam("orderId") String orderId, @RequestParam("productId") long productId) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/customer/orders-placed";
        }
        List<OrderDetailInfo> details = orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
       for (int i=0;i<details.size();i++) {
       if(orderInfo.getDetails().get(i).getProductId()==productId)
           model.addAttribute("orderStatusId",orderInfo.getDetails().get(i).getStatusId());
       model.addAttribute("productId", productId);

       }
        model.addAttribute("orderId", orderId);
        model.addAttribute("optionCount",getAllOptionCounts(account));
		return "customer/orderstatus";
	}
	
	@RequestMapping(value = {"/customer/sendmsg"}, method = RequestMethod.GET)
	public String viewsendmsg(HttpServletRequest request,HttpServletRequest response,Model model,//
			@RequestParam("senderId") String id,@RequestParam("productId") String pid, //
			final RedirectAttributes redirectAttributes) {
	   int sid=Integer.parseInt(id);
	   long pid1=Integer.parseInt(pid);
	   Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
	   int rid=account.getId();
	   List<MessageInfo> myMessages = messageDAO.getMyMessages(rid,sid,pid1);
				    
	   Account account1= accountDAO.findRegistration(sid);
	   Products product1=productDAO.findProduct(pid1);
	   System.out.println(account1);
       System.out.println(product1);
	   System.out.println(account);
	  		    
	   model.addAttribute("messageForm", myMessages);
	   model.addAttribute("senderId", id);
	   model.addAttribute("productId",pid);
	   
	
	   MessageInfo newMessage = new MessageInfo();
	   model.addAttribute("newMessage", newMessage);
	
	
        return "customer/sendmsg";
	}


    @RequestMapping(value={ "/customer/sendmsg"},method=RequestMethod.POST )
	public String customerordersendmsg(HttpServletRequest request,HttpServletRequest response,Model model,//
			@ModelAttribute("newMessage") @Validated MessageInfo newMessage, BindingResult result,
			@RequestParam("senderId") String id,@RequestParam("productId") String pid,
			final RedirectAttributes redirectAttributes) {
	
	   if (result.hasErrors()) {
			//newMessage.setValid(false);
			// Forward to reenter Account info.
	        System.out.println("Validation of new message has been failed");
			return "customer/sendmsg";
	   }
	
	   int rId=Integer.parseInt(id);
	   long pId=Integer.parseInt(pid);
       Account sender = accountDAO.findAccount(request.getUserPrincipal().getName());
	//List<MessageInfo> myMessages = messageDAO.getMyMessages(rid,sid,pid1);
				    
	   Account recipient = accountDAO.findRegistration(rId);
	   Products product=productDAO.findProduct(pId);
	   this.messageDAO.save(newMessage,sender, recipient,product);
	   System.out.println(newMessage.getMessage());
       System.out.println(sender.getId());
	   System.out.println(recipient.getId());
	   System.out.println(product.getId());
	   redirectAttributes.addFlashAttribute("message", "Message sent succesfully");
	   //model.addAttribute("messageForm", myMessages);
       //this.messageDAO.save(messageInfo,sid,rid,pid1);
        return "redirect:/customer/inbox";
}
	
    @RequestMapping("/customer/inbox")
    public String custinboxPage(HttpServletRequest request, Model model) {
       Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
       List<MessageInfo> myMessages = messageDAO.getMyLatestMessages(account.getId());
       model.addAttribute("inbox", myMessages);
	   return "customer/inbox";
}
	/* orders functionality code  end */
	
}

