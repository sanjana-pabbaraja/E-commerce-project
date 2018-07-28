package com.amazon.market.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.amazon.market.dao.AccountDAO;
import com.amazon.market.entity.Account;
import com.amazon.market.model.AccountInfo;
 
// @Component: As a Bean.
@Component
public class AccountInfoValidator implements Validator {
	
	 // This Validator support AccountInfo class.
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
	
    @Override
    public boolean supports(Class<?> aclaz) {
        return aclaz == AccountInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	AccountInfo regInfo = (AccountInfo) target;
    	System.out.println("uid is "+regInfo.getId());
        // Check the fields of AccountInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.registrationForm.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registrationForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.registrationForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileno", "NotEmpty.registrationForm.mobileno");
        
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.regInfo.phone");

        System.out.println("uid is "+regInfo.getId());
        System.out.println("uid is "+regInfo.getId());
        System.out.println(" email "+ regInfo.getEmail());
        if (!emailValidator.isValid(regInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.registrationForm.email");
        }
        
     
        
       
    }
 
}