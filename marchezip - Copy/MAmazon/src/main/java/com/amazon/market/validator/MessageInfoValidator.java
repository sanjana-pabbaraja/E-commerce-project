
package com.amazon.market.validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.amazon.market.dao.MessageDAO;
import com.amazon.market.entity.Message;
import com.amazon.market.model.MessageInfo;

@Component
public class MessageInfoValidator implements Validator  {
	 // This Validator support AccountInfo class.
	 
  
 
	
    @Override
    public boolean supports(Class<?> aclaz) {
    	return aclaz == MessageInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	
        }

}
