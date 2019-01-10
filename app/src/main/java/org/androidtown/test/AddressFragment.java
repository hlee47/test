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

import org.androidtown.test.adapters.RecyclerViewAddressSelectAdapter;
import org.androidtown.test.models.AddressListModel;

import java.util.ArrayList;
import java.util.Collections;


public class AddressFragment extends Fragment implements View.OnClickListener {

    private AddressListModel userSelectedAddress;
    private OnItemClickedListener mOnItemClickedListener;
    private RecyclerViewAddressSelectAdapter recyclerViewAddressSelectAdapter;

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
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        Button addAddress = view.findViewById(R.id.btn_address_add);
        Button selectAddress = view.findViewById(R.id.btn_address_select);
        addAddress.setOnClickListener(this);
        selectAddress.setOnClickListener(this);

        // Get arguments from MainActivity and set it as mUserAddress
        // Sort the address list by default on top
        ArrayList<AddressListModel> mUserAddressList = (ArrayList<AddressListModel>) getArguments().getSerializable("userAddressList");
        sortByDefault(mUserAddressList);

        // Set RecyclerView
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_address);
        recyclerView.setHasFixedSize(true);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set RecyclerViewAdapter depending on button clicked
//        if (getArguments().getInt("buttonId") == R.id.button_address_list) {
//            addAddress.setVisibility(View.GONE);
//            selectAddress.setVisibility(View.VISIBLE);
//            recyclerViewAddressSelectAdapter = new RecyclerViewAddressSelectAdapter(context, mUserAddressList, mOnItemClickedListener);
//            recyclerView.setAdapter(recyclerViewAddressSelectAdapter);
//        } else {
//            addAddress.setVisibility(View.VISIBLE);
//            selectAddress.setVisibility(View.GONE);
//            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context, mUserAddressList, mOnItemClickedListener);
//            recyclerView.setAdapter(recyclerViewAdapter);
//        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_address_select) {
            userSelectedAddress = recyclerViewAddressSelectAdapter.getSelectedAddress();
            mOnItemClickedListener.selectedAddress(userSelectedAddress);
        }
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    private ArrayList<AddressListModel> sortByDefault(ArrayList<AddressListModel> userAddressList) {
        if(!userAddressList.get(0).getDefault().equals("true")) {
            for(int i = 0; i < userAddressList.size(); i++) {
                if(userAddressList.get(i).getDefault().equals("true")) {
                    Collections.rotate(userAddressList.subList(0, i+1), 1);
                }
            }
        }
        return userAddressList;
    }

}
