package org.androidtown.test.models;

import java.io.Serializable;

public class ProductReviewCommentModel implements Serializable {
    private int commentProductNo;
    private int customerNo;
    private int postProductNo;
    private int productNo;
    private String commentWriter;
    private String commentContent;
    private String createdDate;
    private String updatedDate;

    public ProductReviewCommentModel() {}

    ProductReviewCommentModel(ProductReviewCommentModel productReviewCommentModel) {
        this.commentProductNo = productReviewCommentModel.getCommentProductNo();
        this.customerNo = productReviewCommentModel.getCustomerNo();
        this.postProductNo = productReviewCommentModel.getPostProductNo();
        this.productNo = productReviewCommentModel.getProductNo();
        this.commentWriter = productReviewCommentModel.getCommentWriter();
        this.commentContent = productReviewCommentModel.getCommentContent();
        this.createdDate = productReviewCommentModel.getCreatedDate();
        this.updatedDate = productReviewCommentModel.getUpdatedDate();
    }

    public int getCommentProductNo() {
        return commentProductNo;
    }
    public void setCommentProductNo(int commentProductNo) {
        this.commentProductNo = commentProductNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getPostProductNo() {
        return postProductNo;
    }
    public void setPostProductNo(int postProductNo) {
        this.postProductNo = postProductNo;
    }

    public int getProductNo() {
        return productNo;
    }
    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getCommentWriter() {
        return commentWriter;
    }
    public void setCommentWriter(String commentWriter) {
        this.commentWriter = commentWriter;
    }

    public String getCommentContent() {
        return commentContent;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}

