package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerOrderModel implements Serializable {

    private String orderNo;
    private String memberNo;
    private String customerNo;
    private String orderSequence;
    private String orderName;
    private String orderEmail;
    private String orderAddress;
    private String orderPhone;
    private String orderTitle;
    private String orderPrice;
    private String deliveryPrice;
    private String orderCartList;
    private String orderTid;
    private String orderAccount;
    private String payMethod;
    private String pgType;
    private String orderStatus;
    private String orderOptions;
    private String orderRequest;
    private String parcelCompany;
    private String parcelNumber;
    private String moneySender;
    private String refundAccountInfo;
    private String guestOrderName;
    private String guestOrderPhone;
    private String orderRefundError;
    private String orderRefundErrorMsg;
    private String confirmRefundError;
    private String orderError;
    private String orderUniqueNo;
    private String orderMemo;
    private String orderCancelReason;
    private String orderBankExpireDate;
    private String bankExpireDateText;
    private String updateDate;
    private String createdDate;
    private String orderHistoryType;
    private String shopOrderInfos;
    private String customerName;
    private String customerPhone;
    private String customerId;
    private String customerEmail;
    private String orderTotalQuantity;
    private String cancelOrderQuantity;
    private String orderProductName;
    private String orderProductSKU;
    private String orderQuantity;
    private String payedPrice;
    private String additionalFixedOptionPrice;
    private String orderCustomizedOption;
    private String orderCanceled;
    private String orderPaymentDate;
    private String orderInfoUniqueNo;
    private String orderInfoRequest;
    private String orderInfoParcelCompany;
    private String orderInfoParcelNumber;
    private String orderInfoStatusAdditionalInfo;
    private String orderInfoStatus;
    private String orderInfoDeliveryMethod;
    private String individualCustomerUniqueCode;
    private String orderInfoShippingDueDate;
    private String orderInfoTemp;
    private String orderStatusDescription;
    private String payMethodDescription;
    private String rewardPoint;
    private String orderUsePoint;
    private String totalOrderProductPrice;
    private ArrayList<CustomerOrderInfoModel> orderInfoList;

    public CustomerOrderModel() {}

    public CustomerOrderModel(CustomerOrderModel customerOrderModel) {
        this.orderNo = customerOrderModel.getOrderNo();
        this.memberNo= customerOrderModel.getMemberNo();
        this.customerNo= customerOrderModel.getCustomerNo();
        this.orderSequence= customerOrderModel.getOrderSequence();
        this.orderName= customerOrderModel.getOrderName();
        this.orderEmail= customerOrderModel.getOrderEmail();
        this.orderAddress= customerOrderModel.getOrderAddress();
        this.orderPhone= customerOrderModel.getOrderPhone();
        this.orderTitle= customerOrderModel.getOrderTitle();
        this.orderPrice= customerOrderModel.getOrderPrice();
        this.deliveryPrice = customerOrderModel.getDeliveryPrice();
        this.orderCartList= customerOrderModel.getOrderCartList();
        this.orderTid= customerOrderModel.getOrderTid();
        this.orderAccount= customerOrderModel.getOrderAccount();
        this.payMethod= customerOrderModel.getPayMethod();
        this.pgType= customerOrderModel.getPgType();
        this.orderStatus= customerOrderModel.getOrderStatus();
        this.orderOptions= customerOrderModel.getOrderOptions();
        this.orderRequest= customerOrderModel.getOrderRequest();
        this.parcelCompany= customerOrderModel.getParcelCompany();
        this.parcelNumber= customerOrderModel.getParcelNumber();
        this.moneySender= customerOrderModel.getMoneySender();
        this.refundAccountInfo= customerOrderModel.getRefundAccountInfo();
        this.guestOrderName= customerOrderModel.getGuestOrderName();
        this.guestOrderPhone= customerOrderModel.getGuestOrderPhone();
        this.orderRefundError= customerOrderModel.getOrderRefundError();
        this.orderRefundErrorMsg= customerOrderModel.getOrderRefundErrorMsg();
        this.confirmRefundError= customerOrderModel.getConfirmRefundError();
        this.orderError= customerOrderModel.getOrderError();
        this.orderUniqueNo= customerOrderModel.getOrderUniqueNo();
        this.orderMemo= customerOrderModel.getOrderMemo();
        this.orderCancelReason= customerOrderModel.getOrderCancelReason();
        this.orderBankExpireDate= customerOrderModel.getOrderBankExpireDate();
        this.bankExpireDateText= customerOrderModel.getBankExpireDateText();
        this.updateDate= customerOrderModel.getUpdateDate();
        this.createdDate= customerOrderModel.getCreatedDate();
        this.orderHistoryType= customerOrderModel.getOrderHistoryType();
        this.shopOrderInfos= customerOrderModel.getShopOrderInfos();
        this.customerName= customerOrderModel.getCustomerName();
        this.customerPhone= customerOrderModel.getCustomerPhone();
        this.customerId= customerOrderModel.getCustomerId();
        this.customerEmail= customerOrderModel.getCustomerEmail();
        this.orderTotalQuantity= customerOrderModel.getOrderTotalQuantity();
        this.cancelOrderQuantity= customerOrderModel.getCancelOrderQuantity();
        this.orderProductName= customerOrderModel.getOrderProductName();
        this.orderProductSKU= customerOrderModel.getOrderProductSKU();
        this.orderQuantity= customerOrderModel.getOrderQuantity();
        this.payedPrice= customerOrderModel.getPayedPrice();
        this.additionalFixedOptionPrice= customerOrderModel.getAdditionalFixedOptionPrice();
        this.orderCustomizedOption= customerOrderModel.getOrderCustomizedOption();
        this.orderCanceled= customerOrderModel.getOrderCanceled();
        this.orderPaymentDate= customerOrderModel.getOrderPaymentDate();
        this.orderInfoUniqueNo= customerOrderModel.getOrderInfoUniqueNo();
        this.orderInfoRequest= customerOrderModel.getOrderInfoRequest();
        this.orderInfoParcelCompany= customerOrderModel.getOrderInfoParcelCompany();
        this.orderInfoParcelNumber= customerOrderModel.getOrderInfoParcelNumber();
        this.orderInfoStatusAdditionalInfo= customerOrderModel.getOrderInfoStatusAdditionalInfo();
        this.orderInfoStatus= customerOrderModel.getOrderInfoStatus();
        this.orderInfoDeliveryMethod= customerOrderModel.getOrderInfoDeliveryMethod();
        this.individualCustomerUniqueCode= customerOrderModel.getIndividualCustomerUniqueCode();
        this.orderInfoShippingDueDate= customerOrderModel.getOrderInfoShippingDueDate();
        this.orderInfoTemp= customerOrderModel.getOrderInfoTemp();
        this.orderStatusDescription= customerOrderModel.getOrderStatusDescription();
        this.payMethodDescription= customerOrderModel.getPayMethodDescription();
        this.rewardPoint = customerOrderModel.getRewardPoint();
        this.orderUsePoint = customerOrderModel.getOrderUsePoint();
        this.totalOrderProductPrice = customerOrderModel.getTotalOrderProductPrice();
        this.orderInfoList = customerOrderModel.getOrderInfoList();
    }

    public String getOrderNo() { return this.orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getCustomerNo() { return this.customerNo; }
    public void setCustomerNo(String customerNo) { this.customerNo = customerNo; }

    public String getMemberNo() { return this.memberNo; }
    public void setMemberNo(String memberNo) { this.memberNo = memberNo; }

    public String getOrderSequence() { return this.orderSequence; }
    public void setOrderSequence(String orderSequence) { this.orderSequence = orderSequence; }

    public String getOrderName() { return this.orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

    public String getOrderEmail() { return this.orderEmail; }
    public void setOrderEmail(String orderEmail) { this.orderEmail = orderEmail; }

    public String getOrderAddress() { return this.orderAddress; }
    public void setOrderAddress(String orderAddress) { this.orderAddress = orderAddress; }

    public String getOrderPhone() { return this.orderPhone; }
    public void setOrderPhone(String orderPhone) { this.orderPhone = orderPhone; }

    public String getCustomerPhone() { return this.customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getOrderTitle() { return this.orderTitle; }
    public void setOrderTitle(String orderTitle) { this.orderTitle = orderTitle; }

    public String getOrderPrice() { return this.orderPrice; }
    public void setOrderPrice(String orderPrice) { this.orderPrice = orderPrice; }

    public String getDeliveryPrice() { return  this.deliveryPrice; }
    public void setDeliveryPrice(String deliveryPrice) { this.deliveryPrice = deliveryPrice; }

    public String getOrderCartList() { return this.orderCartList; }
    public void setOrderCartList(String orderCartList) { this.orderCartList = orderCartList; }

    public String getOrderTid() { return this.orderTid; }
    public void setOrderTid(String orderTid) { this.orderTid = orderTid; }

    public String getOrderAccount() { return this.orderAccount; }
    public void setOrderAccount(String orderAccount) { this.orderAccount = orderAccount; }

    public String getPayMethod() { return this.payMethod; }
    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

    public String getPgType() { return this.pgType; }
    public void setPgType(String pgType) { this.pgType = pgType; }

    public String getOrderStatus() { return this.orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getOrderOptions() { return this.orderOptions; }
    public void setOrderOptions(String orderOptions) { this.orderOptions = orderOptions; }

    public String getOrderRequest() { return this.orderRequest; }
    public void setOrderRequest(String orderRequest) { this.orderRequest = orderRequest; }

    public String getParcelCompany() { return this.parcelCompany; }
    public void setParcelCompany(String parcelCompany) { this.parcelCompany = parcelCompany; }

    public String getParcelNumber() { return this.parcelNumber; }
    public void setParcelNumber(String parcelNumber) { this.parcelNumber = parcelNumber; }

    public String getMoneySender() { return this.moneySender; }
    public void setMoneySender(String moneySender) { this.moneySender = moneySender; }

    public String getRefundAccountInfo() { return this.refundAccountInfo; }
    public void setRefundAccountInfo(String refundAccountInfo) { this.refundAccountInfo = refundAccountInfo; }

    public String getGuestOrderName() { return this.guestOrderName; }
    public void setGuestOrderName(String guestOrderName) { this.guestOrderName = guestOrderName; }

    public String getGuestOrderPhone() { return this.guestOrderPhone; }
    public void setGuestOrderPhone(String guestOrderPhone) { this.guestOrderPhone = guestOrderPhone; }

    public String getOrderRefundError() { return this.orderRefundError; }
    public void setOrderRefundError(String orderRefundError) { this.orderRefundError = orderRefundError; }

    public String getOrderRefundErrorMsg() { return this.orderRefundErrorMsg; }
    public void setOrderRefundErrorMsg(String orderRefundErrorMsg) { this.orderRefundErrorMsg = orderRefundErrorMsg; }

    public String getConfirmRefundError() { return this.confirmRefundError; }
    public void setConfirmRefundError(String confirmRefundError) { this.confirmRefundError = confirmRefundError; }

    public String getOrderError() { return this.orderError; }
    public void setOrderError(String orderError) { this.orderError = orderError; }

    public String getOrderUniqueNo() { return this.orderUniqueNo; }
    public void setOrderUniqueNo(String orderUniqueNo) { this.orderUniqueNo = orderUniqueNo; }

    public String getOrderMemo() { return this.orderMemo; }
    public void setOrderMemo(String orderMemo) { this.orderMemo = orderMemo; }

    public String getOrderCancelReason() { return this.orderCancelReason; }
    public void setOrderCancelReason(String orderCancelReason) { this.orderCancelReason = orderCancelReason; }

    public String getOrderBankExpireDate() { return this.orderBankExpireDate; }
    public void setOrderBankExpireDate(String orderBankExpireDate) { this.orderBankExpireDate = orderBankExpireDate; }

    public String getBankExpireDateText() { return this.bankExpireDateText; }
    public void setBankExpireDateText(String bankExpireDateText) { this.bankExpireDateText = bankExpireDateText; }

    public String getUpdateDate() { return this.updateDate; }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }

    public String getCreatedDate() { return this.createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getOrderHistoryType() { return this.orderHistoryType; }
    public void setOrderHistoryType(String orderHistoryType) { this.orderHistoryType = orderHistoryType; }

    public String getShopOrderInfos() { return this.shopOrderInfos; }
    public void setShopOrderInfos(String shopOrderInfos) { this.shopOrderInfos = shopOrderInfos; }

    public String getCustomerName() { return this.customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerId() { return this.customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerEmail() { return this.customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getOrderTotalQuantity() { return this.orderTotalQuantity; }
    public void setOrderTotalQuantity(String orderTotalQuantity) { this.orderTotalQuantity = orderTotalQuantity; }

    public String getCancelOrderQuantity() { return this.cancelOrderQuantity; }
    public void setCancelOrderQuantity(String cancelOrderQuantity) { this.cancelOrderQuantity = cancelOrderQuantity; }

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

    public String getOrderCustomizedOption() { return this.orderCustomizedOption; }
    public void setOrderCustomizedOption(String orderCustomizedOption) { this.orderCustomizedOption = orderCustomizedOption; }

    public String getOrderCanceled() { return this.orderCanceled; }
    public void setOrderCanceled(String orderCanceled) { this.orderCanceled = orderCanceled; }

    public String getOrderPaymentDate() { return this.orderPaymentDate; }
    public void setOrderPaymentDate(String orderPaymentDate) { this.orderPaymentDate = orderPaymentDate; }

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

    public String getOrderInfoTemp() { return this.orderInfoTemp; }
    public void setOrderInfoTemp(String orderInfoTemp) { this.orderInfoTemp = orderInfoTemp; }

    public String getOrderStatusDescription() { return this.orderStatusDescription; }
    public void setOrderStatusDescription(String orderStatusDescription) { this.orderStatusDescription = orderStatusDescription; }

    public String getPayMethodDescription() { return this.payMethodDescription; }
    public void setPayMethodDescription(String payMethodDescription) { this.payMethodDescription = payMethodDescription; }

    public String getRewardPoint() { return this.rewardPoint; }
    public void setRewardPoint(String rewardPoint) { this.rewardPoint = rewardPoint; }

    public String getOrderUsePoint() { return this.orderUsePoint; }
    public void setOrderUsePoint(String orderUsePoint) { this.orderUsePoint = orderUsePoint; }

    public String getTotalOrderProductPrice() { return this.totalOrderProductPrice; }
    public void setTotalOrderProductPrice(String totalOrderProductPrice) { this.totalOrderProductPrice = totalOrderProductPrice; }

    public ArrayList<CustomerOrderInfoModel> getOrderInfoList() {
        return orderInfoList;
    }
    public void setOrderInfoList(ArrayList<CustomerOrderInfoModel> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
