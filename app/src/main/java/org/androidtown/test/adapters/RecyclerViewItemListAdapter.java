package org.androidtown.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.androidtown.test.CustomerLoginAPIClient;
import org.androidtown.test.GlideApp;
import org.androidtown.test.OnItemClickedListener;
import org.androidtown.test.OnResponseCallback;
import org.androidtown.test.ProductDetailFragment;
import org.androidtown.test.R;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductModel;

import java.util.ArrayList;

public class RecyclerViewItemListAdapter extends RecyclerView.Adapter<RecyclerViewItemListAdapter.MyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CARD = 1;
    private Context mContext;
    private ArrayList<ProductModel> mProductList;
    private ProductModel mProductModel;
    private ProductDetailModel mProductDetailModel;
    private LayoutInflater mLayoutInflater;
    private OnItemClickedListener mOnItemClickedListener;
    private OnResponseCallback mOnResponseCallback = new OnResponseCallback() {
        @Override
        public void onResponseLogin(String response) {

        }

        @Override
        public void onSuccessLogin(CustomerInfoModel customerInfoModel) {

        }

        @Override
        public void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList) {

        }

        @Override
        public void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {

        }

        @Override
        public void onSuccessProductDetailPull(ProductDetailModel productDetailModel) {
            mProductDetailModel = productDetailModel;
            Bundle args = new Bundle();
            args.putSerializable("productDetailModel", mProductDetailModel);
            args.putSerializable("productModel", mProductModel);
            AppCompatActivity activity = (AppCompatActivity) mContext;
            Fragment frag = new ProductDetailFragment();
            frag.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).addToBackStack(null).commit();
        }
    };

    public RecyclerViewItemListAdapter(Context context, ArrayList<ProductModel> productList, OnItemClickedListener itemClickedListener) {
        this.mContext = context;
        this.mProductList = productList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mOnItemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.fragment_product_card, parent, false);
        switch(viewType) {
            case TYPE_HEADER:
                return new MyViewHolder(view, mOnItemClickedListener);
            case TYPE_CARD:
                return new MyViewHolder(view, mOnItemClickedListener);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, final int position) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = 120;
        switch (viewHolder.getItemViewType()) {
            case TYPE_HEADER:
                viewHolder.vProductLayout.setLayoutParams(params);
                viewHolder.tvProductName.setText("수제 유기농 콤부차로 건강을!");
                viewHolder.tvCompany.setVisibility(View.GONE);
                viewHolder.itemImage.setVisibility(View.GONE);
                viewHolder.llPrice.setVisibility(View.GONE);
                viewHolder.llPriceDiscount.setVisibility(View.GONE);
                break;
            case TYPE_CARD:
                GlideApp.with(mContext.getApplicationContext()).asBitmap().load(getItem(position).getProductThumbnail()).placeholder(new ColorDrawable(Color.WHITE)).override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter().into(viewHolder.itemImage);
                viewHolder.tvProductName.setText(getItem(position).getProductName());
                viewHolder.tvProductPrice.setText(String.valueOf(getItem(position).getProductPrice()));
                viewHolder.tvProductPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.tvProductPriceDiscount.setText(String.valueOf(getItem(position).getProductDiscountPrice()));
                //viewHolder.tvProductPrice.setBackgroundResource(mProductList.get(position).getProductSelect() ? R.drawable.btn_rct_wht_lhtgry_dot_blck : R.drawable.btn_rct_wht_dot_blck);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mProductList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_CARD;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private ProductModel getItem(int position) {
        return mProductList.get(position - 1);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickedListener mOnItemClickedListener;
        private ImageView itemImage;
        private TextView tvCompany;
        private TextView tvProductName;
        private LinearLayout llPrice;
        private TextView tvProductPrice;
        private LinearLayout llPriceDiscount;
        private TextView tvProductPriceDiscount;
        private View vProductLayout;
        private boolean isClicked;

        private MyViewHolder(@NonNull View itemView, OnItemClickedListener itemClickedListener) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_view_product);
            tvCompany = itemView.findViewById(R.id.tv_company);
            tvProductName = itemView.findViewById(R.id.text_product_name);
            llPrice = itemView.findViewById(R.id.ll_price);
            tvProductPrice = itemView.findViewById(R.id.text_product_price);
            llPriceDiscount = itemView.findViewById(R.id.ll_price_discount);
            tvProductPriceDiscount = itemView.findViewById(R.id.text_product_price_discount);
            vProductLayout = itemView.findViewById(R.id.ll_item_card);
            vProductLayout.setOnClickListener(this);
            itemView.setOnClickListener(this);

            mOnItemClickedListener = itemClickedListener;
        }

        @Override
        public void onClick(View view) {
//            isClicked = mProductList.get(getAdapterPosition()).getProductSelect();
//            isClicked = !isClicked; // toggle the boolean flag
//            view.setBackgroundResource(isClicked ? R.drawable.btn_sqr_gry_brdr_drkgry : R.drawable.btn_sqr_wht);
//            mProductList.get(getAdapterPosition()).setProductSelect(isClicked);

            mProductModel = mProductList.get(getAdapterPosition() - 1);
            new CustomerLoginAPIClient("SHOP_PRODUCT_BY_PRODUCT", mProductModel.getProductNo(), null, null, null, mContext, null, mOnResponseCallback);
//            MainActivity activity = (MainActivity) mContext;
//            activity.onClick(view);
        }
    }

//    public ArrayList<ProductModel> getSelectedProduct() {
//        ArrayList<ProductModel> selectedItems = new ArrayList<>();
//        for(ProductModel productModel : mProductList) {
//            if(productModel.getProductSelect()) {
//                selectedItems.add(productModel);
//            }
//        }
//        return selectedItems;
//    }
}
