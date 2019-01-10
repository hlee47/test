package org.androidtown.test;

import org.androidtown.test.models.AddressListModel;
import org.androidtown.test.models.CartModel;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductModel;
import org.androidtown.test.models.PaymentListModel;

import java.util.ArrayList;

public interface OnItemClickedListener {
    void selectedProduct(ProductModel productModel);
    void selectedAddress(AddressListModel selectedAddress);
    void selectedPayment(PaymentListModel selectedPayment);
    void selectedCartNo(int cartNo);

    void addAddress(AddressListModel userAddress);
    void removeAddress(int position);
    void defaultAddress();

    void addPayment(PaymentListModel userPayment);
    void removePayment(int position);
    void defaultPayment();

    void updateCustomerInfo(CustomerInfoModel customerInfoModel);
    void updateCustomerOrderList(ArrayList<CustomerOrderModel> customerOrderDataList);
    void updateCustomerOrderInfoList(ArrayList<CustomerOrderInfoModel> customerOrderInfoList);

    void onSuccessProductListPull(ArrayList<ProductModel> productList);
    void onSuccessProductImgList(ArrayList<String> imgURLList);

    void onSuccessPageDataList(ArrayList<PageDataModel> pageDataList);
    void onSuccessBuyNowCartNo(String buyNowCartNo);
    void onSuccessCartList(ArrayList<CartModel> cartList);
}
