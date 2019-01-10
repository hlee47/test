package org.androidtown.test;

import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.ProductDetailModel;

import java.util.ArrayList;

public interface OnResponseCallback {
    void onResponseLogin(String response);
    void onSuccessLogin(CustomerInfoModel customerInfoModel);
    void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList);
    void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList);
    void onSuccessProductDetailPull(ProductDetailModel productDetailModel);
}

