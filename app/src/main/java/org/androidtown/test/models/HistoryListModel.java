package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryListModel implements Serializable {

    private AddressListModel shippingAddress;
    private ArrayList<ProductModel> itemOrdered;
    private String dateOrdered;
    private PaymentListModel paymentMethod;
    private String orderTotalPrice;
    private String orderTotalCredit;
    private String orderId;
    private String trackingId;
    private int deliveryType = -1;
    private int deliveryOrder;
    private int deliveryCollect;
    private int depositAvailable;
    private int depositCredited;

    public HistoryListModel(HistoryListModel historyListModel) {
        this.shippingAddress = historyListModel.shippingAddress;
        this.itemOrdered = historyListModel.itemOrdered;
        this.paymentMethod = historyListModel.paymentMethod;
        this.dateOrdered = historyListModel.dateOrdered;
        this.orderTotalPrice = historyListModel.orderTotalPrice;
        this.orderTotalCredit = historyListModel.orderTotalCredit;
        this.orderId = historyListModel.orderId;
        this.trackingId = historyListModel.trackingId;
        this.deliveryType = historyListModel.deliveryType;
        this.deliveryOrder = historyListModel.deliveryOrder;
        this.deliveryCollect = historyListModel.deliveryCollect;
    }

    public HistoryListModel(AddressListModel shippingAddress, ArrayList<ProductModel> itemOrdered, PaymentListModel paymentMethod, String dateOrdered, String orderTotalPrice, String orderTotalCredit, String orderId, String trackingId, int deliveryOrder, int deliveryCollect) {
        this.shippingAddress = shippingAddress;
        this.itemOrdered = itemOrdered;
        this.paymentMethod = paymentMethod;
        this.dateOrdered = dateOrdered;
        this.orderTotalPrice = orderTotalPrice;
        this.orderTotalCredit = orderTotalCredit;
        this.orderId = orderId;
        this.trackingId = trackingId;
        this.deliveryOrder = deliveryOrder;
        this.deliveryCollect = deliveryCollect;
    }

    public AddressListModel getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(AddressListModel shippingAddress) { this.shippingAddress = shippingAddress; }

    public ArrayList<ProductModel> getItemOrdered() { return itemOrdered; }
    public void setItemOrdered(ArrayList<ProductModel> itemOrdered) { this.itemOrdered = itemOrdered; }

    public PaymentListModel getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentListModel paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getDateOrdered() { return dateOrdered; }
    public void setDateOrdered(String dateOrdered) { this.dateOrdered = dateOrdered; }

    public String getOrderTotalPrice() { return orderTotalPrice; }
    public void setOrderTotalPrice(String orderTotalPrice) { this.orderTotalPrice = orderTotalPrice; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }

    public int getDeliveryType() { return deliveryType; }
    public void setDeliveryType(int deliveryType) { this.deliveryType = deliveryType; }

    public int getDeliveryOrder() { return deliveryOrder; }
    public void setDeliveryOrder(int deliveryOrder) { this.deliveryOrder = deliveryOrder; }

    public int getDeliveryCollect() { return deliveryCollect; }
    public void setDeliveryCollect(int deliveryCollect) { this.deliveryCollect = deliveryCollect; }

}
