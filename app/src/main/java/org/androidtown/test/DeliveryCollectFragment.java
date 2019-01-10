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

public class DeliveryCollectFragment extends Fragment {

//    HistoryListModel userHistoryList;
//    HistoryListModel userDeliveryOpen;
//    HistoryListModel userDeliveryClose;
//    ArrayList<HistoryListModel> userCollectOpenList;
//    ArrayList<HistoryListModel> userCollectCloseList;
//    ArrayList<HistoryListModel> userCollectReadyList;
    private ArrayList<CustomerOrderModel> customerOrderReadyList;
    private ArrayList<CustomerOrderModel> customerOrderOpenList;
    private ArrayList<CustomerOrderModel> customerOrderCloseList;

    private OnItemClickedListener mOnItemClickedListener;
    private RecyclerViewDeliveryAdapter recyclerViewCollectAdapter;

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
        View view = inflater.inflate(R.layout.fragment_delivery_collect, container, false);
        Context context = view.getContext();
        customerOrderReadyList = new ArrayList<>();
        customerOrderOpenList = new ArrayList<>();
        customerOrderCloseList = new ArrayList<>();

        // Get arguments from MainActivity and set it as mUserAddress

        ArrayList<CustomerOrderModel> mCustomerOrderList = (ArrayList<CustomerOrderModel>) getArguments().getSerializable("customerOrderList");
        for(CustomerOrderModel customerOrder : mCustomerOrderList) {
            if (customerOrder.getOrderStatus().matches("requestRefund")) {
                customerOrderOpenList.add(new CustomerOrderModel(customerOrder));
            } else if (customerOrder.getOrderStatus().matches("refunded")) {
                customerOrderCloseList.add(new CustomerOrderModel(customerOrder));
            }
        }

        // Set RecyclerView
        RecyclerView recyclerViewCollectOpen = view.findViewById(R.id.recycler_view_collect_open);
        recyclerViewCollectOpen.setHasFixedSize(true);
        RecyclerView recyclerViewCollectClose = view.findViewById(R.id.recycler_view_collect_closed);
        recyclerViewCollectClose.setHasFixedSize(true);
        RecyclerView recyclerViewCollectReady = view.findViewById(R.id.recycler_view_collect_ready);
        recyclerViewCollectReady.setHasFixedSize(true);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCollectOpen.setLayoutManager(layoutManager);
        recyclerViewCollectClose.setLayoutManager(layoutManager2);
        recyclerViewCollectReady.setLayoutManager(layoutManager3);

        // Set RecyclerViewAdapter depending on button clicked
        recyclerViewCollectAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderOpenList, mOnItemClickedListener);
        recyclerViewCollectOpen.setAdapter(recyclerViewCollectAdapter);
        recyclerViewCollectAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderCloseList, mOnItemClickedListener);
        recyclerViewCollectClose.setAdapter(recyclerViewCollectAdapter);
        recyclerViewCollectAdapter = new RecyclerViewDeliveryAdapter(context, customerOrderReadyList, mOnItemClickedListener);
        recyclerViewCollectReady.setAdapter(recyclerViewCollectAdapter);

        return view;

    }
}
