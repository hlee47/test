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

import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.R;
import org.androidtown.test.PaymentDetailFragment;
import org.androidtown.test.models.PaymentListModel;

import java.util.ArrayList;

public class RecyclerViewPaymentListAdapter extends RecyclerView.Adapter<RecyclerViewPaymentListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<PaymentListModel> mUserPaymentList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickedListener mOnItemClickedListener;

    public RecyclerViewPaymentListAdapter(Context context, ArrayList<PaymentListModel> userPaymentList, OnItemClickedListener itemClickedListener) {
        this.mContext = context;
        this.mUserPaymentList = userPaymentList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.fragment_user_payment_card, parent, false);
        return new MyViewHolder(view, mOnItemClickedListener); }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int position) {
        viewHolder.paymentNickname.setText(mUserPaymentList.get(position).getPaymentName());

        // Make image visible for default payment true
        if(mUserPaymentList.get(position).getDefault().matches("true")) {
            viewHolder.defaultPaymentImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.defaultPaymentImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mUserPaymentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView paymentNickname;
        private ImageView defaultPaymentImage;
        OnItemClickedListener mOnItemClickedListener;

        private MyViewHolder(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            paymentNickname = itemView.findViewById(R.id.text_user_payment_nickname);
            defaultPaymentImage = itemView.findViewById(R.id.image_view_payment_default);
            mOnItemClickedListener = itemClickedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable("userPaymentList", mUserPaymentList);
            args.putSerializable("userPaymentSelected", mUserPaymentList.get(getAdapterPosition()));
            args.putSerializable("position", getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment frag = new PaymentDetailFragment();
            frag.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        }
    }
}
