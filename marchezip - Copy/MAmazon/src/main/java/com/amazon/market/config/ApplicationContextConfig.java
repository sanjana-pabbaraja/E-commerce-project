package com.amazon.market.config;
 
import java.util.Properties;
 
import javax.sql.DataSource;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.amazon.market.dao.AccountDAO;
import com.amazon.market.dao.ApplicantDAO;
import com.amazon.market.dao.CategoryDAO;
import com.amazon.market.dao.DeliverypersonZoneDAO;
import com.amazon.market.dao.FollowersDAO;
import com.amazon.market.dao.LocationZonesDAO;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.dao.OrderDAO;
import com.amazon.market.dao.OrderStatusDAO;
import com.amazon.market.dao.ProductDAO;
import com.amazon.market.dao.SellerratingDAO;
import com.amazon.market.dao.SubcategoryDAO;
import com.amazon.market.dao.UserAddressDAO;
import com.amazon.market.dao.UserWishlistDAO;
import com.amazon.market.dao.impl.AccountDAOImpl;
import com.amazon.market.dao.impl.ApplicantDAOImpl;
import com.amazon.market.dao.impl.CategoryDAOImpl;
import com.amazon.market.dao.impl.DeliverypersonZoneDAOImpl;
import com.amazon.market.dao.impl.FollowersDAOImpl;
import com.amazon.market.dao.impl.LocationZonesDAOImpl;
import com.amazon.market.dao.impl.MessageDAOImpl;
import com.amazon.market.dao.impl.OrderDAOImpl;
import com.amazon.market.dao.impl.OrderStatusDAOImpl;
import com.amazon.market.dao.impl.ProductDAOImpl;
import com.amazon.market.dao.impl.SellerratingDAOImpl;
import com.amazon.market.dao.impl.SubcategoryDAOImpl;
import com.amazon.market.dao.impl.UserAddressDAOImpl;
import com.amazon.market.dao.impl.UserWishlistDAOImpl;
import com.amazon.market.model.ProductInfo;
 
@Configuration
@ComponentScan("com.amazon.market.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {
 
 // The Environment class serves as the property holder
 // and stores all the properties loaded by the @PropertySource
 @Autowired
 private Environment env;
 
 
 
 @Bean
 public ResourceBundleMessageSource messageSource() {
     ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
     // Load property in message/validator.properties
     rb.setBasenames(new String[] { "messages/validator"});
     return rb;
 }
 
 
 @Bean(name = "viewResolver")
 public InternalResourceViewResolver getViewResolver() {
     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
     viewResolver.setPrefix("/WEB-INF/pages/");
     viewResolver.setSuffix(".jsp");
     return viewResolver;
 }
 
 @Bean(name = "dataSource")
 public DataSource getDataSource() {
     DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
     dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
     dataSource.setUrl(env.getProperty("ds.url"));
     dataSource.setUsername(env.getProperty("ds.username"));
     dataSource.setPassword(env.getProperty("ds.password"));
 
     return dataSource;
 }
 
 @Autowired
 @Bean(name = "sessionFactory")
 public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
     Properties properties = new Properties();
    
     // See: ds-hibernate-cfg.properties
     properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
     properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
     properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
      
 
     LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
     factoryBean.setPackagesToScan(new String[] { "com.amazon.market.entity" });
     factoryBean.setDataSource(dataSource);
     factoryBean.setHibernateProperties(properties);
     factoryBean.afterPropertiesSet();
     //
     SessionFactory sf = factoryBean.getObject();
     return sf;
 }
 
 @Autowired
 @Bean(name = "transactionManager")
 public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
     HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
     return transactionManager;
 }
 
 @Bean(name = "accountDAO")
 public AccountDAO getaccountDAO() {
     return new AccountDAOImpl();
 }
 
 @Bean(name = "useraddressDAO")
 public UserAddressDAO getuseraddressDAO() {
     return new UserAddressDAOImpl();
 }
 
 @Bean(name = "applicantDAO")
 public ApplicantDAO getApplicantDAO() {
     return new ApplicantDAOImpl();
 }
 @Bean(name = "productDAO")
 public ProductDAO getProductDAO() {
     return new ProductDAOImpl();
 }
 @Bean(name = "categoryDAO")
 public CategoryDAO getCategoryDAO() {
     return new CategoryDAOImpl();
 }
 @Bean(name = "subcategoryDAO")
 public SubcategoryDAO getSubcategoryDAO() {
     return new SubcategoryDAOImpl();
 }
 @Bean(name = "messageDAO")
 public MessageDAO getMessageDAO() {
     return new MessageDAOImpl();
 }
 
 @Bean(name = "orderDAO")
 public OrderDAO getOrderDAO() {
     return new OrderDAOImpl();
 }
 
 @Bean(name = "orderstatusDAO")
 public OrderStatusDAO getOrderStatusDAO() {
     return new OrderStatusDAOImpl();
 }
 
 @Bean(name = "userWishlistDAO")
 public UserWishlistDAO getUserWishlistDAO() {
     return new UserWishlistDAOImpl();
 }
 
 @Bean(name = "locationZonesDAO")
 public LocationZonesDAO getLocationzonesDAO() {
     return new LocationZonesDAOImpl();
 }
 
 @Bean(name = "deliverypersonZoneDAO")
 public DeliverypersonZoneDAO getDeliverypersonZoneDAO() {
     return new DeliverypersonZoneDAOImpl();
 }
 @Bean(name = "followersDAO")
 public FollowersDAO getFollowersDAO() {
     return new FollowersDAOImpl();
 }
 @Bean(name = "sellerratingDAO")
 public SellerratingDAO getSellerratingDAO() {
     return new SellerratingDAOImpl();
 }
 @Bean(name = "multipartResolver")
 public CommonsMultipartResolver multipartResolver() {
     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
     multipartResolver.setMaxUploadSize(10000000);
     return multipartResolver;
 }
 
}