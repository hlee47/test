package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductReviewModel implements Serializable {
    private int postProductNo;
    private int customerNo;
    private int boardProductNo;
    private int productNo;
    private int postProductOrderNo;
    private int postProductIdx;
    private String recentLinkedProductName;
    private ArrayList<String> postProductImg;
    private String postProductContent;
    private String postProductWriter;
    private String productName;
    private String postQnaStatus;
    private String updatedDate;
    private String createdDate;
    private String postReviewRate;
    private String linkedPostCommentProductList;
    private boolean isExpanded = false;

    public ProductReviewModel() {}

    public ProductReviewModel(ProductReviewModel productReviewModel) {
        this.postProductNo = productReviewModel.getPostProductNo();
        this.customerNo = productReviewModel.getCustomerNo();
        this.boardProductNo = productReviewModel.getBoardProductNo();
        this.productNo = productReviewModel.getProductNo();
        this.postProductOrderNo = productReviewModel.getPostProductOrderNo();
        this.postProductIdx = productReviewModel.getPostProductIdx();
        this.recentLinkedProductName = productReviewModel.getRecentLinkedProductName();
        this.postProductImg = productReviewModel.getPostProductImg();
        this.postProductContent = productReviewModel.getPostProductContent();
        this.postProductWriter = productReviewModel.getPostProductWriter();
        this.productName = productReviewModel.getProductName();
        this.postQnaStatus = productReviewModel.getPostQnaStatus();
        this.updatedDate = productReviewModel.getUpdatedDate();
        this.createdDate = productReviewModel.getCreatedDate();
        this.postReviewRate = productReviewModel.getPostReviewRate();
        this.linkedPostCommentProductList = productReviewModel.getLinkedPostCommentProductList();
    }

    public int getPostProductNo() {
        return postProductNo;
    }
    public void setPostProductNo(int postProductNo) {
        this.postProductNo = postProductNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getBoardProductNo() {
        return boardProductNo;
    }
    public void setBoardProductNo(int boardProductNo) {
        this.boardProductNo = boardProductNo;
    }

    public int getProductNo() {
        return productNo;
    }
    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getPostProductOrderNo() {
        return postProductOrderNo;
    }
    public void setPostProductOrderNo(int postProductOrderNo) {
        this.postProductOrderNo = postProductOrderNo;
    }

    public int getPostProductIdx() {
        return postProductIdx;
    }
    public void setPostProductIdx(int postProductIdx) {
        this.postProductIdx = postProductIdx;
    }

    public String getRecentLinkedProductName() {
        return recentLinkedProductName;
    }
    public void setRecentLinkedProductName(String recentLinkedProductName) {
        this.recentLinkedProductName = recentLinkedProductName;
    }

    public ArrayList<String> getPostProductImg() {
        return postProductImg;
    }
    public void setPostProductImg(ArrayList<String> postProductImg) {
        this.postProductImg = new ArrayList<>(postProductImg);
    }

    public String getPostProductContent() {
        return postProductContent;
    }
    public void setPostProductContent(String postProductContent) {
        this.postProductContent = postProductContent;
    }

    public String getPostProductWriter() {
        return postProductWriter;
    }
    public void setPostProductWriter(String postProductWriter) {
        this.postProductWriter = postProductWriter;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPostQnaStatus() {
        return postQnaStatus;
    }
    public void setPostQnaStatus(String postQnaStatus) {
        this.postQnaStatus = postQnaStatus;
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

    public String getPostReviewRate() {
        return postReviewRate;
    }
    public void setPostReviewRate(String postReviewRate) {
        this.postReviewRate = postReviewRate;
    }

    public String getLinkedPostCommentProductList() {
        return linkedPostCommentProductList;
    }
    public void setLinkedPostCommentProductList(String linkedPostCommentProductList) {
        this.linkedPostCommentProductList = linkedPostCommentProductList;
    }

    public boolean isExpanded() {
        return isExpanded;
    }
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}

