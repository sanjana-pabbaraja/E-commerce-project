package com.amazon.market.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.amazon.market.model.UserAddressInfo;
 
// @Component: As a Bean.
@Component
public class UserAddressInfoValidator implements Validator {
 
    //private EmailValidator emailValidator = EmailValidator.getInstance();
 
    // This Validator support UserAddressInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserAddressInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	UserAddressInfo addrInfo = (UserAddressInfo) target;
 
        // Check the fields of UserAddressInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty.useraddressForm.firstname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty.useraddressForm.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doorno", "NotEmpty.useraddressForm.doorno");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty.useraddressForm.street");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "area", "NotEmpty.useraddressForm.area");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.useraddressForm.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "NotEmpty.useraddressForm.state");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode", "NotEmpty.useraddressForm.pincode");

    }
 
}