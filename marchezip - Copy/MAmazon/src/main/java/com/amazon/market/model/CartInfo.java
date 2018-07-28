package com.amazon.market.model;


import java.util.ArrayList;
import java.util.List;
 
public class CartInfo {
 
    private int orderNum;
 
   private CustomerInfo customerInfo;
   private UserAddressInfo useraddressInfo;
 
    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
 
    public CartInfo() {
 
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    /* user address info added start */
    public void setUserAddressInfo(UserAddressInfo useraddressInfo) {
        this.useraddressInfo = useraddressInfo;
    }
    
    public UserAddressInfo getUserAddressInfo() {
        return useraddressInfo;
    }
    /* user address info added stop */
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
 
    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }
 
    private CartLineInfo findLineById(long id) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProductInfo().getId()==id) {
                return line;
            }
        }
        return null;
    }
 
    public void addProduct(ProductInfo productInfo, int quantity) {
        CartLineInfo line = this.findLineById(productInfo.getId());
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProductInfo(productInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
 
    public void validate() {
 
    }
 
    public void updateProduct(long id, int quantity) {
        CartLineInfo line = this.findLineById(id);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
 
    public void removeProduct(ProductInfo productInfo) {
        CartLineInfo line = this.findLineById(productInfo.getId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }
 
    public void updateQuantity(CartInfo cartForm) {
    	System.out.println(cartForm.getQuantityTotal());
    	
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            System.out.println(lines.size());
            for (CartLineInfo line : lines) {
            	System.out.println(line.getProductInfo().toString());
            	System.out.println(line.getQuantity());
                this.updateProduct(line.getProductInfo().getId(), line.getQuantity());
            }
        }
 
    }
 
}
