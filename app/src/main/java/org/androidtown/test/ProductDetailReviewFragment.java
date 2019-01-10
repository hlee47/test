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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.androidtown.test.adapters.RecyclerViewProductDetailsAdapter;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductReviewModel;

import java.util.ArrayList;
import java.util.Locale;

public class ProductDetailReviewFragment extends Fragment implements View.OnClickListener {

    private ProductDetailModel mProductDetailModel;
    private ArrayList<ProductReviewModel> mProductReviewList;
    private static ArrayList<ProductReviewModel> mProductReviewListRating5;
    private static ArrayList<ProductReviewModel> mProductReviewListRating4;
    private static ArrayList<ProductReviewModel> mProductReviewListRating3;
    private static ArrayList<ProductReviewModel> mProductReviewListRating2;
    private static ArrayList<ProductReviewModel> mProductReviewListRating1;
    private RecyclerViewProductDetailsAdapter recyclerViewProductDetailsAdapter;
    private ProgressBar pb1;
    private ProgressBar pb2;
    private ProgressBar pb3;
    private ProgressBar pb4;
    private ProgressBar pb5;
    private TextView tvPb1Count;
    private TextView tvPb2Count;
    private TextView tvPb3Count;
    private TextView tvPb4Count;
    private TextView tvPb5Count;
    private TextView tvAvgRating;
    private TableRow tbRating5;
    private TableRow tbRating4;
    private TableRow tbRating3;
    private TableRow tbRating2;
    private TableRow tbRating1;
    private Button btnRvwWrite;
    private TableLayout tlRating;
    private TextView tvRvwNone;
    private int totalCount = 0;
    private int countRating1 = 0;
    private int countRating2 = 0;
    private int countRating3 = 0;
    private int countRating4 = 0;
    private int countRating5 = 0;
    private int totalRating = 0;
    private int rating = 0;
    private int avgRating = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductDetailModel = (ProductDetailModel) getArguments().getSerializable("productDetailModel");
        mProductReviewList = (ArrayList<ProductReviewModel>) getArguments().getSerializable("productReviewList");
        mProductReviewListRating5 = new ArrayList<>();
        mProductReviewListRating4 = new ArrayList<>();
        mProductReviewListRating3 = new ArrayList<>();
        mProductReviewListRating2 = new ArrayList<>();
        mProductReviewListRating1 = new ArrayList<>();

        ProductReviewModel productReviewModel;
        if(mProductReviewList.size() > 0){
            for(int i = 0; i < mProductReviewList.size(); i++) {
                productReviewModel = mProductReviewList.get(i);
                rating = Integer.parseInt(productReviewModel.getPostReviewRate());
                switch (rating) {
                    case 1:
                        mProductReviewListRating1.add(productReviewModel);
                        countRating1++;
                        break;
                    case 2:
                        mProductReviewListRating2.add(productReviewModel);
                        countRating2++;
                        break;
                    case 3:
                        mProductReviewListRating3.add(productReviewModel);
                        countRating3++;
                        break;
                    case 4:
                        mProductReviewListRating4.add(productReviewModel);
                        countRating4++;
                        break;
                    case 5:
                        mProductReviewListRating5.add(productReviewModel);
                        countRating5++;
                        break;
                    default:
                        break;
                }
                totalRating = totalRating + rating;
                totalCount++;
            }
            avgRating = totalRating/totalCount;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail_review, viewGroup, false);
        Context context = view.getContext();

        RecyclerView recyclerViewProductDetailReview = view.findViewById(R.id.rv_product_detail_review);
        recyclerViewProductDetailReview.setHasFixedSize(false);
        recyclerViewProductDetailReview.setItemAnimator(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        recyclerViewProductDetailReview.setLayoutManager(layoutManager);
        recyclerViewProductDetailsAdapter = new RecyclerViewProductDetailsAdapter(context, mProductReviewList, mProductDetailModel.getProductReviewCommentList());
        recyclerViewProductDetailReview.setAdapter(recyclerViewProductDetailsAdapter);

        pb1 = view.findViewById(R.id.pb_rating_1);
        pb2 = view.findViewById(R.id.pb_rating_2);
        pb3 = view.findViewById(R.id.pb_rating_3);
        pb4 = view.findViewById(R.id.pb_rating_4);
        pb5 = view.findViewById(R.id.pb_rating_5);
        tvPb1Count = view.findViewById(R.id.tv_rating_1_count);
        tvPb2Count = view.findViewById(R.id.tv_rating_2_count);
        tvPb3Count = view.findViewById(R.id.tv_rating_3_count);
        tvPb4Count = view.findViewById(R.id.tv_rating_4_count);
        tvPb5Count = view.findViewById(R.id.tv_rating_5_count);
        tvAvgRating = view.findViewById(R.id.tv_avg_rating);
        tvAvgRating.setOnClickListener(this);
        tbRating1 = view.findViewById(R.id.tr_rating_1);
        tbRating2 = view.findViewById(R.id.tr_rating_2);
        tbRating3 = view.findViewById(R.id.tr_rating_3);
        tbRating4 = view.findViewById(R.id.tr_rating_4);
        tbRating5 = view.findViewById(R.id.tr_rating_5);
        tbRating1.setOnClickListener(this);
        tbRating2.setOnClickListener(this);
        tbRating3.setOnClickListener(this);
        tbRating4.setOnClickListener(this);
        tbRating5.setOnClickListener(this);
        btnRvwWrite = view.findViewById(R.id.btn_rvw_write);
        btnRvwWrite.setText("후기 작성하기");
        tlRating = view.findViewById(R.id.tl_rating);
        tvRvwNone = view.findViewById(R.id.tv_rvw_none);
        tvRvwNone.setText("작성된 후기가 없습니다");

        if(mProductReviewList.size() > 0) {
            recyclerViewProductDetailReview.setVisibility(View.VISIBLE);
            tlRating.setVisibility(View.VISIBLE);
            tvRvwNone.setVisibility(View.GONE);

            tvAvgRating.setText(String.format(Locale.getDefault(),"%.1f", (double)avgRating));
            pb1.setProgress(countRating1);
            pb2.setProgress(countRating2);
            pb3.setProgress(countRating3);
            pb4.setProgress(countRating4);
            pb5.setProgress(countRating5);
            tvPb1Count.setText(String.valueOf(countRating1));
            tvPb2Count.setText(String.valueOf(countRating2));
            tvPb3Count.setText(String.valueOf(countRating3));
            tvPb4Count.setText(String.valueOf(countRating4));
            tvPb5Count.setText(String.valueOf(countRating5));

        } else {
            recyclerViewProductDetailReview.setVisibility(View.GONE);
            tlRating.setVisibility(View.GONE);
            tvRvwNone.setVisibility(View.VISIBLE);
        }



        return view;
    }

    @Override
    public void onClick(View v) {
        mProductReviewList.clear();
        switch (v.getId()) {
            case R.id.tr_rating_1:
                mProductReviewList.addAll(mProductReviewListRating1);
                break;
            case R.id.tr_rating_2:
                mProductReviewList.addAll(mProductReviewListRating2);
                break;
            case R.id.tr_rating_3:
                mProductReviewList.addAll(mProductReviewListRating3);
                break;
            case R.id.tr_rating_4:
                mProductReviewList.addAll(mProductReviewListRating4);
                break;
            case R.id.tr_rating_5:
                mProductReviewList.addAll(mProductReviewListRating5);
                break;
        }
        recyclerViewProductDetailsAdapter.notifyDataSetChanged();
    }
}
