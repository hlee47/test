package org.androidtown.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import org.androidtown.test.R;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductReviewCommentModel;
import org.androidtown.test.models.ProductReviewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewProductDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int REVIEW = 0;
    private static final int QnA = 1;
    private Context mContext;
    private ProductDetailModel mProductDetailModel;
    private ArrayList<ProductReviewModel> mProductReviewList;
    private ProductReviewCommentModel productReviewCommentModel;
    private ArrayList<ProductReviewCommentModel> mProductReviewCommentList;
    private LayoutInflater layoutInflater;

    private ImageView ivReviewUserImg;
    private TextView tvReviewUserName;
    private TextView tvReviewCreatedDate;
    private RatingBar rbReviewRating;
    private HorizontalScrollView hsv;
    private LinearLayout llReviewImg;
    private TextView tvReviewContent;
    private LinearLayout llPostComment;
    private TextView tvPostCommentDate;
    private TextView tvPostCommentContent;
    private int viewType;

    public RecyclerViewProductDetailsAdapter(Context context, ArrayList<ProductReviewModel> productReviewList, ArrayList<ProductReviewCommentModel> productReviewCommentList){
        this.mContext = context;
        this.mProductReviewList = productReviewList;
        this.mProductReviewCommentList = productReviewCommentList;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_product_detail_review_card, viewGroup, false);
        switch (viewType) {
            case REVIEW:
                return new ViewHolderProductDetailReview(view);
            case QnA:
                return  new ViewHolderProductDetailQnA(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        bind(viewHolder, position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            for (Object payload : payloads) {
                boolean isExpand = (Boolean) payload;
                holder.itemView.findViewById(R.id.tv_post_comment_content).setVisibility(isExpand?View.VISIBLE:View.GONE);
                holder.itemView.findViewById(R.id.iv_post_comment_arrow).setBackgroundResource(isExpand? R.drawable.arrow_short_up : R.drawable.arrow_short_down);
            }
        }
    }

    private void bind(final RecyclerView.ViewHolder viewHolder, final int position) {
        viewHolder.setIsRecyclable(false);
        final ProductReviewModel productReviewModel = mProductReviewList.get(position);
        String linkedPostCommendProductNo = productReviewModel.getLinkedPostCommentProductList();
        switch (viewHolder.getItemViewType()) {
            case REVIEW:
                if(productReviewModel.getPostProductImg() != null) {
                    llReviewImg.setPadding(50,50,50,0);
                    for (int i = 0; i < productReviewModel.getPostProductImg().size(); i++) {
                        ImageView image = new ImageView(mContext);
                        image.setAdjustViewBounds(true);
                        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(1100, 900);
                        marginLayoutParams.setMargins(0, 0,20, 0);
                        image.setLayoutParams(marginLayoutParams);
                        llReviewImg.addView(image);
                        if(productReviewModel.getPostProductImg().get(i).matches("https:.*")){
                            Glide.with(mContext.getApplicationContext()).asBitmap().load(productReviewModel.getPostProductImg().get(i)).apply(new RequestOptions().override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(new ColorDrawable(Color.WHITE)).fitCenter()).into(image);
                        } else {
                            Glide.with(mContext.getApplicationContext()).asBitmap().load("https://contents.sixshop.com"+ productReviewModel.getPostProductImg().get(i)).apply(new RequestOptions().override(500,0).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(new ColorDrawable(Color.WHITE)).fitCenter()).into(image);
                        }
                    }
                }
                String rate = productReviewModel.getPostReviewRate().equals("null") ? "0" : productReviewModel.getPostReviewRate();
                tvReviewUserName.setText(productReviewModel.getPostProductWriter());
                tvReviewCreatedDate.setText(productReviewModel.getCreatedDate());
                tvReviewContent.setText(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? Html.fromHtml( productReviewModel.getPostProductContent(),Html.FROM_HTML_MODE_COMPACT) : Html.fromHtml( productReviewModel.getPostProductContent()));
                rbReviewRating.setRating(Integer.parseInt(rate));
                llPostComment.setVisibility(productReviewModel.getLinkedPostCommentProductList().equals("null") ? View.GONE : View.VISIBLE);
                tvPostCommentContent.setVisibility(productReviewModel.isExpanded() ? View.VISIBLE : View.GONE);
                for(ProductReviewCommentModel productReviewCommentModel : mProductReviewCommentList) {
                    String commentProductNo = String.valueOf(productReviewCommentModel.getCommentProductNo());
                    if(commentProductNo.matches(linkedPostCommendProductNo)) {
                        tvPostCommentContent.setText(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? Html.fromHtml(productReviewCommentModel.getCommentContent(),Html.FROM_HTML_MODE_COMPACT) : Html.fromHtml(productReviewCommentModel.getCommentContent()));
                        break;
                    }
                }
                llPostComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean expanded = productReviewModel.isExpanded();
                        productReviewModel.setExpanded(!expanded);
                        notifyItemChanged(position, productReviewModel.isExpanded());
                    }
                });
                break;
            case QnA:
                tvReviewUserName.setText(productReviewModel.getPostProductWriter());
                tvReviewCreatedDate.setText(productReviewModel.getCreatedDate());
                tvReviewContent.setText(productReviewModel.getPostProductContent());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mProductReviewList.size();
    }

    @Override
    public int getItemViewType(int position) {
        viewType = mProductReviewList.get(position).getPostQnaStatus() == "null"? 0 : 1;
        return viewType;
    }

    public class ViewHolderProductDetailReview extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolderProductDetailReview(@NonNull View itemView) {
            super(itemView);
            ivReviewUserImg = itemView.findViewById(R.id.iv_review_user_img);
            tvReviewUserName = itemView.findViewById(R.id.tv_review_user_name);
            tvReviewCreatedDate = itemView.findViewById(R.id.tv_review_created_date);
            rbReviewRating = itemView.findViewById(R.id.rb_review_rating);
            hsv = itemView.findViewById(R.id.hsv);
            llReviewImg = itemView.findViewById(R.id.ll_review_img);
            tvReviewContent = itemView.findViewById(R.id.tv_review_content);
            llPostComment = itemView.findViewById(R.id.ll_post_comment);
            tvPostCommentContent = itemView.findViewById(R.id.tv_post_comment_content);
            tvPostCommentDate = itemView.findViewById(R.id.tv_post_comment_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolderProductDetailQnA extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolderProductDetailQnA(@NonNull View itemView) {
            super(itemView);
            ivReviewUserImg = itemView.findViewById(R.id.iv_review_user_img);
            tvReviewUserName = itemView.findViewById(R.id.tv_review_user_name);
            tvReviewCreatedDate = itemView.findViewById(R.id.tv_review_created_date);
            rbReviewRating = itemView.findViewById(R.id.rb_review_rating);
            tvReviewContent = itemView.findViewById(R.id.tv_review_content);
            llPostComment = itemView.findViewById(R.id.ll_post_comment);
            tvPostCommentContent = itemView.findViewById(R.id.tv_post_comment_content);
            tvPostCommentDate = itemView.findViewById(R.id.tv_post_comment_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
