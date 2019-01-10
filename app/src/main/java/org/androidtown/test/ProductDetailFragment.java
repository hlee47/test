package org.androidtown.test;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;

import org.androidtown.test.adapters.ViewFragmentStatePagerAdapter;
import org.androidtown.test.adapters.ViewPagerAdapter;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.PaymentListModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductModel;
import org.androidtown.test.models.ProductOptionNameModel;
import org.androidtown.test.models.ProductOptionValueModel;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class ProductDetailFragment extends Fragment implements View.OnClickListener {
    private static int currentPage = 0;
    private static int NUM_PAGES = 1;
    private PageDataModel mPageDataModel;
    private ProductModel mProductModel;
    private ProductDetailModel mProductDetailModel;
    private ProductOptionValueModel mProductOptionValueModel;
    private ArrayList<String> productOptionValueNameList;
    private ArrayList<ProductOptionValueModel> mProductOptionValueList;
    private ProductOptionNameModel mProductOptionNameModel;
    private ArrayList<ProductOptionNameModel> mProductOptionNameList;
    private OnItemClickedListener mOnItemClickedListener;
    private ImageView itemImage;
    private TextView tvCompany;
    private TextView tvProductName;
    private LinearLayout llPrice;
    private TextView tvProductPrice;
    private LinearLayout llPriceDiscount;
    private TextView tvProductPriceDiscount;
    private View vProductLayout;
    private Button btnOrder;
    private Button btnCart;
    private com.travijuu.numberpicker.library.NumberPicker npProductQuantity;
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private static int productQuantity = 1;
    private static String productOptionPrice = "0";

    private ViewPager mViewPager;
    private CustomViewPager mViewFragmentPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewFragmentStatePagerAdapter mViewFragmentStatePagerAdapter;
    private TabLayout mTabLayout;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductModel = (ProductModel) getArguments().getSerializable("productModel");
        mProductDetailModel = (ProductDetailModel) getArguments().getSerializable("productDetailModel");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_detail, container, false);
        mViewPager = rootView.findViewById(R.id.vp_product_image);
        mTabLayout = rootView.findViewById(R.id.tl_product_image);
        mTabLayout.setTabRippleColor(null);
        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPagerAdapter = new ViewPagerAdapter(getContext(), NUM_PAGES, mProductModel);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewFragmentPager = rootView.findViewById(R.id.vp_product_details);
        mViewFragmentPager.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTabLayout = rootView.findViewById(R.id.tl_product_details);
        mTabLayout.setTabRippleColor(null);
        mTabLayout.setupWithViewPager(mViewFragmentPager, true);
        mViewFragmentStatePagerAdapter = new ViewFragmentStatePagerAdapter(getChildFragmentManager(),mTabLayout.getTabCount(), 1, mProductDetailModel);
        mViewFragmentPager.setAdapter(mViewFragmentStatePagerAdapter);
        mViewFragmentPager.setOffscreenPageLimit(2);

        itemImage = rootView.findViewById(R.id.image_view_product);
        tvCompany = rootView.findViewById(R.id.tv_company);
        tvProductName = rootView.findViewById(R.id.text_product_name);
        llPrice = rootView.findViewById(R.id.ll_price);
        tvProductPrice = rootView.findViewById(R.id.text_product_price);
        llPriceDiscount = rootView.findViewById(R.id.ll_price_discount);
        tvProductPriceDiscount = rootView.findViewById(R.id.text_product_price_discount);
        npProductQuantity = rootView.findViewById(R.id.np_product_quantity);
        npProductQuantity.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                productQuantity = npProductQuantity.getValue();
            }
        });
        btnOrder = rootView.findViewById(R.id.btn_buy_now);
        btnCart = rootView.findViewById(R.id.btn_put_cart);
        btnOrder.setOnClickListener(this);
        btnCart.setOnClickListener(this);

        tvProductName.setText(mProductModel.getProductName());
        tvProductPrice.setText(String.valueOf(mProductModel.getProductPrice()));
        tvProductPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvProductPriceDiscount.setText(String.valueOf(mProductModel.getProductDiscountPrice()));

        // Set Spinner Adapter and Listener
        spinner = rootView.findViewById(R.id.spinner_options);
        productOptionValueNameList = new ArrayList<>();
        productOptionValueNameList.add("구매옵션을 선택해 주세요");
        if(mProductDetailModel.getProductOptionNameList()!=null){
            for(int i = 0; i < mProductDetailModel.getProductOptionNameList().size(); i++) {
                mProductOptionNameModel = mProductDetailModel.getProductOptionNameList().get(i);
//            if(productOptionNameModel.getOptionNameOrderNo().matches(String.valueOf(i))) {
                for(int j = 0; j < mProductDetailModel.getProductOptionValueList().size(); j++) {
                    mProductOptionValueModel = mProductDetailModel.getProductOptionValueList().get(j);
//                    if(productOptionNameModel.getOptionNameNo() == productOptionValueModel.getOptionNameNo() && productOptionValueModel.getOptionValueNo().matches(String.valueOf(j))) {
                    productOptionValueNameList.add(mProductOptionValueModel.getOptionValue());
//                    }
                }
//            }
            }
        }
        spinner.setSelected(false);
        spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner_custom, productOptionValueNameList) {
            @Override
            public boolean isEnabled(int position) {
                if(position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position > 0) {
                    //Notify the selected item text
                    mProductModel.setOptionNo(mProductDetailModel.getProductOptionValueList().get(position - 1).getOptionNo());
                    System.out.println("fffffffffffff "+mProductDetailModel.getProductOptionValueList().get(position - 1).getOptionPrice());
                    mProductModel.setProductPrice(Integer.parseInt(mProductDetailModel.getProductOptionValueList().get(position - 1).getOptionPrice()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        mViewFragmentPager.setCurrentItem(0);
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if(mProductModel.getOptionNo() == null && mProductDetailModel.getProductOptionNameList() != null) {
            MainActivity.setAlert("알림", "구매옵션을 선택해 주세요", getContext());
            return;
        } else {
            mProductModel.setProductQuantity(productQuantity);
            mOnItemClickedListener.selectedProduct(mProductModel);
            MainActivity activity = (MainActivity) getActivity();
            activity.onClick(view);
        }
    }
}
