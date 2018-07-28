package com.amazon.market.validator;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.amazon.market.dao.ProductDAO;
import com.amazon.market.entity.Products;
import com.amazon.market.model.ProductInfo;
 
// @Component: As a Bean.
@Component
public class ProductInfoValidator implements Validator {
 
    @Autowired
    private ProductDAO productDAO;
 
    // This Validator support ProductInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        ProductInfo productInfo = (ProductInfo) target;
 
        // Check the fields of ProductInfo class.
       //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.productDescription");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
        
        //productInfo.getId()
        /*String code = productInfo.getId()+"";
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.productForm.code");
            } else if(productInfo.isNewProduct()) {
                Products product = productDAO.findProduct(Integer.parseInt(code));
                if (product != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }*/
    }
 
}