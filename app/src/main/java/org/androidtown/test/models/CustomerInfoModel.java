package org.androidtown.test.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomerInfoModel implements Serializable {

    private String customerNo, memberNo, customerGradeNo, customerName, customerEmail;
    private String customerId;
    private String customerPassword;
    private String signupForm;
    private String signupKey;
    private String uniqueKey;
    private String kakaoKey;
    private String customerAddress;
    private String customerPhone;
    private String customerBirthDate;
    private String customerOptions;
    private String customerGrade;
    private String customerPoint;
    private String withdrawal;
    private String customerSumOfOrderPrice;
    private String serialNumber;
    private String customerOrderCountNo;
    private String refundBankName;
    private String refundBankAccountHolder;
    private String refundBankAccount;
    private String customerMemo;
    private String loginDate;
    private String customerGradeExpireDate;
    private String updateDate;
    private String createdDate;
    private String blogPostLikeList;
    private String boardPostLikeList;
    private String customerGradeTitle;
    private String customerDi;
    private String customerCommId;
    private String customerSex;
    private String customerSumOfUsedPoint;
    private String customerRecommenderNo;
    private String customerRecommenderIdOrEmail;
    private String loginFailCount;
    private String smsMarketingAgreement;
    private String smsMarketingAgreementUpdateDate;
    private String emailMarketingAgreement;
    private String emailMarketingAgreementUpdateDate;
    private ArrayList<String> shopOrderNumberList;

    public CustomerInfoModel() {
    }

    public String getCustomerNo() { return this.customerNo; }
    public void setCustomerNo(String customerNo) { this.customerNo = customerNo; }

    public String getMemberNo() { return this.memberNo; }
    public void setMemberNo(String memberNo) { this.memberNo = memberNo; }

    public String getCustomerGradeNo() { return this.customerGradeNo; }
    public void setCustomerGradeNo(String customerGradeNo) { this.customerGradeNo = customerGradeNo; }

    public String getCustomerName() { return this.customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return this.customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerId() { return this.customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerPassword() { return this.customerPassword; }
    public void setCustomerPassword(String customerPassword) { this.customerPassword = customerPassword; }

    public String getSignupForm() { return this.signupForm; }
    public void setSignupFrom(String signupForm) { this.signupForm = signupForm; }

    public String getSignupKey() { return this.signupKey; }
    public void setSignupKey(String signupKey) { this.signupKey = signupKey; }

    public String getUniqueKey() { return this.uniqueKey; }
    public void setUniqueKey(String uniqueKey) { this.uniqueKey = uniqueKey; }

    public String getKakaoKey() { return this.kakaoKey; }
    public void setKakaoKey(String kakaoKey) { this.kakaoKey = kakaoKey; }

    public String getCustomerAddress() { return this.customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public String getCustomerPhone() { return this.customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getCustomerBirthDate() { return this.customerBirthDate; }
    public void setCustomerBirthDate(String customerBirthDate) { this.customerBirthDate = customerBirthDate; }

    public String getCustomerOptions() { return this.customerOptions; }
    public void setCustomerOptions(String customerOptions) { this.customerOptions = customerOptions; }

    public String getCustomerGrade() { return this.customerGrade; }
    public void setCustomerGrade(String customerGrade) { this.customerGrade = customerGrade; }

    public String getCustomerPoint() { return this.customerPoint; }
    public void setCustomerPoint(String customerPoint) { this.customerPoint = customerPoint; }

    public String getWithdrawal() { return this.withdrawal; }
    public void setWithdrawal(String withdrawal) { this.withdrawal = withdrawal; }

    public String getCustomerSumOfOrderPrice() { return this.customerSumOfOrderPrice; }
    public void setCustomerSumOfOrderPrice(String customerSumOfOrderPrice) { this.customerSumOfOrderPrice = customerSumOfOrderPrice; }

    public String getSerialNumber() { return this.serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getCustomerOrderCountNo() { return this.customerOrderCountNo; }
    public void setCustomerOrderCountNo(String customerOrderCountNo) { this.customerOrderCountNo = customerOrderCountNo; }

    public String getRefundBankName() { return this.refundBankName; }
    public void setRefundBankName(String refundBankName) { this.refundBankName = refundBankName; }

    public String getRefundBankAccountHolder() { return this.refundBankAccountHolder; }
    public void setRefundBankAccountHolder(String refundBankAccountHolder) { this.refundBankAccountHolder = refundBankAccountHolder; }

    public String getRefundBankAccount() { return this.refundBankAccount; }
    public void setRefundBankAccount(String refundBankAccount) { this.refundBankAccount = refundBankAccount; }

    public String getCustomerMemo() { return this.customerMemo; }
    public void setCustomerMemo(String customerMemo) { this.customerMemo = customerMemo; }

    public String getLoginDate() { return this.loginDate; }
    public void setLoginDate(String loginDate) { this.loginDate = loginDate; }

    public String getCustomerGradeExpireDate() { return this.customerGradeExpireDate; }
    public void setCustomerGradeExpireDate(String customerGradeExpireDate) { this.customerGradeExpireDate = customerGradeExpireDate; }

    public String getUpdateDate() { return this.updateDate; }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }

    public String getCreatedDate() { return this.createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getBlogPostLikeList() { return this.blogPostLikeList; }
    public void setBlogPostLikeList(String blogPostLikeList) { this.blogPostLikeList = blogPostLikeList; }

    public String getBoardPostLikeList() { return this.boardPostLikeList; }
    public void setBoardPostLikeList(String boardPostLikeList) { this.boardPostLikeList = boardPostLikeList; }

    public String getCustomerGradeTitle() { return this.customerGradeTitle; }
    public void setCustomerGradeTitle(String customerGradeTitle) { this.customerGradeTitle = customerGradeTitle; }

    public String getCustomerDi() { return this.customerDi; }
    public void setCustomerDi(String customerDi) { this.customerDi = customerDi; }

    public String getCustomerCommId() { return this.customerCommId; }
    public void setCustomerCommId(String customerCommId) { this.customerCommId = customerCommId; }

    public String getCustomerSex() { return this.customerSex; }
    public void setCustomerSex(String customerSex) { this.customerSex = customerSex; }

    public String getCustomerSumOfUsedPoint() { return this.customerSumOfUsedPoint; }
    public void setCustomerSumOfUsedPoint(String customerSumOfUsedPoint) { this.customerSumOfUsedPoint = customerSumOfUsedPoint; }

    public String getCustomerRecommenderNo() { return this.customerRecommenderNo; }
    public void setCustomerRecommenderNo(String customerRecommenderNo) { this.customerRecommenderNo = customerRecommenderNo; }

    public String getCustomerRecommenderIdOrEmail() { return this.customerRecommenderIdOrEmail; }
    public void setCustomerRecommenderIdOrEmail(String customerRecommenderIdOrEmail) { this.customerRecommenderIdOrEmail = customerRecommenderIdOrEmail; }

    public String getLoginFailCount() { return this.loginFailCount; }
    public void setLoginFailCount(String loginFailCount) { this.loginFailCount = loginFailCount; }

    public String getSmsMarketingAgreement() { return this.smsMarketingAgreement; }
    public void setSmsMarketingAgreement(String smsMarketingAgreement) { this.smsMarketingAgreement = smsMarketingAgreement; }

    public String getSmsMarketingAgreementUpdateDate() { return this.smsMarketingAgreementUpdateDate; }
    public void setSmsMarketingAgreementUpdateDate(String smsMarketingAgreementUpdateDate) { this.smsMarketingAgreementUpdateDate = smsMarketingAgreementUpdateDate; }

    public String getEmailMarketingAgreement() { return this.emailMarketingAgreement; }
    public void setEmailMarketingAgreement(String emailMarketingAgreement) { this.emailMarketingAgreement = emailMarketingAgreement; }

    public String getEmailMarketingAgreementUpdateDate() { return this.emailMarketingAgreementUpdateDate; }
    public void setEmailMarketingAgreementUpdateDate(String emailMarketingAgreementUpdateDate) { this.emailMarketingAgreementUpdateDate = emailMarketingAgreementUpdateDate; }

    public ArrayList<String> getShopOrderNumberList() { return  this.shopOrderNumberList; }
    public void setShopOrderNumberList(ArrayList<String> shopOrderNumberList) { this.shopOrderNumberList = shopOrderNumberList; }
}
