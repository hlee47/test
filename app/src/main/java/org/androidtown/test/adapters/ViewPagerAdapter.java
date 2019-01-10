package org.androidtown.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.androidtown.test.GlideApp;
import org.androidtown.test.R;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.ProductModel;

public class ViewPagerAdapter extends PagerAdapter {
    private int NUM_ITEMS;
    private final int PRODUCT_DETAIL = 0;
    private final int HOME_BANNER = 1;
    private int VIEW_TYPE = -1;
    private PageDataModel mPageDataModel;
    private ProductModel mProductModel;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ViewPagerAdapter(Context context, int NUM_ITEMS, ProductModel productModel) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.NUM_ITEMS = NUM_ITEMS;
        this.mProductModel = productModel;
        setViewType(PRODUCT_DETAIL);
    }

    public ViewPagerAdapter(Context context, int NUM_ITEMS, PageDataModel pageDataModel) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.NUM_ITEMS = NUM_ITEMS;
        this.mPageDataModel = pageDataModel;
        setViewType(HOME_BANNER);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View imageLayout = mLayoutInflater.inflate(R.layout.frame_banner, container, false);
        final ImageView ivBanner = imageLayout.findViewById(R.id.iv_banner);
        switch(getViewType()) {
            case HOME_BANNER:
                GlideApp.with(mContext.getApplicationContext()).asBitmap().load("https://contents.sixshop.com" + mPageDataModel.getPageImageURLList().get(position)).override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(new ColorDrawable(Color.WHITE)).fitCenter().into(ivBanner);
                break;
            case PRODUCT_DETAIL:
                GlideApp.with(mContext.getApplicationContext()).asBitmap().load(mProductModel.getProductThumbnail()).override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(new ColorDrawable(Color.WHITE)).fitCenter().into(ivBanner);
                break;
        }

        container.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private void setViewType(int viewType) {
        this.VIEW_TYPE = viewType;
    }

    private int getViewType() {
        return VIEW_TYPE;
    }
}