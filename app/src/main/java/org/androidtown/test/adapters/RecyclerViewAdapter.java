package org.androidtown.test.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.test.AddressDetailFragment;
import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.R;
import org.androidtown.test.models.AddressListModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AddressListModel> mUserAddressList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickedListener mOnItemClickedListener;

    public RecyclerViewAdapter(Context context, ArrayList<AddressListModel> userAddressList, OnItemClickedListener itemClickedListener) {
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

        // Make image visible for default address true
        if(mUserAddressList.get(position).getDefault().matches("true")) {
            viewHolder.defaultAddressImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.defaultAddressImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mUserAddressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userAddress;
        private ImageView defaultAddressImage;
        OnItemClickedListener mOnItemClickedListener;

        private MyViewHolder(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            userAddress = itemView.findViewById(R.id.text_user_address);
            defaultAddressImage = itemView.findViewById(R.id.image_view_address_default);
            mOnItemClickedListener = itemClickedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable("userAddressSelected", mUserAddressList.get(getAdapterPosition()));
            args.putSerializable("position",getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment frag = new AddressDetailFragment();
            frag.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        }

    }
}
