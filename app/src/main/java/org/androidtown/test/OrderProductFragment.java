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
import android.widget.TextView;

import org.androidtown.test.adapters.RecyclerViewOrderListAdapter;
import org.androidtown.test.models.CartModel;
import org.androidtown.test.models.ProductModel;

import java.util.ArrayList;

public class OrderProductFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private ArrayList<ProductModel> mOrderProductList;
    private static ProductModel mProductModel;
    private static ArrayList<CartModel> mCartList;
    private static RecyclerView recyclerView;
    private static RecyclerViewOrderListAdapter recyclerViewOrderListAdapter;
    private static OnItemClickedListener mOnItemClickedListener;
    private static TextView orderProductPrice;
    private static TextView orderProductDiscountTotal;
    private static TextView orderDeliveryFee;
    private static TextView orderProductPriceTotal;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnItemClickedListener = (OnItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onItemClickedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCartList = (ArrayList<CartModel>) getArguments().getSerializable("cartList");
        mCartList = new ArrayList<>();
        mOrderProductList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_order_product, container, false);
        Button confirmProduct = rootView.findViewById(R.id.btn_order_product_confirm);
        confirmProduct.setOnClickListener(this);

        orderProductPrice = rootView.findViewById(R.id.tv_product_price_total);
        orderProductDiscountTotal = rootView.findViewById(R.id.tv_product_discount_total);
        orderDeliveryFee = rootView.findViewById(R.id.tv_delivery_fee);
        orderProductPriceTotal = rootView.findViewById(R.id.tv_order_price_total);

        // Calculate Total Price and set textview prices
//        mOrderProductList = (ArrayList<ProductModel>) getArguments().getSerializable("orderProductList");
//        for(int i = 0; i < mOrderProductList.size(); i++) {
//            int productPrice = mOrderProductList.get(i).getProductDiscountPrice();
//            int productQuantity  = mOrderProductList.get(i).getProductQuantity();
//            int productPriceTotal = productPrice * productQuantity;
//            totalPrice = totalPrice + productPriceTotal; }

        setOrderProductList(mCartList);
//        for(int i=0; i<mCartList.size(); i++) {
//            mProductModel = mCartList.get(i).getProductModel();
//            mOrderProductList.add(mProductModel);
//            productPrice = mProductModel.getProductDiscountPrice()<=0 ? mProductModel.getProductPrice() : mProductModel.getProductDiscountPrice() ;
//            productQuantity = mCartList.get(i).getCartQuantity();
//            productPriceTotal = productPrice * productQuantity;
//            totalPrice = totalPrice + productPriceTotal;
//        }
//        orderProductPrice.setText(String.valueOf(totalPrice));
//        if(totalPrice >= 30000) {
//            orderDeliveryFee.setText("0");
//            orderProductPriceTotal.setText(String.valueOf(totalPrice));
//        } else {
//            orderDeliveryFee.setText("3000");
//            orderProductPriceTotal.setText(String.valueOf(totalPrice + 3000));
//        }

        // Set RecyclerView
        mContext = rootView.getContext();
        recyclerView = rootView.findViewById(R.id.recycler_view_order_list);
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(null);

        // Set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set RecyclerViewAdapter
        recyclerViewOrderListAdapter = new RecyclerViewOrderListAdapter(mContext, mCartList, mOnItemClickedListener);
        recyclerView.setAdapter(recyclerViewOrderListAdapter);

        return rootView;
    }

    public void setOrderProductList(ArrayList<CartModel> cartList) {
        int productPriceTotal = 0;
        int productDiscountTotal = 0;
        for(int i=0; i<mCartList.size(); i++) {
            mProductModel = mCartList.get(i).getProductModel();
            int productPrice = mProductModel.getProductPrice();
            int productDiscountPrice = mProductModel.getProductDiscountPrice();
            if(productDiscountPrice < 0 ) {
                productDiscountPrice = productPrice;
            }
            int productQuantity = cartList.get(i).getCartQuantity();
            productPriceTotal = productPriceTotal + (productPrice * productQuantity);
            productDiscountTotal = productDiscountTotal + (productDiscountPrice * productQuantity);
        }

        orderProductPrice.setText(String.valueOf(productPriceTotal));
        orderProductDiscountTotal.setText(String.valueOf(productPriceTotal - productDiscountTotal));
        if(productDiscountTotal >= 3000) {
            orderDeliveryFee.setText("0");
            orderProductPriceTotal.setText(String.valueOf(productDiscountTotal));
        } else {
            orderDeliveryFee.setText("3000");
            orderProductPriceTotal.setText(String.valueOf(productDiscountTotal + 3000));
        }
    }

    public void notifyDataSetChange(ArrayList<CartModel> cartList) {
        mCartList.clear();
        mCartList.addAll(cartList);
        setOrderProductList(cartList);
        recyclerViewOrderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

}
