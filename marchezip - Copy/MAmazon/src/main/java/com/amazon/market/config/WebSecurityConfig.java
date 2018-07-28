package com.amazon.market.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.amazon.market.authentication.MyDBAuthenticationService;
 
@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Autowired
   MyDBAuthenticationService myDBAauthenticationService;
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
       // For User in database.
       auth.userDetailsService(myDBAauthenticationService);
 
   }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
 
       http.csrf().disable();
 
       // The pages requires login as ADMIN or REGISTEREDUSER.
       
      // http.authorizeRequests().antMatchers("/editProduct","/orderList","/order", "/accountInfo")//
             //  .access("hasAnyRole('ROLE_ADMIN', 'ROLE_REGISTEREDUSER')");
       // If no login, it will redirect to /login page.
       // For REGISTEREDUSER only.
       http.authorizeRequests().antMatchers("/receivedorder","/orders-received","/orders-placed","/myproducts","/editProduct","/deleteProduct","/shoppingCartCustomer","/addtoWishlist","/changepassword").access("hasRole('ROLE_REGISTEREDUSER')");
       http.authorizeRequests().antMatchers("/deliverables","/delivered-history","/deliver-order","/changepassword").access("hasRole('ROLE_DELIVERYBOY')");

       // When the user has logged in as XX.
       // But access a page that requires role YY,
       // AccessDeniedException will throw.
       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
       // Config for Login Form
       http.authorizeRequests().and().formLogin()//
               // Submit URL of login page.
               .loginProcessingUrl("/j_spring_security_check") // Submit URL
               .loginPage("/login")//
               .defaultSuccessUrl("/loginSuccess")//
               .failureUrl("/login?error=true")//
               .usernameParameter("email")//
               .passwordParameter("password")
               // Config for Logout Page
               // (Go to home page).
               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
               //.and()..register().registerUrl("/register").registerSuccessUrl("/accountInfo");
 
   }
}