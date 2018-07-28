package com.amazon.market.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.entity.Products;
import com.amazon.market.model.AccountInfo;
import com.amazon.market.model.ApplicantInfo;
import com.amazon.market.model.CartInfo;
import com.amazon.market.model.CategoryInfo;
import com.amazon.market.model.MessageInfo;
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
public class AdminController {

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

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		
	}

	@RequestMapping("/admin/view-profile")
	public String adminaccountPage(Model model) {

		return "admin/view-profile";
	}

	@RequestMapping("/admin/deliveryboys")
	public String admindelboysPage(Model model) {

		return "/admin/deliveryboys";
	}
	
	@RequestMapping("/admin/category")
	public String admincategoryPage(Model model) {

		return "/admin/category";
	}
	@RequestMapping("/admin/deliveryboys-monitor")
	public String admindmonPage(Model model) {

		return "admin/deliveryboys-monitor";
	}

	@RequestMapping("/admin/edit-deliveryboys")
	public String adminupdatedelPage(Model model) {

		return "/admin/edit-deliveryboys";
	}
	@RequestMapping("/admin/monitor-orders")
	public String adminordersPage(Model model) {

		return "admin/monitor-orders";
	}

	@RequestMapping("/admin/product-monitor")
	public String adminproductsPage(Model model) {

		return "/admin/product-monitor";
	}
	
	@RequestMapping("/admin/users")
	public String adminusersPage(Model model) {

		return "admin/users";
	}

	@RequestMapping("/admin/change-password")
	public String adminchangepwdPage(Model model) {

		return "/admin/change-password";
	}
}

