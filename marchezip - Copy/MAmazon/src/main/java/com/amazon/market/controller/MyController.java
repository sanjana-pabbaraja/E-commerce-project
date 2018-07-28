package com.amazon.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.amazon.market.dao.FollowersDAO;
import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.SellerratingDAO;
import com.amazon.market.dao.SubcategoryDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.dao.UserWishlistDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products;
import com.amazon.market.entity.SellerRating;
import com.amazon.market.entity.UserAddress;
import com.amazon.market.entity.UserWishlist;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.CartInfo;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.CustomerInfo;
import com.amazon.market.model.LocationzonesInfo;
import com.amazon.market.model.MessageInfo;
import com.amazon.market.model.ProductInfo;
import com.amazon.market.model.SubcategoryInfo;
import com.amazon.market.model.UserAddressInfo;
import com.amazon.market.util.Utils;
import com.amazon.market.validator.AccountInfoValidator;
import com.amazon.market.validator.ApplicantValidator;
import com.amazon.market.validator.ProductInfoValidator;
import com.amazon.market.validator.UserAddressInfoValidator;
import com.amazon.pay.Client;
import com.amazon.pay.Config;
import com.amazon.pay.impl.PayClient;
import com.amazon.pay.impl.PayConfig;
import com.amazon.pay.request.AuthorizeRequest;
import com.amazon.pay.request.ConfirmOrderReferenceRequest;
import com.amazon.pay.request.GetOrderReferenceDetailsRequest;
import com.amazon.pay.request.SetOrderReferenceDetailsRequest;
import com.amazon.pay.response.parser.AuthorizeResponseData;
import com.amazon.pay.response.parser.GetOrderReferenceDetailsResponseData;
import com.amazon.pay.types.CurrencyCode;
import com.amazon.pay.types.Region;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need To use RedirectAttributes
@EnableWebMvc
@SessionAttributes("cartObj")
@javax.servlet.annotation.MultipartConfig
public class MyController {

	@Autowired
	private ApplicantDAO applicantDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private UserAddressDAO useraddressDAO;

	@Autowired
	private ApplicantValidator applicantValidator;

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	FollowersDAO followersDAO;
	

	private AccountInfoValidator accountInfoValidator;

	@Autowired
	private ProductInfoValidator productInfoValidator;
	
	@Autowired
	private UserAddressInfoValidator useraddressInfoValidator;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
    private LocationZonesDAO locationZonesDAO; 
	
	@Autowired
	UserWishlistDAO userWishlistDAO;
	
	@Autowired
	SellerratingDAO sellerrattingDAO;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		
		// For User address Form.
	       // (@ModelAttribute("useraddressForm") @Validated UserAddressInfo
	       // useraddressForm)
	       if (target.getClass() == UserAddressInfo.class) {
	           dataBinder.setValidator(useraddressInfoValidator);
	       }
	       // For Registration Form.
	       // (@ModelAttribute("registrationForm") @Validated AccountInfo
	       // registrationForm)
	       else if (target.getClass() == AccountInfo.class) {
	           dataBinder.setValidator(accountInfoValidator);
	       }
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@RequestMapping("/text")
	public String text() {
		return "text";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/FAQ")
	public String FAQ() {
		return "FAQ";
	}
	@RequestMapping("/forgotpassword")
	public String forgotpwdPage() {
		return "forgotpassword";
	}
	
	@RequestMapping("/contactSeller")
	public String contactseller() {
		return "contactSeller";
	}

	@RequestMapping("/termsandc")
	public String termsandc() {
		return "termsandc";
	}
	@RequestMapping("/aboutus")
	public String aboutus() {
		return "aboutus";
	}
	
	@RequestMapping({ "/unfollow" })
	public String unFollowUser(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") String id) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		System.out.println("followUser id is "+id);
		boolean follower = false;
		int sellerid = Integer.parseInt(id);
		if (sellerid > 0) {
			follower = followersDAO.findUserFollower(account.getId(),sellerid);
		}
		if (follower) {//if he is not in a follower , then save
			followersDAO.unfollow(account.getId(),sellerid);
			System.out.println("he is already following , so un following now");
		}
		// Redirect to shoppingCart page.
		return "redirect:/customer/followings";
	}

	
	@RequestMapping({ "/follow" })
	public String followUser(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") String id) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		System.out.println("followUser id is "+id);
		boolean follower = false;
		int sellerid = Integer.parseInt(id);
		if (sellerid > 0) {
			follower = followersDAO.findUserFollower(account.getId(),sellerid);
		}
		if (!follower) {//if he is not in a follower , then save

			followersDAO.save(account.getId(),sellerid);
			System.out.println("he is already following , so not saving");
		}else {
			System.out.println("followersDAO.save , saving");
		}
		// Redirect to shoppingCart page.
		return "redirect:/customer/followings";
	}
	
	
	// update user password
			@RequestMapping(value = { "/updatepwd" }, method = RequestMethod.GET)
			public String updatepwd(HttpServletRequest request,Model model, @RequestParam("oldpwd") String cpwd, @RequestParam("newpwd") String npwd,final RedirectAttributes redirectAttributes) {
				Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
			    int res=accountDAO.changePassword(account.getId(),cpwd,npwd);
			    System.out.println(res);
				if(res==1)
				model.addAttribute("cmessage", "Password updated");
				else
				model.addAttribute("cmessage", "Unable to update Password");
				return "/customer/changepassword";
			}
	
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest request,Model model) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		System.out.println("this is == "+account.getUserRole());
		System.out.println(account.getUserRole().equals("DELIVERYBOY"));
		System.out.println(account.getUserRole().equals("REGISTEREDUSER"));
		if(account.getUserRole().equals("DELIVERYBOY")) {
			System.out.println("this is delivery boy");
			return "redirect:/deliveryboy/account";
		}
		if(account.getUserRole().equals("REGISTEREDUSER")) {
			System.out.println("this is REGISTEREDUSER");
			return "redirect:/customer/account";
		}
		return "redirect:/login";
	}
	
	
	
	
	@RequestMapping("/detail")
	public String detailPage(Model model, @RequestParam("id") String id) {
		
		int pid=Integer.parseInt(id);
		ProductInfo prodInfo = new ProductInfo();
		if(pid>0) {
			prodInfo = productDAO.findProductInfo(pid);
		}
		Map<Long, String> categoryMap = this.dataForCategory();
		Map<Integer, List<SubcategoryInfo>> subcategoryList = subcategoryDAO.listSubCategoryInfos();
		System.out.println("=========category======");
		Account sacc=accountDAO.findRegistration((int) prodInfo.getOwner());
		//System.out.println(categoryMap.get(0));
		//System.out.println(subcategoryList.get(categoryMap.get(0)));
		System.out.println(subcategoryList.toString());
		
		String sellerrat=sellerrattingDAO.getRatingbyseller((int) prodInfo.getOwner());
		model.addAttribute("sellerRating", sellerrat);

		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("subcategoryList", subcategoryList);
		model.addAttribute("productInfo", prodInfo);
		model.addAttribute("saccount",sacc);
		//System.out.println(sacc.getUserName()+" "+sacc.getId());

		return "detail";
	}
	
	
	@RequestMapping("/basket")
	public String basketPage(Model model) {

		return "basket";
	}
	
	@RequestMapping("/shoppingCart")
	public String cart() {
		return "shoppingCart";
	}
	
	@RequestMapping("/seller")
	public String sellerproList(HttpServletRequest request,Model model, @RequestParam("id") String id) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		int sid=Integer.parseInt(id);
		List<ProductInfo> list;
		if(sid>0) {
			 list = productDAO.listProductInfosByUid(sid);
		}else {
			 list = productDAO.listProductInfos();	
		}
		Account sacc=accountDAO.findRegistration(sid);
		model.addAttribute("saccount",sacc);
		model.addAttribute("sellerProducts", list);
		boolean followFlag = followersDAO.findUserFollower(account.getId(),sid);
		model.addAttribute("sellerProducts", list);
		String sellerrat=sellerrattingDAO.getRatingbyseller(sid);
		model.addAttribute("sellerRating", sellerrat);

		if(!followFlag) {
			model.addAttribute("followFlag", true);
		}else {
			model.addAttribute("followFlag", false);

		}
		return "seller";
	}
	

	@RequestMapping("/searchresults")
	public String searchresults(Model model, @RequestParam("name") String name, @RequestParam("category") String cid, @RequestParam("myCountry") String loc) {
		
		int catid=Integer.parseInt(cid);
		int locid=locationZonesDAO.listLocationidByname(loc);
		if(catid<0) {
			catid=0;
		}
		if(locid<0) {
			locid=0;
		}
		System.out.println(locid);
		List<ProductInfo> list;
		
			 list = productDAO.listProductInfosByName(name,catid,locid);
		
		
		model.addAttribute("productInfos", list);
		
		
		
		return "searchresults";
	}
	
	
	@RequestMapping("/index")
	public String homePage(HttpServletRequest request,Model model) {
		
		List<ProductInfo> list;
		
		 list = productDAO.listLatestProducts();
		 
		 
		// System.out.println(" home page index");
		//System.out.println(request.getUserPrincipal());
		//System.out.println(request.getUserPrincipal().getName());
		if(request.getUserPrincipal()!=null) {
			Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
			List<ProductInfo> list1;
			
			 list1 = productDAO.listRecommendedProducts( account.getEmail());
			
			model.addAttribute("RecommandedProducts", list1);
		}
			
	
		model.addAttribute("LatestProducts", list);
		return "index";
	}
	
	@RequestMapping("/orderplaced")
	public String orderplacedPage(Model model) {
	
		return "orderplaced";
	}
	
	
	// GET: Show Login Page
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping("/applicantList")
	public String applicantList(Model model) {
		List<ApplicantInfo> list = applicantDAO.listApplicantInfos();
		model.addAttribute("applicantInfos", list);
		return "applicantList";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}

	// GET: Enter User information.
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String registeruser(HttpServletRequest request, Model model) {
		AccountInfo registrationInfo;
		registrationInfo = new AccountInfo();
		model.addAttribute("registrationForm", registrationInfo);
		return "register";
	}

	// POST: Save User information.
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registeruser(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("registrationForm") @Validated AccountInfo registrationForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		System.out.println(result.getAllErrors().toString());
		// If has Errors.
		if (result.hasErrors()) {
			registrationForm.setValid(false);
			// Forward to reenter Account info.
			return "register";
		} else {

			try {
				registrationForm.setValid(true);
				System.out.println("registration--");
				System.out.println(accountDAO.findUsername(registrationForm));
				System.out.println(registrationForm.toString());
				System.out.println("registration--");
				if (accountDAO.findUsername(registrationForm)) {
					redirectAttributes.addFlashAttribute("message", "User account for this email already exists!");
					return "redirect:/register";
				} else {
					accountDAO.save(registrationForm);
					redirectAttributes.addFlashAttribute("message", "Successfully created your User account.");
					return "redirect:/login";
				}
			} catch (Exception e) {
				// Need: Propagation.NEVER?
				String message = e.getMessage();
				redirectAttributes.addFlashAttribute("message", message);
				// Redirect to Confirmation page.
				return "redirect:/register";
			}
		}

		// Redirect to Confirmation page.
		// return "redirect:/login";
	}
	private Map<Long, String> dataForCategory() {
		List<CategoryInfo> list = categoryDAO.listCategoryInfos();
		System.out.println(list.size());
		Map<Long, String> categoryMap = new LinkedHashMap<Long, String>();
		for (CategoryInfo categoryInfo : list) {
			categoryMap.put(categoryInfo.getId(), categoryInfo.getcategoryName());
		}
		return categoryMap;
	}
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		List<ProductInfo> list = productDAO.listProductInfos();
		model.addAttribute("productInfos", list);
		Map<Long, String> categoryMap = this.dataForCategory();
		model.addAttribute("categoryMap", categoryMap);
		return "productList";
	}
	
	@RequestMapping("/category")
	public String categoryList(Model model, @RequestParam("id") String id) {
		
		int cid=Integer.parseInt(id);
		List<ProductInfo> list;
		if(cid>0) {
			 list = productDAO.listProductInfosByCateogory(cid);
		}else {
			 list = productDAO.listProductInfos();	
		}
		model.addAttribute("productInfos", list);
		Map<Long, String> categoryMap = this.dataForCategory();
		Map<Integer, List<SubcategoryInfo>> subcategoryList = subcategoryDAO.listSubCategoryInfos();
		System.out.println("=========category======");
		//System.out.println(categoryMap.get(0));
		//System.out.println(subcategoryList.get(categoryMap.get(0)));
		System.out.println(subcategoryList.toString());
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("subcategoryList", subcategoryList);
		return "category";
	}
	
	@RequestMapping("/subcategory")
	public String subcategoryList(Model model, @RequestParam("id") String id) {
		
		int cid=Integer.parseInt(id);
		List<ProductInfo> list;
		if(cid>0) {
			 list = productDAO.listProductInfosBySubateogory(cid);
		}else {
			 list = productDAO.listProductInfos();	
		}
		model.addAttribute("productInfos", list);
		Map<Long, String> categoryMap = this.dataForCategory();
		Map<Integer, List<SubcategoryInfo>> subcategoryList = subcategoryDAO.listSubCategoryInfos();
		System.out.println("=========category======");
		//System.out.println(categoryMap.get(0));
		//System.out.println(subcategoryList.get(categoryMap.get(0)));
		System.out.println(subcategoryList.toString());
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("subcategoryList", subcategoryList);
		return "category";
	}

	private Map<String, String> dataForPositions() {
		Map<String, String> positionMap = new LinkedHashMap<String, String>();
		positionMap.put("Developer", "Developer");
		positionMap.put("Leader", "Leader");
		positionMap.put("Tester", "Tester");
		return positionMap;
	}

	

	private List<String> dataForSkills() {
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("C/C++");
		list.add("C#");
		return list;
	}

	private String formApplicant(Model model, ApplicantInfo applicantInfo) {
		model.addAttribute("applicantForm", applicantInfo);

		Map<String, String> positionMap = this.dataForPositions();

		model.addAttribute("positionMap", positionMap);

		List<String> list = dataForSkills();
		model.addAttribute("skills", list);

		if (applicantInfo.getId() == null) {
			model.addAttribute("formTitle", "Create Applicant");
		} else {
			model.addAttribute("formTitle", "Edit Applicant");
		}

		return "formApplicant";
	}

	

	@RequestMapping("/createApplicant")
	public String createApplicant(Model model) {

		ApplicantInfo applicantInfo = new ApplicantInfo();

		return this.formApplicant(model, applicantInfo);
	}

	

	@RequestMapping("/editApplicant")
	public String editApplicant(Model model, @RequestParam("id") String id) {
		ApplicantInfo applicantInfo = null;
		if (id != null) {
			applicantInfo = this.applicantDAO.findApplicantInfo(id);
		}
		if (applicantInfo == null) {
			return "redirect:/applicantList";
		}

		return this.formApplicant(model, applicantInfo);
	}

	

	@RequestMapping("/deleteApplicant")
	public String deleteApplicant(Model model, @RequestParam("id") String id) {
		if (id != null) {
			this.applicantDAO.deleteApplicant(id);
		}
		return "redirect:/applicantList";
	}

	

	@RequestMapping({ "/addtoCart" })
	public String listProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") long id) {

		Products product = null;
		if (id > 0) {
			product = productDAO.findProduct(id);
		}
		if (product != null) {

			// Cart info stored in Session.
			CartInfo cartInfo = Utils.getCartInSession(request);

			ProductInfo productInfo = new ProductInfo(product.getId(), product.getProductName(),
					product.getCategoryId(), product.getProductDescription(), product.getPrice(), null);

			cartInfo.addProduct(productInfo, 1);
		}
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart";
	}
	
	
	@RequestMapping({ "/addtoWishlist" })
	public String addtoWishlistHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") long id) {
		Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		
		boolean wishlistproduct = false;
		if (id > 0) {
			wishlistproduct = userWishlistDAO.findUserWishlist(id, account.getId());
		}
		if (!wishlistproduct) {//if it is not in wishlist , then save

			userWishlistDAO.save(id, account.getId());
		}else {
			System.out.println("product already in wishlist , so not saving");
		}
		// Redirect to shoppingCart page.
		return "redirect:/customer/wishlist";
	}
	
	/*@RequestMapping(value={ "/contactSeller" }, method = RequestMethod.GET)
	public String contactSeller(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") int id) {

		
		Account reciepient=this.accountDAO.findRegistration(id);
		Account sender=this.accountDAO.findAccount(request.getUserPrincipal().getName());
        model.addAttribute("reciepient", reciepient);
		
		model.addAttribute("sender", sender);
		List<MessageInfo> messages=this.messageDAO.getMyLatestMessages(sender);
		model.addAttribute("listMessages", messages);
		// Redirect to shoppingCart page.
		return "contactSeller";
	}*/
	/*@RequestMapping(value={ "/contactSeller" }, method = RequestMethod.POST)
	public String contactSellerMessage(HttpServletRequest request, Model model, //
			@RequestParam(value = "reciepient") int reciepient,@RequestParam(value = "message") String message) {

		Account reciepientac=this.accountDAO.findRegistration(Integer.parseInt(request.getParameter("reciepient")));
		Account sender=this.accountDAO.findAccount(request.getUserPrincipal().getName());
        model.addAttribute("reciepient", reciepientac);
		
		model.addAttribute("sender", sender);
		model.addAttribute("message", "Your message has been sent.");
		this.messageDAO.sendMessageToThisUser(Integer.parseInt(request.getParameter("reciepient")), message, sender.getId());
		
		// Redirect to shoppingCart page.
		return "contactSeller";
	}*/

	// POST: Update quantity of products in cart.
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
	public String shoppingCartUpdateQty(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("cartForm") CartInfo cartForm) {

		CartInfo cartInfo = Utils.getCartInSession(request);
		System.out.println(cartInfo.getQuantityTotal());
		cartInfo.updateQuantity(cartForm);

		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart";
	}

	// GET: Show Cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		CartInfo myCart = Utils.getCartInSession(request);

		
		/*ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	       MailMail mm = (MailMail) context.getBean("mailMail");
	       mm.sendMail("Mahesh","Payment recieved for order no. -"+" 541 ", "Your Order has been recieved and payment has been recieved.Payment of Rs.541 recieved towards for order no.541 ","mailtojuffa@gmail.com");
		*/
		model.addAttribute("cartObj", myCart);
		model.addAttribute("cartForm", myCart);
		return "shoppingCart";
	}

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == ApplicantInfo.class) {
			dataBinder.setValidator(applicantValidator);
		}
	}

	// Save or update Applicant
	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value = "/saveApplicant", method = RequestMethod.POST)
	public String saveApplicant(Model model, //
			@ModelAttribute("applicantForm") @Validated ApplicantInfo applicantInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return this.formApplicant(model, applicantInfo);
		}

		this.applicantDAO.saveApplicant(applicantInfo);

		// Important!!: Need @EnableWebMvc
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("message", "Save Applicant Successful");

		return "redirect:/applicantList";

	}

	@RequestMapping({ "/removefromCart" })
	public String removeProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") long id) {
		Products product = null;
		if (id > 0) {
			product = productDAO.findProduct(id);
		}
		if (product != null) {

			// Cart Info stored in Session.
			CartInfo cartInfo = Utils.getCartInSession(request);

			ProductInfo productInfo = new ProductInfo(product.getId(), product.getProductName(),
					product.getCategoryId(), product.getProductDescription(), product.getPrice(), null);

			cartInfo.removeProduct(productInfo);

		}
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart";
	}
	

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") long id) throws IOException {
		Products product = null;
		if (id > 0) {
			product = this.productDAO.findProduct(id);
		}
		if (product != null && product.getProductImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getProductImage());
		}
		response.getOutputStream().close();
	}
	
	 /* order place or checkout start */
	// GET: Enter customer information.
	   @SuppressWarnings("null")
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	   public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

	       CartInfo cartInfo = Utils.getCartInSession(request);
	     
	       // Cart is empty.
	       if (cartInfo.isEmpty()) {
	            
	           // Redirect to shoppingCart page.
	           return "redirect:/shoppingCart";
	       }
	       System.out.println("current loggedin userid "+request.getUserPrincipal().getName());
	       Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
	       System.out.println("current loggedin userid "+account.getId());
	       
	       model.addAttribute("currentUser", account.getUserName());

	       List<UserAddressInfo> useraddrInfoList = useraddressDAO.listUserAddressInfos(account.getId());
	       
	       System.out.println(account.getId());
	     //  System.out.println(useraddress.getFirstname());
	       UserAddressInfo useraddressInfo = new UserAddressInfo();
	       if (useraddrInfoList.size()>0) {	    	   
	    	            useraddressInfo.setUserid(useraddrInfoList.get(0).getUserid());	    	    
	           	    	useraddressInfo.setFirstname(useraddrInfoList.get(0).getFirstname());
	           	    	useraddressInfo.setLastname(useraddrInfoList.get(0).getLastname());
	           	    	useraddressInfo.setDoorno(useraddrInfoList.get(0).getDoorno());
	           	    	useraddressInfo.setStreet(useraddrInfoList.get(0).getStreet());
	           	    	useraddressInfo.setArea(useraddrInfoList.get(0).getArea());
	           	    	useraddressInfo.setCity(useraddrInfoList.get(0).getCity());
	           	    	useraddressInfo.setState(useraddrInfoList.get(0).getState());
	           	    	useraddressInfo.setPincode(useraddrInfoList.get(0).getPincode());
           	           
           	       }

	       model.addAttribute("useraddressForm", useraddressInfo);
	       model.addAttribute("cartInfo", cartInfo);

	       return "checkout1";//"shoppingCartCustomer";
	   }
	   
	   
	// POST: Save customer information.
	   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	   public String shoppingCartCustomerSave(HttpServletRequest request, //
	           Model model, //
	           @ModelAttribute("useraddressForm") @Validated UserAddressInfo useraddressForm, //
	           BindingResult result, //
	           final RedirectAttributes redirectAttributes) {
		   Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
	       // If has Errors.
	       if (result.hasErrors()) {
	    	   useraddressForm.setValid(false);
	           // Forward to reenter customer info.
	           return "checkout1";//"shoppingCartCustomer";
	       }

	       useraddressForm.setValid(true);
	       CartInfo cartInfo = Utils.getCartInSession(request);
	       useraddressForm.setUserid(account.getId());
	       
	       CustomerInfo customerInfo = cartInfo.getCustomerInfo();
	       if (customerInfo == null) {
	           customerInfo = new CustomerInfo();
	           customerInfo.setName(account.getUserName());
	           customerInfo.setPhone(account.getMobileno());
	           customerInfo.setEmail(account.getEmail());
	       }
	       
	       customerInfo.setValid(true);
	       cartInfo.setCustomerInfo(customerInfo);
	       cartInfo.setUserAddressInfo(useraddressForm);
	       //useraddressDAO.save(useraddressForm);
	       
	       model.addAttribute("cartInfo", cartInfo);
	       // Redirect to Confirmation page.
	       return "checkout2";
	   }
	   
	   
	// POST: Get payment method information.
	   @RequestMapping(value = { "/shoppingCartPayment" }, method = RequestMethod.POST)
	   public String shoppingCartCustomerSave(HttpServletRequest request, //
	           Model model) {
		   System.out.println(request.getParameter("payment"));
		   //Account account = accountDAO.findAccount(request.getUserPrincipal().getName());
		   CartInfo myCart = Utils.getCartInSession(request);

			model.addAttribute("cartForm", myCart);
		   model.addAttribute("paymentMethod", request.getParameter("payment"));
		   model.addAttribute("cartInfo", myCart);
	       // Redirect to Confirmation page.
	       return "shoppingCartConfirmation";//"redirect:/shoppingCartConfirmation";
	   }
	   
	// GET: Review Cart to confirm.
	   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
	       CartInfo cartInfo = Utils.getCartInSession(request);
	       
	       System.out.println("get shopping cart confirmation");
	       System.out.println(cartInfo.getAmountTotal());
	       System.out.println(cartInfo.isValidCustomer());
	       
	       // Cart have no products.
	       if (cartInfo.isEmpty()) {
	           // Redirect to shoppingCart page.
	           return "redirect:/shoppingCart";
	       } else if (!cartInfo.isValidCustomer()) {
	           // Enter customer info.
	           return "redirect:/shoppingCartCustomer";
	       }
	       model.addAttribute("cartInfo", cartInfo);
	       return "shoppingCartConfirmation";
	   }
	
	// POST: Send Cart (Save).
	   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	   // Avoid UnexpectedRollbackException (See more explanations)
	   @Transactional(propagation = Propagation.NEVER)
	   public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
	       CartInfo cartInfo = Utils.getCartInSession(request);
	       System.out.println("POST shopping cart confirmation");
	       // Cart have no products.
	       if (cartInfo.isEmpty()) {
	           // Redirect to shoppingCart page.
	           return "redirect:/shoppingCart";
	       } else if (!cartInfo.isValidCustomer()) {
	           // Enter customer info.
	           return "redirect:/shoppingCartCustomer";
	       }
	       try {
	           orderDAO.saveOrder(cartInfo);
	       } catch (Exception e) {
	           // Need: Propagation.NEVER?
	    	   model.addAttribute("exceptionMsg", e.getMessage());
	           return "redirect:/shoppingCartConfirmation";
	       }
	       // Remove Cart In Session.
	       Utils.removeCartInSession(request);
	        
	       // Store Last ordered cart to Session.
	       Utils.storeLastOrderedCartInSession(request, cartInfo);

	       ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	       MailMail mm = (MailMail) context.getBean("mailMail");
	       System.out.println("cemail");
	       mm.sendMail(cartInfo.getCustomerInfo().getName(),"Payment recieved for order no. -"+" "+cartInfo.getOrderNum(), "Your Order has been recieved and payment has been recieved.Payment of Rs."+cartInfo.getAmountTotal()+" recieved towards for order no."+cartInfo.getOrderNum(),cartInfo.getCustomerInfo().getEmail());
	       // Redirect to successful page.
	       return "orderplaced";
	   }
	   
	   @RequestMapping(value = { "/orderplaced" }, method = RequestMethod.GET)
	   public String orderplaced(HttpServletRequest request, Model model) {

	       CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

	       if (lastOrderedCart == null) {
	           return "redirect:/shoppingCart";
	       }
	       
	       
	       return "orderplaced";
	   }
	   
	   /* payment start */
	   
	   // GET: Show Address and Payment Widgets
	   @RequestMapping(value = { "/Widgets" }, method = RequestMethod.GET)
	   public String Widgets(HttpServletRequest request, Model model,@RequestParam("access_token") String access_token,@RequestParam("token_type") String token_type,@RequestParam("expires_in") String expires_in,@RequestParam("scope") String scope) {
	       CartInfo myCart = Utils.getCartInSession(request);
	       model.addAttribute("cartForm", myCart);
	       return "Widgets";
	   }
	   
	// GET: Show Authorization Confirm
	   @RequestMapping(value = { "/AuthorizeandConfirm" }, method = RequestMethod.POST)
	   public String Widgets(HttpServletRequest request, Model model,@RequestParam("consent_token") String access_token,@RequestParam("oro_id") String oro_id,@RequestParam("carttotal") String carttotal) {
	       
	   	CartInfo cartInfo = Utils.getCartInSession(request);
	   	boolean success=false;
	              
	       String consentToken = request.getParameter("consent_token");
	       String orderReferenceId = request.getParameter("oro_id");
	       String amount = request.getParameter("carttotal");
	         
	       Config configkey = new PayConfig()
	             .withSellerId("A3GTZ3FSK3A6SJ")
	             .withAccessKey("AKIAIT3SWX5AWCIOAUXA")
	             .withSecretKey("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")
	             .withSandboxMode(true)
	             .withRegion(Region.US)
	             .withCurrencyCode(CurrencyCode.USD);
	       
	       try {
	           Client client = new PayClient(configkey);


	           //out.println("<p>Making a GetOrderReferenceDetails API Call. Usually you call GetOrderReferenceDetails before calling a SetOrderReferenceDetails to check Order details and also get a Zipcode/shipping_address to calculate shipping tax.</p>");

	           final GetOrderReferenceDetailsRequest getOrderReferenceDetailsRequest  = new GetOrderReferenceDetailsRequest(orderReferenceId);
	           getOrderReferenceDetailsRequest.setAccessToken(consentToken);
	           final GetOrderReferenceDetailsResponseData responsOrderRef = client.getOrderReferenceDetails(getOrderReferenceDetailsRequest);
	           //out.println("<pre id='confirm'>" + responsOrderRef.toXML().replace("<", "&lt;").replace(">", "&gt;") + "</pre>");


	           SetOrderReferenceDetailsRequest setOrderReferenceDetailsRequest = new SetOrderReferenceDetailsRequest(orderReferenceId, amount);
	           setOrderReferenceDetailsRequest.setCustomInformation("Java SDK OneTime Checkout Sample");
	           setOrderReferenceDetailsRequest.setStoreName("Test Store");
	           setOrderReferenceDetailsRequest.setSellerNote("1st Amazon Pay OneTime Checkout Order");
	           client.setOrderReferenceDetails(setOrderReferenceDetailsRequest);

	           ConfirmOrderReferenceRequest confirmOrderReferenceRequest = new ConfirmOrderReferenceRequest(orderReferenceId);
	           client.confirmOrderReference(confirmOrderReferenceRequest);


	           String uniqueAuthorizationRefereneceId = UUID.randomUUID().toString().replace("-", "");
	           AuthorizeRequest authorizeRequest = new AuthorizeRequest(orderReferenceId,
	                   uniqueAuthorizationRefereneceId, amount);
	           authorizeRequest.setTransactionTimeout("0");
	           authorizeRequest.setCaptureNow(true);
	           AuthorizeResponseData authResponse = client.authorize(authorizeRequest);

	           //out.println("<p>The <i>Authorize</i> API call will authorize the order reference.<br />The Capture API call will capture the funds for the given order reference id. If you want to make a separate <i>Capture</i> API call, you can set the <strong>CaptureNow</strong> parameter to <strong>false</strong> and then make a Capture call to collect funds.</p><pre id='confirm'>" + authResponse.toXML().replace("<", "&lt;").replace(">", "&gt;") + "</pre>");

	           //out.println("<p>To get <i>Amazon Authorization Id</i>, add the following code snippet.</p><pre>String amazonAuthorizationId = authResponse.getDetails().getAmazonAuthorizationId();<br /></pre>");


	           final GetOrderReferenceDetailsRequest getOrderReferenceDetailsRequest2  = new GetOrderReferenceDetailsRequest(orderReferenceId);
	           final GetOrderReferenceDetailsResponseData orderrefResponse_2 = client.getOrderReferenceDetails(getOrderReferenceDetailsRequest2);
	           //out.println("<pre id='confirm'>" + orderrefResponse_2.toXML().replace("<", "&lt;").replace(">", "&gt;") + "</pre>");
	           //out.println("<pre>You have paid , $"+orderrefResponse_2.getDetails().getOrderTotal()+".</pre>");
	           //out.println("<pre>Thank You , "+orderrefResponse_2.getDetails().getBuyer().getName()+". Your Order Placed Successfully</pre>");
	        // Cart have no products.
	           
	           success=true;

	       } catch (Exception e) {
	           e.printStackTrace();
	           return "redirect:/paymentFailure";
	           //return "redirect:/shoppingCart";
	           //out.println(e.getMessage());
	       }
	       if(success) {
	       	try {
	               orderDAO.saveOrder(cartInfo);
	           } catch (Exception e) {
	               // Need: Propagation.NEVER?
	               return "shoppingCartConfirmation";
	           }
	       	// Remove Cart In Session.
	           Utils.removeCartInSession(request);
	           // Store Last ordered cart to Session.
	           Utils.storeLastOrderedCartInSession(request, cartInfo);
	           // Redirect to successful page.
	           return "redirect:/orderplaced";
	       }else {
	       	return "redirect:/paymentFailure";
	       }
	    
	   }//end of authorize and confirm
	   
	   @RequestMapping("/paymentFailure")
	   public String paymentFailure() {
	       return "paymentFailure";
	   }
	   
	   /* payment end */
	
	
}