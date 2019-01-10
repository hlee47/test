package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerOrderInfoModel implements Serializable {

    private String orderInfoNo;
    private String memberNo;
    private String customerNo;
    private String orderCanceledOrderInfoNo;
    private String productNo;
    private String orderNo;
    private String optionNo;
    private String taxFree;
    private String orderQuantity;
    private String orderProductName;
    private String orderProductSKU;
    private String nonDelivery;
    private String payedPrice;
    private String additionalFixedOptionPrice;
    private String additionalFixedOption;
    private String individualCustomerUniqueCode;
    private String updateData;
    private String createdData;
    private String orderInfoShippingDueDate;
    private String orderInfoUniqueNo;
    private String orderInfoRequest;
    private String orderInfoDeliveryMethod;
    private String orderInfoParcelCompany;
    private String orderInfoParcelNumber;
    private String orderInfoStatusAdditionalInfo;
    private String orderInfoStatus;
    private String orderInfoHistoryType;
    private String orderInfoError;
    private String orderUniqueNo;
    private String orderName;
    private String guestOrderName;
    private String delay;

    public CustomerOrderInfoModel() {
    }

    public String getOrderInfoNo() { return  this.orderInfoNo; }
    public void setOrderInfoNo(String orderInfoNo) { this.orderInfoNo = orderInfoNo; }

    public String getMemberNo() { return this.memberNo; }
    public void setMemberNo(String memberNo) { this.memberNo = memberNo; }

    public String getCustomerNo() { return this.customerNo; }
    public void setCustomerNo(String customerNo) { this.customerNo = customerNo; }

    public String getOrderCanceledOrderInfoNo() { return this.orderCanceledOrderInfoNo; }
    public void setOrderCanceledOrderInfoNo(String orderCanceledOrderInfoNo) { this.orderCanceledOrderInfoNo = orderCanceledOrderInfoNo; }

    public String getProductNo() { return this.productNo; }
    public void setProductNo(String productNo) { this.productNo = productNo; }

    public String getOrderNo() { return this.orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getOptionNo() { return this.optionNo; }
    public void setOptionNo(String optionNo) { this.optionNo = optionNo; }

    public String getTaxFree() { return this.taxFree; }
    public void setTaxFree(String taxFree) { this.taxFree = taxFree; }

    public String getOrderName() { return this.orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

    public String getOrderUniqueNo() { return this.orderUniqueNo; }
    public void setOrderUniqueNo(String orderUniqueNo) { this.orderUniqueNo = orderUniqueNo; }

    public String getUpdateData() { return this.updateData; }
    public void setUpdateData(String updateData) { this.updateData = updateData; }

    public String getCreatedData() { return this.createdData; }
    public void setCreatedData(String createdData) { this.createdData = createdData; }

    public String getOrderProductName() { return this.orderProductName; }
    public void setOrderProductName(String orderProductName) { this.orderProductName = orderProductName; }

    public String getOrderProductSKU() { return this.orderProductSKU; }
    public void setOrderProductSKU(String orderProductSKU) { this.orderProductSKU = orderProductSKU; }

    public String getOrderQuantity() { return this.orderQuantity; }
    public void setOrderQuantity(String orderQuantity) { this.orderQuantity = orderQuantity; }

    public String getPayedPrice() { return this.payedPrice; }
    public void setPayedPrice(String payedPrice) { this.payedPrice = payedPrice; }

    public String getAdditionalFixedOptionPrice() { return this.additionalFixedOptionPrice; }
    public void setAdditionalFixedOptionPrice(String additionalFixedOptionPrice) { this.additionalFixedOptionPrice = additionalFixedOptionPrice; }

    public String getOrderInfoUniqueNo() { return this.orderInfoUniqueNo; }
    public void setOrderInfoUniqueNo(String orderInfoUniqueNo) { this.orderInfoUniqueNo = orderInfoUniqueNo; }

    public String getOrderInfoRequest() { return this.orderInfoRequest; }
    public void setOrderInfoRequest(String orderInfoRequest) { this.orderInfoRequest = orderInfoRequest; }

    public String getOrderInfoParcelCompany() { return this.orderInfoParcelCompany; }
    public void setOrderInfoParcelCompany(String orderInfoParcelCompany) { this.orderInfoParcelCompany = orderInfoParcelCompany; }

    public String getOrderInfoParcelNumber() { return this.orderInfoParcelNumber; }
    public void setOrderInfoParcelNumber(String orderInfoParcelNumber) { this.orderInfoParcelNumber = orderInfoParcelNumber; }

    public String getOrderInfoStatusAdditionalInfo() { return this.orderInfoStatusAdditionalInfo; }
    public void setOrderInfoStatusAdditionalInfo(String orderInfoStatusAdditionalInfo) { this.orderInfoStatusAdditionalInfo = orderInfoStatusAdditionalInfo; }

    public String getOrderInfoStatus() { return this.orderInfoStatus; }
    public void setOrderInfoStatus(String orderInfoStatus) { this.orderInfoStatus = orderInfoStatus; }

    public String getOrderInfoDeliveryMethod() { return this.orderInfoDeliveryMethod; }
    public void setOrderInfoDeliveryMethod(String orderInfoDeliveryMethod) { this.orderInfoDeliveryMethod = orderInfoDeliveryMethod; }

    public String getIndividualCustomerUniqueCode() { return this.individualCustomerUniqueCode; }
    public void setIndividualCustomerUniqueCode(String individualCustomerUniqueCode) { this.individualCustomerUniqueCode = individualCustomerUniqueCode; }

    public String getOrderInfoShippingDueDate() { return this.orderInfoShippingDueDate; }
    public void setOrderInfoShippingDueDate(String orderInfoShippingDueDate) { this.orderInfoShippingDueDate = orderInfoShippingDueDate; }

    public String getNonDelivery() { return this.nonDelivery; }
    public void setNonDelivery(String nonDelivery) { this.nonDelivery = nonDelivery; }

    public String getAdditionalFixedOption() { return this.additionalFixedOption; }
    public void setAdditionalFixedOption(String additionalFixedOption) { this.additionalFixedOption = additionalFixedOption; }

    public String getOrderInfoHistoryType() { return this.orderInfoHistoryType; }
    public void setOrderInfoHistoryType(String orderInfoHistoryType) { this.orderInfoHistoryType = orderInfoHistoryType; }

    public String getOrderInfoError() { return this.orderInfoError; }
    public void setOrderInfoError(String orderInfoError) { this.orderInfoError = orderInfoError; }

    public String getGuestOrderName() { return this.getGuestOrderName(); }
    public void setGuestOrderName(String guestOrderName) { this.guestOrderName = guestOrderName; }

    public String getDelay() { return  this.getDelay(); }
    public void setDelay(String delay) { this.delay = delay; }

}
