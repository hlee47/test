package org.androidtown.test.models;


import java.io.Serializable;
import java.util.ArrayList;

public class PageDataModel implements Serializable {

    private int pageNo;
    private String pageLink;
    private String pageName;
    private int pageOrderNo;
    private ArrayList<String> pageImageURLList;

    public PageDataModel() {
    }

    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageLink() {
        return pageLink;
    }
    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public String getPageName() {
        return pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getPageOrderNo() {
        return pageOrderNo;
    }
    public void setPageOrderNo(int pageOrderNo) {
        this.pageOrderNo = pageOrderNo;
    }

    public ArrayList<String> getPageImageURLList() {
        return pageImageURLList;
    }
    public void setPageImageURLList(ArrayList<String> imageURLList) {
        this.pageImageURLList = imageURLList;
    }
}