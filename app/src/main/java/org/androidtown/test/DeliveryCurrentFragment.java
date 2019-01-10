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

import org.androidtown.test.adapters.RecyclerViewDeliveryAdapter;
import org.androidtown.test.models.CustomerOrderModel;

import java.util.ArrayList;

public class DeliveryCurrentFragment extends Fragment {

    private ArrayList<CustomerOrderModel> customerOrderReadyList;
    private ArrayList<CustomerOrderModel> customerOrderOpenList;
    private ArrayList<CustomerOrderModel> customerOrderCloseList;
    private OnItemClickedListener mOnItemClickedListener;
    private RecyclerViewDeliveryAdapter recyclerViewDeliveryAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnItemClickedListener = (OnItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onItemClickedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_status, container, false);
        Context context = view.getContext();
        customerOrderReadyList = new ArrayList<>();
        customerOrderOpenList = new ArrayList<>();
        customerOrderCloseList = new ArrayList<>();

        // Get arguments from MainActivity and set it as mUserAddress
        ArrayList<CustomerOrderModel> mCustomerOrderList = (ArrayList<CustomerOrderModel>) getArguments().getSerializable("customerOrderList");
        for(CustomerOrderModel customerOrder : mCustomerOrderList) {
            if(customerOrder.getOrderStatus().matches("notYetDeposit")) {
                customerOrderReadyList.add(new CustomerOrderModel(customerOrder));
            } else if(customerOrder.getOrderStatus().matches( "paySucceess")) {
                customerOrderReadyList.add(new CustomerOrderModel(customerOrder));
            } else if(customerOrder.getOrderStatus().matches("readyForDelivery")) {
                customerOrderReadyList.add(new CustomerOrderModel(customerOrder));
            } else if(customerOrder.getOrderStatus().matches("delivering")){
                customerOrderOpenList.add(new CustomerOrderModel(customerOrder));
            } else if(customerOrder.getOrderStatus().matches("delivered")){
                customerOrderCloseList.add(new CustomerOrderModel(customerOrder));
            } else if(customerOrder.getOrderStatus().matches("serviceCompleted")){
                customerOrderCloseList.add(new CustomerOrderModel(customerOrder));
            } else {

            }
        }

        // Set RecyclerView
        RecyclerView recyclerViewDeliveryOpen = view.findViewById(R.id.recycler_view_delivery_open);
        recyclerViewDeliveryOpen.setHasFixedSize(false);
        RecyclerView recyclerViewDeliveryClose = view.findViewById(R.id.recycler_view_delivery_closed);
        recyclerViewDeliveryClose.setHasFixedSize(false);
        RecyclerView recyclerViewDeliveryReady = view.findViewById(R.id.recycler_view_delivery_ready);
        recyclerViewDeliveryClose.setHasFixedSize(false);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewDeliveryOpen.setLayoutManager(layoutManager);
        recyclerViewDeliveryClose.setLayoutManager(layoutManager2);
        recyclerViewDeliveryReady.setLayoutManager(layoutManager3);

        // Set RecyclerViewAdapter depending on button clicked
        recyclerViewDeliveryAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderOpenList, mOnItemClickedListener);
        recyclerViewDeliveryOpen.setAdapter(recyclerViewDeliveryAdapter);
        recyclerViewDeliveryAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderCloseList, mOnItemClickedListener);
        recyclerViewDeliveryClose.setAdapter(recyclerViewDeliveryAdapter);
        recyclerViewDeliveryAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderReadyList, mOnItemClickedListener);
        recyclerViewDeliveryReady.setAdapter(recyclerViewDeliveryAdapter);

        return view;
    }

}
