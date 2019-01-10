package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductOptionNameModel implements Serializable {
    private String optionNo;
    private String optionNameNo;
    private String productNo;
    private String optionName;
    private String optionNameOrderNo;
    private String updatedDate;
    private String createdDate;
    private String optionValueList;

    public ProductOptionNameModel() {

    }

    public void ProductOptionNameModel(ProductOptionNameModel productOptionNameModel) {
        this.optionNameNo = productOptionNameModel.getOptionNameNo();
        this.productNo = productOptionNameModel.getProductNo();
        this.optionName = productOptionNameModel.getOptionName();
        this.optionNameOrderNo = productOptionNameModel.getOptionNameOrderNo();
        this.optionValueList = productOptionNameModel.getOptionValueList();
        this.updatedDate = productOptionNameModel.getUpdatedDate();
        this.createdDate = productOptionNameModel.getCreatedDate();
    }

    public String getOptionNameNo() {
        return optionNameNo;
    }
    public void setOptionNameNo(String optionNameNo) {
        this.optionNameNo = optionNameNo;
    }

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOptionName() {
        return optionName;
    }
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionNameOrderNo() {
        return optionNameOrderNo;
    }
    public void setOptionNameOrderNo(String optionNameOrderNo) {
        this.optionNameOrderNo = optionNameOrderNo;
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

    public String getOptionValueList() {
        return optionValueList;
    }
    public void setOptionValueList(String optionValueList) {
        this.optionValueList = optionValueList;
    }
}
