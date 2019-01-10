package org.androidtown.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.test.adapters.ViewFragmentStatePagerAdapter;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.ProductDetailModel;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment implements View.OnClickListener, OnResponseCallback {

    private CustomViewPager viewPager;
    private TabLayout tabLayout;
    private TabItem tabOrderOpen;
    private TabItem tabOrderCurrent;
    private TabItem tabOrderCancelled;
    private ViewFragmentStatePagerAdapter viewFragmentStatePagerAdapter;
    private CustomerInfoModel mCustomerInfoModel;
    private ArrayList<CustomerOrderModel> mCustomerOrderList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerOrderList = new ArrayList<>();
        mCustomerInfoModel = (CustomerInfoModel) getArguments().getSerializable("customerInfoModel");
        mCustomerOrderList = (ArrayList<CustomerOrderModel>) getArguments().getSerializable("customerOrderList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        tabOrderCurrent = view.findViewById(R.id.tab_order_current);
        tabOrderOpen = view.findViewById(R.id.tab_order_open);
        tabOrderCancelled = view.findViewById(R.id.tab_order_canceled);
        viewPager = view.findViewById(R.id.vp_delivery);
        viewFragmentStatePagerAdapter = new ViewFragmentStatePagerAdapter(getChildFragmentManager(),tabLayout.getTabCount(), 0, mCustomerOrderList);
        viewPager.setAdapter(viewFragmentStatePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponseLogin(String response) {

    }

    @Override
    public void onSuccessLogin(CustomerInfoModel customerInfoModel) {

    }

    @Override
    public void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList) {
//        mCustomerOrderList.clear();
//        mCustomerOrderList.addAll(customerOrderList);
//        viewFragmentStatePagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {

    }

    @Override
    public void onSuccessProductDetailPull(ProductDetailModel productDetailModel) {

    }
}
