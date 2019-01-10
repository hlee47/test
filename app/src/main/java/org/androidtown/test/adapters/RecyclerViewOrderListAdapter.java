package org.androidtown.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.androidtown.test.GlideApp;
import org.androidtown.test.MainActivity;
import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.R;
import org.androidtown.test.models.CartModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.ProductModel;

import java.util.ArrayList;

public class RecyclerViewOrderListAdapter extends RecyclerView.Adapter<RecyclerViewOrderListAdapter.MyViewHolder> {

    private final static int TYPE_ORDER_PLACE = 0;
    private final static int TYPE_ORDER_HISTORY = 1;
    private int viewType = -1;

    private Context mContext;
    private CustomerOrderModel mCustomerOrderModel;
    private ArrayList<CustomerOrderInfoModel> mCustomerOrderInfoList;
    private ProductModel mProductModel;
    private ArrayList<ProductModel> mProductList;
    private CartModel mCartModel;
    private ArrayList<CartModel> mCartList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickedListener mOnItemClickedListener;

    private ImageView itemImage;
    private TextView tvOrderItemName;
    private TextView tvOrderItemPrice;
    private TextView tvOrderItemDiscountPrice;
    private TextView tvCurrencyUnit;
    private TextView tvOrderPriceTotal;
    private TextView tvOrderItemQuantity;
    private TextView tvOrderDate;
    private TextView tvOrderStatus;
    private TextView tvOrderStatusDate;
    private TextView tvOrderItemPoint;
    private TextView tvOrderItemRemove;
    private TableRow trOrderItemQuantity;
    private TableRow trOrderItemPrice;
    private TableRow trOrderPriceTotal;
    private TableRow trOrderDate;
    private TableRow trOrderStatus;
    private TableRow trOrderStatusDate;
    private TableRow trOrderItemPoint;
    private Button buttonCollect;

    public RecyclerViewOrderListAdapter(Context context, ArrayList<CartModel> cartList, OnItemClickedListener onItemClickedListener) {
        this.mContext = context;
        this.mCartList = cartList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = onItemClickedListener;
        this.viewType = TYPE_ORDER_PLACE;
    }

    public RecyclerViewOrderListAdapter(Context context, CustomerOrderModel customerOrderModel, ArrayList<CustomerOrderInfoModel> customerOrderInfoList, OnItemClickedListener itemClickedListener) {
        this.mContext = context;
        this.mCustomerOrderModel = customerOrderModel;
        this.mCustomerOrderInfoList = customerOrderInfoList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = itemClickedListener;
        this.viewType = TYPE_ORDER_HISTORY;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.fragment_order_product_card, parent, false);
        return new MyViewHolder(view, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int position) {
        String productName = null;
        String orderQuantity = null;
        String payedPrice = null;
        String discountPrice = null;
        String rewardPoint = null;
        switch (getViewType()) {
            case TYPE_ORDER_PLACE:
                System.out.println("********* "+mCartList.get(position).getCartNo()+"********** "+position);

                CartModel cartModel = mCartList.get(position);
                ProductModel productModel = mCartList.get(position).getProductModel();
                GlideApp.with(mContext.getApplicationContext()).asBitmap().load(productModel.getProductThumbnail()).placeholder(new ColorDrawable(Color.WHITE)).override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter().into(itemImage);
                productName = productModel.getProductName();
                orderQuantity = String.valueOf(cartModel.getCartQuantity());
                payedPrice = String.valueOf(productModel.getProductPrice());
                discountPrice = String.valueOf(productModel.getProductDiscountPrice());
                if(productModel.getProductDiscountPrice() > 0) {
                    tvOrderItemDiscountPrice.setText(discountPrice);
                    tvOrderItemPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    tvOrderItemPrice.setTextColor(ContextCompat.getColor(mContext, R.color.colorGold));
                    tvOrderItemDiscountPrice.setVisibility(View.GONE);
                    tvCurrencyUnit.setVisibility(View.GONE);
                }
                rewardPoint = String.valueOf(productModel.getRewardPointRate());
                break;
            case TYPE_ORDER_HISTORY:
                CustomerOrderInfoModel customerOrderInfo = mCustomerOrderInfoList.get(position);
                itemImage.setBackgroundResource(R.drawable.kombu_tae);
                productName = customerOrderInfo.getOrderProductName();
                orderQuantity = customerOrderInfo.getOrderQuantity();
                payedPrice = customerOrderInfo.getPayedPrice();
                rewardPoint = mCustomerOrderModel.getRewardPoint();
                break;
        }
        tvOrderItemName.setText(productName);
        tvOrderItemQuantity.setText(orderQuantity);
        tvOrderItemPrice.setText(payedPrice);
        tvOrderItemPoint.setText(rewardPoint);

        trOrderDate.setVisibility(View.GONE);
        trOrderPriceTotal.setVisibility(View.GONE);
        trOrderStatus.setVisibility(View.GONE);
        trOrderStatusDate.setVisibility(View.GONE);
        buttonCollect.setVisibility(View.GONE);
    }

    public int getViewType() {
        return this.viewType;
    }

    @Override
    public int getItemCount() {
        return getViewType()==TYPE_ORDER_PLACE ? mCartList.size() : mCustomerOrderInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickedListener mOnItemClickedListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_view_order_item);
            tvOrderItemName = itemView.findViewById(R.id.tv_order_item_name);
            tvOrderItemQuantity = itemView.findViewById(R.id.tv_order_item_quantity);
            tvOrderItemPrice = itemView.findViewById(R.id.tv_order_item_price);
            tvOrderItemDiscountPrice = itemView.findViewById(R.id.tv_order_item_discount_price);
            tvCurrencyUnit = itemView.findViewById(R.id.tv_currency_unit);
            tvOrderPriceTotal = itemView.findViewById(R.id.tv_order_price_total);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            tvOrderStatusDate = itemView.findViewById(R.id.tv_order_status_date);
            tvOrderItemPoint = itemView.findViewById(R.id.tv_order_item_point);
            tvOrderItemRemove = itemView.findViewById(R.id.tv_order_item_remove);
            trOrderItemQuantity = itemView.findViewById(R.id.tr_order_item_quantity);
            trOrderItemPrice = itemView.findViewById(R.id.tr_order_item_price);
            trOrderPriceTotal = itemView.findViewById(R.id.tr_order_price_total);
            trOrderDate = itemView.findViewById(R.id.tr_order_date);
            trOrderStatus = itemView.findViewById(R.id.tr_order_status);
            trOrderStatusDate = itemView.findViewById(R.id.tr_order_status_date);
            trOrderItemPoint = itemView.findViewById(R.id.tr_order_item_point);
            buttonCollect = itemView.findViewById(R.id.button_user_collect_empty);
            mOnItemClickedListener = itemClickedListener;
            tvOrderItemRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickedListener.selectedCartNo(mCartList.get(getAdapterPosition()).getCartNo());
            mCartList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
//            notifyItemRangeChanged(getAdapterPosition(), getItemCount());
            MainActivity activity = (MainActivity) mContext;
            activity.onClick(view);
        }
    }

}
