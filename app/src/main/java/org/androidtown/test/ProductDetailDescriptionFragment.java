package org.androidtown.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidtown.test.adapters.RecyclerViewProductDetailsAdapter;
import org.androidtown.test.models.ProductDetailModel;

public class ProductDetailDescriptionFragment extends Fragment {

    private ProductDetailModel mProductDetailModel;
    private int NUM_IMG;
    private RecyclerViewProductDetailsAdapter recyclerViewProductDetailsAdapter;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail_description, container, false);
        Context mContext = view.getContext();
        mProductDetailModel = (ProductDetailModel) getArguments().getSerializable("productDetailModel");
        NUM_IMG = mProductDetailModel.getProductDescriptionImgURLList().size();

        LinearLayout linearLayout = view.findViewById(R.id.ll_product_detail_description);
        for(int i=0; i<NUM_IMG; i++) {
            final ImageView image = new ImageView(mContext);
            image.setAdjustViewBounds(true);
            linearLayout.addView(image);
//            final int finalI = i;
            Glide.with(mContext.getApplicationContext()).asBitmap().load(mProductDetailModel.getProductDescriptionImgURLList().get(i)).apply(new RequestOptions().override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(new ColorDrawable(Color.WHITE)).fitCenter()).into(image);
//            Picasso.get().load(mProductDetailModel.getProductDescriptionImgURLList().get(i)).networkPolicy(NetworkPolicy.OFFLINE).into(image, new Callback() {
//                @Override
//                public void onSuccess() {}
//                @Override
//                public void onError(Exception e) {
//                    Picasso.get().load(mProductDetailModel.getProductDescriptionImgURLList().get(finalI)).priority(Picasso.Priority.HIGH).resize(500,0).into(image);
//                    }
//            });
        }

        return view;

    }
}
