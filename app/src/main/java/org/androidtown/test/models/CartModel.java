package org.androidtown.test.models;

import java.io.Serializable;

public class CartModel implements Serializable {
    private int cartNo;
    private int memberNo;
    private int productNo;
    private int customerNo;
    private int cartQuantity;
    private int cartOptionNo;
    private String cartOptions;
    private String cartType;
    private String payedPrice;
    private String cartApplyCouponDiscount;
    private String cartApplyPromotionCodeDiscount;
    private String updatedDate;
    private String createdDate;
    private ProductModel productModel;

    public CartModel() {
    }

    public CartModel(CartModel cartModel){
        this.cartNo = cartModel.getCartNo();
        this.memberNo = cartModel.getMemberNo();
        this.productNo = cartModel.getProductNo();
        this.customerNo = cartModel.getCustomerNo();
        this.cartQuantity = cartModel.getCartQuantity();
        this.cartOptionNo = cartModel.getCartOptionNo();
        this.cartOptions = cartModel.getCartOptions();
        this.cartType = cartModel.getCartType();
        this.payedPrice = cartModel.getPayedPrice();
        this.cartApplyCouponDiscount = cartModel.getCartApplyCouponDiscount();
        this.cartApplyPromotionCodeDiscount = cartModel.getCartApplyPromotionCodeDiscount();
        this.updatedDate = cartModel.getUpdatedDate();
        this.createdDate = cartModel.getCreatedDate();
    }



    public int getCartNo() {
        return cartNo;
    }
    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    public int getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getProductNo() {
        return productNo;
    }
    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }
    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public int getCartOptionNo() {
        return cartOptionNo;
    }
    public void setCartOptionNo(int cartOptionNo) {
        this.cartOptionNo = cartOptionNo;
    }

    public String getCartOptions() {
        return cartOptions;
    }
    public void setCartOptions(String cartOptions) {
        this.cartOptions = cartOptions;
    }

    public String getCartType() {
        return cartType;
    }
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getPayedPrice() {
        return payedPrice;
    }
    public void setPayedPrice(String payedPrice) {
        this.payedPrice = payedPrice;
    }

    public String getCartApplyCouponDiscount() {
        return cartApplyCouponDiscount;
    }
    public void setCartApplyCouponDiscount(String cartApplyCouponDiscount) {
        this.cartApplyCouponDiscount = cartApplyCouponDiscount;
    }

    public String getCartApplyPromotionCodeDiscount() {
        return cartApplyPromotionCodeDiscount;
    }
    public void setCartApplyPromotionCodeDiscount(String cartApplyPromotionCodeDiscount) {
        this.cartApplyPromotionCodeDiscount = cartApplyPromotionCodeDiscount;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public ProductModel getProductModel() {
        return productModel;
    }
    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
