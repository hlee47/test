package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentListModel extends ArrayList<PaymentListModel> implements Serializable {

    private String paymentDefault;
    private String paymentNickname;
    private int paymentNumber1;
    private int paymentNumber2;
    private int paymentNumber3;
    private int paymentNumber4;
    private int paymentExpirationMonth;
    private int paymentExpirationYear;
    private int paymentPassword;
    private int paymentVerification;

    public PaymentListModel(String paymentDefault, String paymentNickname, int paymentNumber1, int paymentNumber2, int paymentNumber3, int paymentNumber4, int paymentExpirationMonth, int paymentExpirationYear, int paymentPassword, int paymentVerification ) {
        this.paymentDefault = paymentDefault;
        this.paymentNickname = paymentNickname;
        this.paymentNumber1 = paymentNumber1;
        this.paymentNumber2 = paymentNumber2;
        this.paymentNumber3 = paymentNumber3;
        this.paymentNumber4 = paymentNumber4;
        this.paymentExpirationMonth = paymentExpirationMonth;
        this.paymentExpirationYear = paymentExpirationYear;
        this.paymentPassword = paymentPassword;
        this.paymentVerification = paymentVerification;
    }

    public String getDefault() { return paymentDefault; }
    public void setDefault(String paymentDefault) { this.paymentDefault = paymentDefault; }

    public String getPaymentName() { return paymentNickname; }
    public void setPaymentNumber(String paymentNickname) { this.paymentNickname = paymentNickname; }

    public int getPaymentNumber1() { return paymentNumber1; }
    public void setPaymentNumber1 (int paymentNumber1) { this.paymentNumber1 = paymentNumber1; }

    public int getPaymentNumber2() { return paymentNumber2; }
    public void setPaymentNumber2 (int paymentNumber2) { this.paymentNumber1 = paymentNumber2; }

    public int getPaymentNumber3() { return paymentNumber3; }
    public void setPaymentNumber3 (int paymentNumber3) { this.paymentNumber1 = paymentNumber3; }

    public int getPaymentNumber4() { return paymentNumber4; }
    public void setPaymentNumber4 (int paymentNumber4) { this.paymentNumber4 = paymentNumber4; }

    public int getPaymentExpirationMonth() { return paymentExpirationMonth; }
    public void setPaymentExpirationMonth (int paymentExpirationMonth) { this.paymentExpirationMonth = paymentExpirationMonth; }

    public int getPaymentExpirationYear() { return paymentExpirationYear; }
    public void setPaymentExpirationYear(int paymentExpirationYear) { this.paymentExpirationYear = paymentExpirationYear; }

    public int getPaymentPassword() { return paymentPassword; }
    public void setPaymentPassword(int paymentPassword) { this.paymentPassword = paymentPassword; }

    public int getPaymentVerification() { return paymentVerification; }
    public void setPaymentVerification(int paymentVerification) { this.paymentVerification = paymentVerification; }

}
