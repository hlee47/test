package org.androidtown.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.ProductDetailModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoginFragment extends Fragment implements View.OnClickListener, OnResponseCallback {

    private EditText editTextId;
    private EditText editTextPassword;
    private String mCustomerId;
    private String mCustomerPassword;
    private String mCustomerEmailBytes;
    private String mCustomerPasswordBytes;
    private ArrayList<CustomerOrderModel> mCustomerOrderList;
    private CustomerInfoModel mCustomerInfoModel;
    private ArrayList<CustomerOrderInfoModel> mCustomerOrderInfoList;
    private View view;
    private OnItemClickedListener onItemClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onItemClickedListener = (OnItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onItemClickedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        mCustomerOrderList = new ArrayList<>();

        editTextId = view.findViewById(R.id.et_user_idoremail);
        editTextPassword = view.findViewById(R.id.et_user_password);
        Button buttonLogin = view.findViewById(R.id.btn_login);
        Button buttonLoginFacebook = view.findViewById(R.id.btn_login_facebook);
        buttonLogin.setOnClickListener(this);
        buttonLoginFacebook.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        mCustomerId = editTextId.getText().toString();
        mCustomerPassword = editTextPassword.getText().toString();
        switch (view.getId()){
            case R.id.btn_login:
                try {
                    byte[] emailBytes = mCustomerId.getBytes("UTF-8");
                    byte[] passwordBytes = mCustomerPassword.getBytes("UTF-8");
                    mCustomerEmailBytes = Base64.encodeToString(emailBytes, Base64.DEFAULT);
                    mCustomerPasswordBytes = Base64.encodeToString(passwordBytes, Base64.DEFAULT);
                    new CustomerLoginAPIClient("LOGIN_EMAIL", mCustomerEmailBytes, mCustomerPasswordBytes, null, null, getContext(), null, this);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_login_facebook:
                new CustomerLoginAPIClient("LOGIN_FACEBOOK", null, null, null, null, getContext(), null, this);
                break;
        }

    }

    @Override
    public void onResponseLogin(String response) {
        if(response.matches("OK")) {
            MainActivity activity = (MainActivity) getActivity();
            activity.onClick(this.view);
        }
    }

    @Override
    public void onSuccessLogin(CustomerInfoModel customerInfoModel) {
        this.mCustomerInfoModel = customerInfoModel;
        new CustomerLoginAPIClient("SHOP_ORDER_LIST_DETAIL", mCustomerInfoModel.getCustomerNo(), null, null, mCustomerInfoModel.getShopOrderNumberList(), getContext(), null, this);
        new CustomerLoginAPIClient("SHOP_CARTS_BY_CUSTOMER", "","", null, null ,getContext(), onItemClickedListener, null);
        onItemClickedListener.updateCustomerInfo(mCustomerInfoModel);
    }

    @Override
    public void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList) {
        mCustomerOrderList.clear();
        mCustomerOrderList.addAll(customerOrderList);
        onItemClickedListener.updateCustomerOrderList(mCustomerOrderList);
    }

    @Override
    public void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {
        mCustomerOrderInfoList.clear();
        mCustomerOrderInfoList.addAll(customerOrderInfoList);
        onItemClickedListener.updateCustomerOrderInfoList(mCustomerOrderInfoList);
    }

    @Override
    public void onSuccessProductDetailPull(ProductDetailModel productDetailModel) {

    }
}
