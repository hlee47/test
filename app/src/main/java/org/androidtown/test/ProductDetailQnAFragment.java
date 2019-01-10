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
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidtown.test.adapters.RecyclerViewProductDetailsAdapter;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductReviewModel;

import java.util.ArrayList;

public class ProductDetailQnAFragment extends Fragment {

    private ProductDetailModel mProductDetailModel;
    private ArrayList<ProductReviewModel> mProductQnaList;
    private int NUM_IMG;
    private RecyclerViewProductDetailsAdapter recyclerViewProductDetailsAdapter;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail_qna, container, false);
        Context context = view.getContext();
        mProductDetailModel = (ProductDetailModel) getArguments().getSerializable("productDetailModel");
        mProductQnaList = (ArrayList<ProductReviewModel>) getArguments().getSerializable("productQnaList");
        RecyclerView recyclerViewProductDetailQnA = view.findViewById(R.id.rv_product_detail_qna);
        recyclerViewProductDetailQnA.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewProductDetailQnA.setLayoutManager(layoutManager);
        recyclerViewProductDetailsAdapter = new RecyclerViewProductDetailsAdapter(context, mProductQnaList, mProductDetailModel.getProductReviewCommentList());
        recyclerViewProductDetailQnA.setAdapter(recyclerViewProductDetailsAdapter);
        return view;
    }
}
