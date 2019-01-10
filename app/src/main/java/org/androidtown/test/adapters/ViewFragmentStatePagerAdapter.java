package org.androidtown.test.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import org.androidtown.test.CustomViewPager;
import org.androidtown.test.DeliveryCollectFragment;
import org.androidtown.test.DeliveryCurrentFragment;
import org.androidtown.test.ProductDetailDescriptionFragment;
import org.androidtown.test.ProductDetailQnAFragment;
import org.androidtown.test.ProductDetailReviewFragment;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductReviewModel;

import java.util.ArrayList;

public class ViewFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final static int DELIVERY_DETAILS = 0;
    private final static int PRODUCT_DETAILS = 1;
    private final static int CURRENT = 0;
    private final static int OPEN = 1;
    private final static int CANCEL = 2;
    private final static int DESCRIPTION = 0;
    private final static int REVIEWS = 1;
    private final static int QnA = 2;
    private int NUM_ITEMS;
    private static int viewType;
    private ArrayList<CustomerOrderModel> mCustomerOrderList;
    private ProductDetailModel mProductDetailModel;
    private ProductReviewModel productReviewModel;
    private ArrayList<ProductReviewModel> productReviewList;
    private ProductReviewModel productQnaModel;
    private ArrayList<ProductReviewModel> productQnaList;
    private int mCurrentPosition = -1;

    public ViewFragmentStatePagerAdapter(FragmentManager fm, int NUM_ITEMS, int viewType, ProductDetailModel productDetailModel) {
        super(fm);
        this.NUM_ITEMS = NUM_ITEMS;
        this.mProductDetailModel = productDetailModel;
        setViewType(viewType);
        ArrayList<ProductReviewModel> mProductReviewList = mProductDetailModel.getProductReviewList();
        productReviewList = new ArrayList<>();
        productQnaList = new ArrayList<>();
        for(int i=0; i<mProductReviewList.size(); i++){
            if(mProductReviewList.get(i).getPostQnaStatus() == "null") {
                productReviewList.add(new ProductReviewModel(mProductReviewList.get(i)));
            } else {
                productQnaList.add(new ProductReviewModel(mProductReviewList.get(i)));
            }
        }
    }

    public ViewFragmentStatePagerAdapter(FragmentManager fm, int NUM_ITEMS, int viewType, ArrayList<CustomerOrderModel> customerOrderList) {
        super(fm);
        this.NUM_ITEMS = NUM_ITEMS;
        mCustomerOrderList = customerOrderList;
        setViewType(viewType);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        Bundle bundle = new Bundle();
        if(getViewType() == DELIVERY_DETAILS) {
            bundle.putSerializable("customerOrderList", mCustomerOrderList);
            switch (position) {
                case CURRENT:
                    frag = new DeliveryCurrentFragment();
                    frag.setArguments(bundle);
                    return frag;
                case OPEN:
                    frag = new DeliveryCollectFragment();
                    frag.setArguments(bundle);
                    return frag;
                case CANCEL:
                    frag = new DeliveryCollectFragment();
                    frag.setArguments(bundle);
                    return frag;
                default:
                    return null;
            }
        } else if(getViewType() == PRODUCT_DETAILS)
            bundle.putSerializable("productDetailModel", mProductDetailModel);

            switch (position) {
                case DESCRIPTION:
                    frag = new ProductDetailDescriptionFragment();
                    frag.setArguments(bundle);
                    return frag;
                case REVIEWS:
                    bundle.putSerializable("productReviewList", productReviewList);
                    frag = new ProductDetailReviewFragment();
                    frag.setArguments(bundle);
                    return frag;
                case QnA:
                    bundle.putSerializable("productQnaList", productQnaList);
                    frag = new ProductDetailQnAFragment();
                    frag.setArguments(bundle);
                    return frag;
                default:
                    return null;
            }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        int type = getViewType();
        if(type == DELIVERY_DETAILS) {
            switch(position) {
                case CURRENT:
                    return "완료된 주문";
                case OPEN:
                    return "진행중 주문";
                case CANCEL:
                    return "취소된 주문";
            }
        } else if(type == PRODUCT_DETAILS)
            switch (position) {
                case DESCRIPTION:
                    return "제품 정보";
                case REVIEWS:
                    return "후기";
                case QnA:
                    return "Q & A";
            }
        return null;
    }

    private int getViewType() {
        return viewType;
    }
    private void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            CustomViewPager pager = (CustomViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }
}