package org.androidtown.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.androidtown.test.adapters.RecyclerViewOrderListAdapter;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.ProductDetailModel;

import java.util.ArrayList;

public class DeliveryDetailFragment extends Fragment implements View.OnClickListener, OnResponseCallback {

    private CustomerOrderModel mCustomerOrderModel;
    private ArrayList<CustomerOrderInfoModel> mCustomerOrderInfoList;
    private OnItemClickedListener onItemClickedListener;
    private RecyclerViewOrderListAdapter recyclerViewOrderListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<CustomerOrderModel> mCustomerOrderList = (ArrayList<CustomerOrderModel>) getArguments().getSerializable("customerOrderList");
        int position = (int) getArguments().getSerializable("position");
        mCustomerOrderModel = mCustomerOrderList.get(position);
        mCustomerOrderInfoList = mCustomerOrderModel.getOrderInfoList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_delivery_detail, container, false);
        TextView tvCreatedDate = rootView.findViewById(R.id.tv_created_date);
        TextView tvOrderNumber = rootView.findViewById(R.id.tv_order_number);
        TextView tvOrderStatus = rootView.findViewById(R.id.tv_order_status);
        TextView tvOrderName = rootView.findViewById(R.id.tv_order_name);
        TextView tvOrderAddress = rootView.findViewById(R.id.tv_order_address);
        TextView tvOrderAddressDetail = rootView.findViewById(R.id.tv_order_address_detail);
        TextView tvOrderPhone = rootView.findViewById(R.id.tv_order_phone);
        TextView tvTotalOrderProductPrice = rootView.findViewById(R.id.tv_total_order_product_price);
        TextView tvDeliveryPrice = rootView.findViewById(R.id.tv_delivery_price);
        TextView tvOrderUsePoint = rootView.findViewById(R.id.tv_order_use_point);
        TextView tvOrderPrice = rootView.findViewById(R.id.tv_order_price);
        tvCreatedDate.setText(mCustomerOrderModel.getCreatedDate());
        tvOrderNumber.setText(mCustomerOrderModel.getOrderNo());
        tvOrderStatus.setText(mCustomerOrderModel.getOrderStatus());
        tvOrderName.setText(mCustomerOrderModel.getOrderName());
        tvOrderAddress.setText(mCustomerOrderModel.getOrderAddress());
        tvOrderAddressDetail.setText(" ");
        tvOrderPhone.setText(mCustomerOrderModel.getOrderPhone());
        tvTotalOrderProductPrice.setText(mCustomerOrderModel.getTotalOrderProductPrice());
        tvDeliveryPrice.setText(mCustomerOrderModel.getDeliveryPrice());
        tvOrderUsePoint.setText(mCustomerOrderModel.getOrderUsePoint());
        tvOrderPrice.setText(mCustomerOrderModel.getOrderPrice());
        Button buttonTrack = rootView.findViewById(R.id.btn_package_track);
        buttonTrack.setOnClickListener(this);

        // Set RecyclerView
        Context context = rootView.getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_order_detail_list);
        recyclerView.setHasFixedSize(true);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set RecyclerViewAdapter
        recyclerViewOrderListAdapter = new RecyclerViewOrderListAdapter(context, mCustomerOrderModel, mCustomerOrderInfoList, onItemClickedListener);
        recyclerView.setAdapter(recyclerViewOrderListAdapter);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    @Override
    public void onResponseLogin(String response) { }

    @Override
    public void onSuccessLogin(CustomerInfoModel customerInfoModel) { }

    @Override
    public void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList) { }

    @Override
    public void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {
        mCustomerOrderInfoList.clear();
        mCustomerOrderInfoList.addAll(customerOrderInfoList);
        recyclerViewOrderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessProductDetailPull(ProductDetailModel productDetailModel) {

    }
}
