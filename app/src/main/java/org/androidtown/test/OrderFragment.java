package org.androidtown.test;

import android.content.Context;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Scroller;

import org.androidtown.test.adapters.RecyclerViewItemListAdapter;
import org.androidtown.test.adapters.ViewPagerAdapter;
import org.androidtown.test.models.AddressListModel;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.ProductModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OrderFragment extends Fragment implements View.OnClickListener{
    private static int currentPage = 0;
    private static int NUM_PAGES;
    AddressListModel mUserAddressSelected;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mViewPagerAdatper;
    private CustomerInfoModel mCustomerInfoModel;
    private ArrayList<CustomerOrderModel> mCustomerOrderList;
    private ArrayList<ProductModel> mProductList, mSelectedProductList;
    private PageDataModel mPageDataModel;
    private OnItemClickedListener mOnItemClickedListener;
    private RecyclerViewItemListAdapter recyclerViewItemListAdapter;
    private Timer timer;

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
        mPageDataModel = (PageDataModel) getArguments().getSerializable("pageDataModel");
        mProductList = (ArrayList<ProductModel>) getArguments().getSerializable("productList");
        mCustomerOrderList = (ArrayList<CustomerOrderModel>) getArguments().getSerializable("customerOrderList");
        mCustomerInfoModel = (CustomerInfoModel) getArguments().getSerializable("customerInfoModel");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_order, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().show();

        // Set banner images

        NUM_PAGES = mPageDataModel.getPageImageURLList().size();
        mViewPager = rootView.findViewById(R.id.vp_banner);
        mTabLayout = rootView.findViewById(R.id.tl_banner);
        mTabLayout.setTabRippleColor(null);
        mTabLayout.setupWithViewPager(mViewPager, true);
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(), null);
            mScroller.set(mViewPager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        mViewPagerAdatper = new ViewPagerAdapter(getContext(), NUM_PAGES, mPageDataModel);
        mViewPager.setAdapter(mViewPagerAdatper);

        // Auto start of banner (viewpager)
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1, 3000);

        Button buttonOrderAddressDefault = rootView.findViewById(R.id.btn_order_address_default);
        Button buttonOrderAddressNew = rootView.findViewById(R.id.btn_order_address_new);
        buttonOrderAddressDefault.setOnClickListener(this);
        buttonOrderAddressNew.setOnClickListener(this);

//        mUserAddressSelected = (AddressListModel) getArguments().getSerializable("userAddressSelected");
//        if(mUserAddressSelected == null) {
//            if(mUserAddressList.size() == 1) {
//                mUserAddressList.get(0).setDefault("true");
//                this.mUserAddressSelected = mUserAddressList.get(0); } else {
//                for(int i = 0; i < mUserAddressList.size(); i++) {
//                    if(mUserAddressList.get(i).getDefault()=="true"){
//                        this.mUserAddressSelected = mUserAddressList.get(i); } }
//                if(this.mUserAddressSelected == null) {
//                    mUserAddressList.get(0).setDefault("true");
//                    this.mUserAddressSelected = mUserAddressList.get(0); } }
//            textViewUserAddressSelected.setText(this.mUserAddressSelected.getUserAddress());
//        } else {
//            textViewUserAddressSelected.setText(this.mUserAddressSelected.getUserAddress());
//        }

        // Reset isClicked to false on view created
        for(ProductModel productModel : mProductList) {
            productModel.setProductSelect(false);
        }

        // Set RecyclerViewAdapter
        Context context = rootView.getContext();
        recyclerViewItemListAdapter = new RecyclerViewItemListAdapter(context, mProductList, mOnItemClickedListener);

        // Set RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (recyclerViewItemListAdapter.getItemViewType(position)) {
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                    default:
                        return 1;
                }
            }
        });
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_products);
        recyclerView.setHasFixedSize(true);
        // Set GridLayout Manager
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewItemListAdapter);

        return rootView;
    }

    @Override
    public void onClick(View view) {
//        if(view.getId() != R.id.button_address_list) {
//            userSelectedItemList = recyclerViewItemListAdapter.getSelectedItems();
//            if(userSelectedItemList.size()==0) {
//                Toast.makeText(getContext(), "상품을 선택해 주세요", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        mOnItemClickedListener.selectedProduct(userSelectedItemList);
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 1000;

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, (android.view.animation.Interpolator) interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }
}
