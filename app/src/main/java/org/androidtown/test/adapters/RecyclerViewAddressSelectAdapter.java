package org.androidtown.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.R;
import org.androidtown.test.models.AddressListModel;

import java.util.ArrayList;

public class RecyclerViewAddressSelectAdapter extends RecyclerView.Adapter<RecyclerViewAddressSelectAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AddressListModel> mUserAddressList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickedListener mOnItemClickedListener;
    private int rowIndex = 0;

    public RecyclerViewAddressSelectAdapter(Context context, ArrayList<AddressListModel> userAddressList, OnItemClickedListener itemClickedListener) {
        this.mContext = context;
        this.mUserAddressList = userAddressList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.fragment_address_card, parent, false);
        return new MyViewHolder(view, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int position) {
        viewHolder.userAddress.setText(mUserAddressList.get(position).getUserAddress());
        viewHolder.defaultAddressImage.setVisibility(View.INVISIBLE);
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = position;
                notifyDataSetChanged();
            }
        });
        if(rowIndex == position) {
            viewHolder.itemLayout.setBackgroundResource(R.drawable.btn_rct_wht_lhtgry_dot_blck);
            viewHolder.userAddress.setTextColor(Color.BLACK);
        } else {
            viewHolder.itemLayout.setBackgroundResource(R.drawable.btn_rct_wht_dot_blck);
            viewHolder.userAddress.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return mUserAddressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        OnItemClickedListener mOnItemClickedListener;
        private TextView userAddress;
        private ImageView defaultAddressImage;
        private View itemLayout;

        private MyViewHolder(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            userAddress = itemView.findViewById(R.id.text_user_address);
            defaultAddressImage = itemView.findViewById(R.id.image_view_address_default);
            itemLayout = itemView.findViewById(R.id.layout_address_card);
            mOnItemClickedListener = itemClickedListener;
        }
    }

    public AddressListModel getSelectedAddress() {
        return mUserAddressList.get(rowIndex);
    }
}
