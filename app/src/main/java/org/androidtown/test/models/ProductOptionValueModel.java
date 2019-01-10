package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductOptionValueModel implements Serializable {

    private String optionNo;
    private String optionValueNo;
    private String productNo;
    private String optionNameNo;
    private String optionValue;
    private String optionPrice;
    private String updatedDate;
    private String createdDate;

    public ProductOptionValueModel() {

    }

    public void ProductOptionValueModel(ProductOptionValueModel productOptionValueModel) {
        this.optionNo = productOptionValueModel.getOptionNo();
        this.optionValueNo = productOptionValueModel.getOptionValueNo();
        this.productNo = productOptionValueModel.getProductNo();
        this.optionNameNo = productOptionValueModel.getOptionNameNo();
        this.optionValue = productOptionValueModel.getOptionValue();
        this.optionPrice = productOptionValueModel.getOptionPrice();
        this.updatedDate = productOptionValueModel.getUpdatedDate();
        this.createdDate = productOptionValueModel.getCreatedDate();

    }

    public String getOptionNo() {
        return optionNo;
    }
    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionValueNo() {
        return optionValueNo;
    }
    public void setOptionValueNo(String optionValueNo) {
        this.optionValueNo = optionValueNo;
    }

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOptionNameNo() {
        return optionNameNo;
    }
    public void setOptionNameNo(String optionNameNo) {
        this.optionNameNo = optionNameNo;
    }

    public String getOptionValue() {
        return optionValue;
    }
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionPrice() {
        return optionPrice;
    }
    public void setOptionPrice(String optionPrice) {
        this.optionPrice = optionPrice;
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

}
