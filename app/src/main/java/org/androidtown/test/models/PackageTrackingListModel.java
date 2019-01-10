package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageTrackingListModel implements Serializable {

    private String senderName;
    private String receiverName;
    private String invoiceNo;
    private String complete;
    private String recipient;
    private TrackingDetailsListModel trackingDetailsList;

    public PackageTrackingListModel() {

    }

    public PackageTrackingListModel(String senderName, String receiverName, String invoiceNo, String complete, String recipient, TrackingDetailsListModel trackingDetailsList) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.invoiceNo = invoiceNo;
        this.complete = complete;
        this.recipient = recipient;
        this.trackingDetailsList = trackingDetailsList;
    }


    public TrackingDetailsListModel getTrackingDetailsList() { return trackingDetailsList; }
    public void setTrackingDetailsList(TrackingDetailsListModel trackingDetailsList) { this.trackingDetailsList = trackingDetailsList; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName= receiverName; }

    public String getInvoiceNo() { return invoiceNo; }
    public void setInvoiceNo(String invoiceNo) { this.invoiceNo = invoiceNo; }

    public String getComplete() { return complete; }
    public void setComplete(String complete) { this.complete = complete; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
}
