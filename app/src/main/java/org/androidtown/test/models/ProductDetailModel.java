package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDetailModel implements Serializable {
    private String productNo;
    private String productName;
    private String productPrice;
    private String productDiscountPrice;
    private String productThumbnail;
    private String productDescription;
    private String relatedProductNoList;
    private String productOptionList;
    private ArrayList<ProductOptionNameModel> productOptionNameList;
    private ArrayList<ProductOptionValueModel> productOptionValueList;
    private ArrayList<String> productDescriptionImgURLList;
    private ArrayList<ProductReviewModel> productReviewList;
    private ArrayList<ProductReviewCommentModel> productReviewCommentList;

    public ProductDetailModel() { }

    public ProductDetailModel(ProductDetailModel productDetailModel) {
        this.productNo = productDetailModel.getProductNo();
        this.productName = productDetailModel.getProductName();
        this.productPrice = productDetailModel.getProductPrice();
        this.productDiscountPrice = productDetailModel.getProductDiscountPrice();
        this.productThumbnail = productDetailModel.getProductThumbnail();
        this.productDescription = productDetailModel.getProductDescription();
        this.relatedProductNoList = productDetailModel.getRelatedProductNoList();
        this.productOptionList = productDetailModel.getProductOptionList();
        this.productDescriptionImgURLList = productDetailModel.getProductDescriptionImgURLList();
        this.productReviewList = productDetailModel.getProductReviewList();
        this.productReviewCommentList = productDetailModel.getProductReviewCommentList();
    }

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }
    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }
    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setRelatedProductNoList(String relatedProductNoList) {
        this.relatedProductNoList = relatedProductNoList;
    }
    public String getRelatedProductNoList() {
        return relatedProductNoList;
    }

    public String getProductOptionList() {
        return productOptionList;
    }
    public void setProductOptionList(String productOptionList) {
        this.productOptionList = productOptionList;
    }

    public ArrayList<ProductOptionNameModel> getProductOptionNameList() {
        return productOptionNameList;
    }
    public void setProductOptionNameList(ArrayList<ProductOptionNameModel> productOptionNameList) {
        this.productOptionNameList = productOptionNameList;
    }

    public ArrayList<ProductOptionValueModel> getProductOptionValueList() {
        return productOptionValueList;
    }
    public void setProductOptionValueList(ArrayList<ProductOptionValueModel> productOptionValueList) {
        this.productOptionValueList = productOptionValueList;
    }

    public ArrayList<String> getProductDescriptionImgURLList() {
        return productDescriptionImgURLList;
    }
    public void setProductDescriptionImgURLList(ArrayList<String> productDescriptionImgURLList) {
        this.productDescriptionImgURLList = productDescriptionImgURLList;
    }

    public ArrayList<ProductReviewModel> getProductReviewList() {
        return productReviewList;
    }
    public void setProductReviewList(ArrayList<ProductReviewModel> productReviewList) {
        this.productReviewList = productReviewList;
    }

    public ArrayList<ProductReviewCommentModel> getProductReviewCommentList() {
        return productReviewCommentList;
    }
    public void setProductReviewCommentList(ArrayList<ProductReviewCommentModel> productReviewCommentList) {
        this.productReviewCommentList = productReviewCommentList;
    }
}
