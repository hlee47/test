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

import org.androidtown.test.adapters.RecyclerViewPaymentListAdapter;
import org.androidtown.test.models.PaymentListModel;

import java.util.ArrayList;
import java.util.Collections;


public class PaymentFragment extends Fragment implements View.OnClickListener {

    private OnItemClickedListener itemClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemClickedListener = (OnItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onItemClickedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_payment, container, false);
        // Get arguments from MainActivity and set it as mUserAddress
        // Sort the address list by default on top
        ArrayList<PaymentListModel> mUserPaymentList = (ArrayList<PaymentListModel>) getArguments().getSerializable("userPaymentList");
        sortByDefault(mUserPaymentList);

        // Set RecyclerView
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_payment);
        recyclerView.setHasFixedSize(true);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set RecyclerViewAdapter
        RecyclerViewPaymentListAdapter recyclerViewPaymentListAdapter = new RecyclerViewPaymentListAdapter(context, mUserPaymentList, itemClickedListener);
        recyclerView.setAdapter(recyclerViewPaymentListAdapter);

        Button userPaymentAdd = view.findViewById(R.id.btn_payment_add);
        userPaymentAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    private ArrayList<PaymentListModel> sortByDefault(ArrayList<PaymentListModel> userPaymentList) {
        if(!userPaymentList.get(0).getDefault().matches("true")) {
            for(int i = 0; i < userPaymentList.size(); i++) {
                if(userPaymentList.get(i).getDefault().matches("true")) {
                    Collections.rotate(userPaymentList.subList(0, i+1), 1);
                }
            }
        }
        return userPaymentList;
    }
}
