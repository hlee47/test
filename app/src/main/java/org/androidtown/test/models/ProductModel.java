package org.androidtown.test.models;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private String productNo;
    private String memberNo;
    private String productName;
    private int productPrice;
    private int productDiscountPrice;
    private String productImgs;
    private String productThumbnail;
    private int productQuantity;
    private String sellStatus;
    private String linkedCategoryList;
    private String relatedProductNoList;
    private String productOptionList;
    private String productOptionPrice;
    private int optionTotalQuantity;
    private int optionQuantity;
    private int productOrderNo;
    private String productAddress;
    private int optionSoldOut;
    private int rewardPointRate;
    private int productAppliedDiscountEventPrice;
    private String productAppliedDiscountName;
    private String productAppliedDiscountType;
    private int productAppliedDiscountRate;
    private String updatedDate;
    private String createdDate;
    private String productOptions;
    private String optionNo;
    private String optionSKU;
    private String categoryNames;
    private boolean productSelect = false;

    public ProductModel() {}

    public ProductModel(ProductModel productModel) {
        this.productNo = productModel.productNo;
        this.memberNo = productModel.memberNo;
        this.productName = productModel.productName;
        this.productPrice = productModel.productPrice;
        this.productDiscountPrice = productModel.productDiscountPrice;
        this.productImgs = productModel.productImgs;
        this.productThumbnail = productModel.productThumbnail;
        this.productQuantity = productModel.productQuantity;
        this.sellStatus = productModel.sellStatus;
        this.linkedCategoryList = productModel.linkedCategoryList;
        this.relatedProductNoList = productModel.relatedProductNoList;
        this.productOptionList = productModel.productOptionList;
        this.optionTotalQuantity = productModel.optionTotalQuantity;
        this.optionQuantity = productModel.optionQuantity;
        this.productOrderNo = productModel.productOrderNo;
        this.productAddress = productModel.productAddress;
        this.optionSoldOut = productModel.optionSoldOut;
        this.rewardPointRate = productModel.rewardPointRate;
        this.productAppliedDiscountEventPrice = productModel.productAppliedDiscountEventPrice;
        this.productAppliedDiscountName = productModel.productAppliedDiscountName;
        this.productAppliedDiscountType = productModel.productAppliedDiscountType;
        this.productAppliedDiscountRate = productModel.productAppliedDiscountRate;
        this.updatedDate = productModel.updatedDate;
        this.createdDate = productModel.createdDate;
        this.productOptions = productModel.productOptions;
        this.optionNo = productModel.optionNo;
        this.optionSKU = productModel.optionSKU;
        this.categoryNames = productModel.categoryNames;
    }

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return  productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductDiscountPrice() {
        return productDiscountPrice;
    }
    public void setProductDiscountPrice(int productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductImgs() {
        return productImgs;
    }
    public void setProductImgs(String productImgs) {
        this.productImgs = productImgs;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }
    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public int getOptionQuantity() {
        return optionQuantity;
    }
    public void setOptionQuantity(int optionQuantity) {
        this.optionQuantity = optionQuantity;
    }

    public int getOptionSoldOut() {
        return optionSoldOut;
    }
    public void setOptionSoldOut(int optionSoldOut) {
        this.optionSoldOut = optionSoldOut;
    }

    public int getOptionTotalQuantity() {
        return optionTotalQuantity;
    }
    public void setOptionTotalQuantity(int optionTotalQuantity) {
        this.optionTotalQuantity = optionTotalQuantity;
    }

    public int getProductAppliedDiscountEventPrice() {
        return productAppliedDiscountEventPrice;
    }
    public void setProductAppliedDiscountEventPrice(int productAppliedDiscountEventPrice) {
        this.productAppliedDiscountEventPrice = productAppliedDiscountEventPrice;
    }

    public int getProductAppliedDiscountRate() {
        return productAppliedDiscountRate;
    }
    public void setProductAppliedDiscountRate(int productAppliedDiscountRate) {
        this.productAppliedDiscountRate = productAppliedDiscountRate;
    }

    public int getProductOrderNo() {
        return productOrderNo;
    }
    public void setProductOrderNo(int productOrderNo) {
        this.productOrderNo = productOrderNo;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getRewardPointRate() {
        return rewardPointRate;
    }
    public void setRewardPointRate(int rewardPointRate) {
        this.rewardPointRate = rewardPointRate;
    }

    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategoryNames() {
        return categoryNames;
    }
    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getLinkedCategoryList() {
        return linkedCategoryList;
    }
    public void setLinkedCategoryList(String linkedCategoryList) {
        this.linkedCategoryList = linkedCategoryList;
    }

    public String getOptionNo() {
        return optionNo;
    }
    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionSKU() {
        return optionSKU;
    }
    public void setOptionSKU(String optionSKU) {
        this.optionSKU = optionSKU;
    }

    public String getProductAddress() {
        return productAddress;
    }
    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getProductAppliedDiscountName() {
        return productAppliedDiscountName;
    }
    public void setProductAppliedDiscountName(String productAppliedDiscountName) {
        this.productAppliedDiscountName = productAppliedDiscountName;
    }

    public String getProductAppliedDiscountType() {
        return productAppliedDiscountType;
    }
    public void setProductAppliedDiscountType(String productAppliedDiscountType) {
        this.productAppliedDiscountType = productAppliedDiscountType;
    }

    public String getProductOptionList() {
        return productOptionList;
    }
    public void setProductOptionList(String productOptionList) {
        this.productOptionList = productOptionList;
    }

    public String getProductOptions() {
        return productOptions;
    }
    public void setProductOptions(String productOptions) {
        this.productOptions = productOptions;
    }

    public String getProductOptionPrice() {
        return productOptionPrice;
    }
    public void setProductOptionPrice(String productOptionPrice) {
        this.productOptionPrice = productOptionPrice;
    }

    public String getRelatedProductNoList() {
        return relatedProductNoList;
    }
    public void setRelatedProductNoList(String relatedProductNoList) {
        this.relatedProductNoList = relatedProductNoList;
    }

    public String getSellStatus() {
        return sellStatus;
    }
    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean getProductSelect() { return productSelect; }
    public void setProductSelect(boolean productSelect) { this.productSelect = productSelect; }

}
