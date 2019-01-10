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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import org.androidtown.test.DeliveryDetailFragment;
import org.androidtown.test.MainActivity;
import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.R;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.CustomerOrderInfoModel;

import java.util.ArrayList;

public class RecyclerViewDeliveryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int DELIVERY_ORDER = 0;
    private static final int DELIVERY_COLLECT = 1;
    private Context mContext;
    private ArrayList<CustomerOrderModel> mCustomerOrderList;
    private ArrayList<CustomerOrderInfoModel> mCustomerOrderInfoList;
    private LayoutInflater layoutInflater;
    private OnItemClickedListener mOnItemClickedListener;

    public ImageView itemImage;
    private TextView tvOrderItemName;
    private TextView tvOrderItemPrice;
    private TextView tvOrderPriceTotal;
    private TextView tvOrderItemQuantity;
    private TextView tvOrderStatus;
    private TextView tvOrderDate;
    private TextView tvOrderStatusDate;
    private TextView tvOrderItemPoint;
    private TableRow trOrderItemQuantity;
    private TableRow trOrderItemPrice;
    private TableRow trOrderPriceTotal;
    private TableRow trOrderDate;
    private TableRow trOrderStatus;
    private TableRow trOrderStatusDate;
    private TableRow trOrderItemPoint;
    private Button buttonCollect;

    public RecyclerViewDeliveryAdapter(Context context, ArrayList<CustomerOrderModel> customerOrderList, OnItemClickedListener itemClickedListener) {
        this.mContext = context;
        this.mCustomerOrderList = customerOrderList;
        this.layoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_order_product_card, parent, false);
        switch(viewType) {
            case DELIVERY_ORDER:
                return new ViewHolderDeliveryStatus(view, mOnItemClickedListener);
            case DELIVERY_COLLECT:
                return new ViewHolderDeliveryCollect(view, mOnItemClickedListener);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CustomerOrderModel customerOrder = mCustomerOrderList.get(position);
        switch (viewHolder.getItemViewType()) {
            case DELIVERY_ORDER:
//                ViewHolderDeliveryStatus viewHolderDeliveryStatus = (ViewHolderDeliveryStatus) viewHolder;
                tvOrderItemName.setText(customerOrder.getOrderTitle());
                tvOrderDate.setText(customerOrder.getCreatedDate());
                tvOrderStatus.setText(customerOrder.getOrderStatus());
                tvOrderStatusDate.setText(customerOrder.getUpdateDate());
                trOrderPriceTotal.setVisibility(View.GONE);
                trOrderItemQuantity.setVisibility(View.GONE);
                trOrderItemPrice.setVisibility(View.GONE);
                trOrderItemPoint.setVisibility(View.GONE);
                buttonCollect.setVisibility(View.GONE);

                break;
            case DELIVERY_COLLECT:
//                ViewHolderDeliveryCollect viewHolderDeliveryCollect = (ViewHolderDeliveryCollect) viewHolder;
                tvOrderItemName.setText(customerOrder.getOrderTitle());
                tvOrderDate.setText(customerOrder.getCreatedDate());
                tvOrderStatus.setText(customerOrder.getOrderStatus());
                tvOrderStatusDate.setText(customerOrder.getUpdateDate());
                tvOrderPriceTotal.setText(customerOrder.getOrderPrice());
                trOrderItemQuantity.setVisibility(View.GONE);
                trOrderItemPrice.setVisibility(View.GONE);
                trOrderItemPoint.setVisibility(View.GONE);
                buttonCollect.setVisibility(View.GONE);

//                if(mUserHistoryList.get(position).getDeliveryCollect() == 0) {
//                    viewHolderDeliveryCollect.buttonCollect.setVisibility(View.VISIBLE);
//                } else if(mUserHistoryList.get(position).getDeliveryCollect() == 1) {
//                    viewHolderDeliveryCollect.buttonCollect.setVisibility(View.GONE);
//                } else {
//                    viewHolderDeliveryCollect.buttonCollect.setVisibility(View.GONE);
//                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        String orderStatus = mCustomerOrderList.get(position).getOrderStatus();
        if(orderStatus.matches("notYetDeposit")) {
            viewType = 0;
        } else if(orderStatus.matches("paySuccess")) {
            viewType = 0;
        } else if(orderStatus.matches("readyForDelivery")) {
            viewType = 0;
        } else if(orderStatus.matches("delivering")) {
            viewType = 0;
        } else if(orderStatus.matches("delivered")) {
            viewType = 0;
        } else if(orderStatus.matches("serviceCompleted")) {
            viewType = 0;
        } else if(orderStatus.matches("requestRefund")) {
            viewType = 1;
        } else if(orderStatus.matches("refunded")) {
            viewType = 1;
        } else {
            viewType=0;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        return mCustomerOrderList.size();
    }

    public class ViewHolderDeliveryStatus extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickedListener mOnItemClickedListener;

        public ViewHolderDeliveryStatus(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            mOnItemClickedListener = itemClickedListener;
            itemView.setOnClickListener(this);
            SetContentView(itemView);
            buttonCollect.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable("customerOrderList", mCustomerOrderList);
            args.putSerializable("position", getAdapterPosition());
            MainActivity activity = (MainActivity) view.getContext();
            Fragment frag = new DeliveryDetailFragment();
            frag.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).addToBackStack(null).commit();
        }
    }

    public class ViewHolderDeliveryCollect extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickedListener mOnItemClickedListener;

        public ViewHolderDeliveryCollect(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            mOnItemClickedListener = itemClickedListener;
            itemView.setOnClickListener(this);
            SetContentView(itemView);
            buttonCollect.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            mUserHistoryList.get(getAdapterPosition()).setDeliveryCollect(1);
//            notifyDataSetChanged();
        }
    }

    private void SetContentView(View itemView) {
        tvOrderItemName = itemView.findViewById(R.id.tv_order_item_name);
        tvOrderItemQuantity = itemView.findViewById(R.id.tv_order_item_quantity);
        tvOrderItemPrice = itemView.findViewById(R.id.tv_order_item_price);
        tvOrderPriceTotal = itemView.findViewById(R.id.tv_order_price_total);
        tvOrderDate = itemView.findViewById(R.id.tv_order_date);
        tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
        tvOrderStatusDate = itemView.findViewById(R.id.tv_order_status_date);
        tvOrderItemPoint = itemView.findViewById(R.id.tv_order_item_point);
        trOrderItemQuantity = itemView.findViewById(R.id.tr_order_item_quantity);
        trOrderItemPrice = itemView.findViewById(R.id.tr_order_item_price);
        trOrderPriceTotal = itemView.findViewById(R.id.tr_order_price_total);
        trOrderDate = itemView.findViewById(R.id.tr_order_date);
        trOrderStatus = itemView.findViewById(R.id.tr_order_status);
        trOrderStatusDate = itemView.findViewById(R.id.tr_order_status_date);
        trOrderItemPoint = itemView.findViewById(R.id.tr_order_item_point);
        buttonCollect = itemView.findViewById(R.id.button_user_collect_empty);
    }
}
