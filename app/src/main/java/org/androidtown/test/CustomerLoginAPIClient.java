package org.androidtown.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.androidtown.test.models.CartModel;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductModel;
import org.androidtown.test.models.ProductOptionNameModel;
import org.androidtown.test.models.ProductOptionValueModel;
import org.androidtown.test.models.ProductReviewCommentModel;
import org.androidtown.test.models.ProductReviewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerLoginAPIClient {
    private final static String LOGIN_EMAIL_URL = "https://www.sweelthy.com/_shop/customer/customerLogin";
    private final static String LOGIN_FACEBOOK_URL = "https://www.sweelthy.com/_shop/customer/socialLogin";
    private final static String SHOP_ORDER_LIST_URL = "https://www.sweelthy.com/_shop/order/getShopOrdersByCustomerNoAndPageAndNpp";
    private final static String SHOP_ORDER_LIST_DETAIL_URL = "https://www.sweelthy.com/_shop/order/getShopOrderByMemberNoAndOrderNo";
//    private final static String SHOP_ORDER_INFO_LIST_URL = "https://www.sweelthy.com/_shop/order/getShopOrderByMemberNoAndOrderNo";
    private final static String SHOP_PRODUCT_BY_CATEGORY_URL = "https://www.sweelthy.com/_shop/getShopProductsByCategoryListWithSearchKeyword";
    private final static String SHOP_DESIGN_AND_DEFAULT_URL = "https://www.sweelthy.com/product/getSiteDesignProductAndDefaultData";
    private final static String SHOP_PRODUCT_BY_PRODUCT_URL = "https://www.sweelthy.com/_shop/getShopProductByMemberNoandProductNo";
    private final static String ADD_TO_CART_URL = "https://www.sweelthy.com/_shop/purchase/addToCart";
    private final static String SHOP_CARTS_BY_CUSTOMER_URL = "https://www.sweelthy.com/_shop/cart/getShopCartsByMemberNoAndCustomerNo";
    private final static String DELETE_CART_URL = "https://www.sweelthy.com/_shop/cart/deleteCartByCartNo";

    private final static int LOGIN_EMAIL = 0;
    private final static int LOGIN_FACEBOOK = 1;
    private final static int SHOP_ORDER_LIST = 2;
    private final static int SHOP_ORDER_LIST_DETAIL = 3;
//    private final static int SHOP_ORDER_INFO_LIST = 4;
    private final static int SHOP_PRODUCT_BY_CATEGORY = 4;
    private final static int SHOP_DESIGN_AND_DEFAULT = 5;
    private final static int SHOP_PRODUCT_BY_PRODUCT = 6;
    private final static int ADD_TO_CART = 7;
    private final static int SHOP_CARTS_BY_CUSTOMER = 8;
    private final static int DELETE_CART = 9;

    private final static int PROGRESS_DIALOG_OPEN = 0;
    private final static int PROGRESS_DIALOG_CLOSE = 1;

    private static ProgressDialog progressDialog;
    private static StringRequest stringRequestOrderList;
    private StringRequest stringRequestOrderListDetail;
//    private static StringRequest stringRequestOrderInfoData;
    private static StringRequest stringRequestDesignAndDefault;
    private static StringRequest stringRequestProductByCategory;
    private static StringRequest stringRequestProductByProduct;
    private static StringRequest stringRequestAddToCart;
    private static StringRequest stringRequestCartsByCustomer;
    private static StringRequest stringRequestEmail;
    private static StringRequest stringRequestFacebook;
    private static StringRequest stringRequestDeleteCart;
    private int responseCode;
    private DefaultRetryPolicy retryPolicy;

    private String stringRequest;
    private Context mContext;

    private final String memberNo = "19604";
    private static String customerPassword = "-1";
    private static String buyNowCartNo;
    private String buyKakaoCartNo = "";
    private String signupType = "both";
    private String kakaoId = "";
    private String kakaoReturnUrl = "/";
    private String kakaoConnect = "N";
    private String recaptchaVerified = "0";
    private static String customerEmail = "-1";
    private final String siteNo = "19604";
    private String siteKey = "";
    private final String siteLink = "sweelthy";
    private final String pageNo = "159719";
    private static String shopCustomerNo = "0";
    private static String customerNo = "-1";
    private String orderNo = "-1";
    private int count = -1;
    private String displayType ="display";
    private String pageLink = "home";
    private String itemType = "productList";
    private String shopCategoryNoList;
    private String page = "1";
    private String productNo;
    private String cartQuantity;
    private String cartType;
    private String cartNo;
    private String cartOptionNo;

    private static ArrayList<String> orderNumberList = new ArrayList<>();
    private static CustomerInfoModel mCustomerInfoModel = new CustomerInfoModel();
    private static CustomerOrderModel mCustomerOrderModel = new CustomerOrderModel();
    private static ArrayList<CustomerOrderModel> mCustomerOrderList = new ArrayList<>();
    private static CustomerOrderInfoModel mCustomerOrderInfoModel = new CustomerOrderInfoModel();
    private static ArrayList<CustomerOrderInfoModel> mCustomerOrderInfoList = new ArrayList<>();
    private static ProductModel mProductModel = new ProductModel();
    private static ArrayList<ProductModel> mProductList = new ArrayList<>();
    private static ProductDetailModel mProductDetailModel = new ProductDetailModel();
    private static PageDataModel mPageDataModel = new PageDataModel();
    private static ArrayList<PageDataModel> mPageDataList = new ArrayList<>();
    private static ProductReviewModel mProductReviewModel = new ProductReviewModel();
    private static ArrayList<ProductReviewModel> mProductReviewList = new ArrayList<>();
    private static ProductReviewCommentModel mProductReviewCommentModel = new ProductReviewCommentModel();
    private static ArrayList<ProductReviewCommentModel> mProductReviewCommentList = new ArrayList<>();
    private static CartModel mCartModel = new CartModel();
    private static ArrayList<CartModel> mCartList = new ArrayList<>();
    private static ProductOptionNameModel mProductOptionNameModel = new ProductOptionNameModel();
    private static ArrayList<ProductOptionNameModel> mProductOptionNameList = new ArrayList<>();
    private static ProductOptionValueModel mProductOptionValueModel = new ProductOptionValueModel();
    private static ArrayList<ProductOptionValueModel> mProductOptionValueList = new ArrayList<>();

    private OnResponseCallback mOnResponseCallback;
    private OnItemClickedListener mOnItemClickedListener;

    public CustomerLoginAPIClient(String requestType, String s1, String s2, String s3, ArrayList<String> stringArrayList, final Context context, OnItemClickedListener listener, OnResponseCallback callback) {
        retryPolicy = new DefaultRetryPolicy(500, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.mContext = context;
        this.mOnResponseCallback = callback;
        this.mOnItemClickedListener = listener;

        switch (getRequestType(requestType)) {
            case LOGIN_EMAIL:
                this.customerEmail = s1;
                this.customerPassword = s2;
                setStringRequestEmail();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestEmail);
            case LOGIN_FACEBOOK:
                this.customerEmail = s1;
                this.customerPassword = s2;
                progressDialog(mContext, PROGRESS_DIALOG_OPEN);
                setStringRequestFacebook();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestFacebook);
            case SHOP_ORDER_LIST:
                setStringRequestOrderList();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestOrderList);
                break;
            case SHOP_ORDER_LIST_DETAIL:
                this.customerNo = s1;
                this.orderNumberList = stringArrayList;
                this.count = stringArrayList.size()-1;
                setStringRequestOrderListDetail();
                break;
            case SHOP_PRODUCT_BY_CATEGORY:
                this.shopCategoryNoList = s1;
                progressDialog(mContext, PROGRESS_DIALOG_OPEN);
                setStringRequestProductByCategory();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestProductByCategory);
                break;
            case SHOP_DESIGN_AND_DEFAULT:
                setStringRequestDesignAndDefault();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestDesignAndDefault);
                break;
            case SHOP_PRODUCT_BY_PRODUCT:
                this.productNo = s1;
                progressDialog(mContext, PROGRESS_DIALOG_OPEN);
                setStringRequestProductByProduct();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestProductByProduct);
                break;
            case ADD_TO_CART:
                this.productNo = s1;
                this.cartQuantity = s2;
                this.cartType = s3;
                this.cartOptionNo = stringArrayList.get(0) != null ? stringArrayList.get(0): "0";
                System.out.println("ssssssssss "+stringArrayList +" SSSSSSSSSS "+cartOptionNo);
                progressDialog(mContext, PROGRESS_DIALOG_OPEN);
                setStringRequestAddToCart();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestAddToCart);
                break;
            case SHOP_CARTS_BY_CUSTOMER:
                this.buyNowCartNo = s1;
                this.buyKakaoCartNo = s2;
                if(!progressDialog.isShowing()) {
                    progressDialog(mContext, PROGRESS_DIALOG_OPEN);
                }
                setStringRequestCartsByCustomer();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestCartsByCustomer);
                break;
            case DELETE_CART:
                this.cartNo = s1;
                setStringRequestDeleteCart();
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestDeleteCart);
            default:
                break;
        }
    }

    private int getRequestType(String requestType) {
        switch (requestType) {
            case "LOGIN_EMAIL":
                return LOGIN_EMAIL;
            case "LOGIN_FACEBOOK":
                return LOGIN_FACEBOOK;
            case "SHOP_ORDER_LIST":
                return SHOP_ORDER_LIST;
            case "SHOP_ORDER_LIST_DETAIL":
                return SHOP_ORDER_LIST_DETAIL;
            case "SHOP_PRODUCT_BY_CATEGORY":
                return SHOP_PRODUCT_BY_CATEGORY;
            case "SHOP_PRODUCT_BY_PRODUCT":
                return SHOP_PRODUCT_BY_PRODUCT;
            case "SHOP_DESIGN_AND_DEFAULT":
                return SHOP_DESIGN_AND_DEFAULT;
            case "ADD_TO_CART":
                return ADD_TO_CART;
            case "SHOP_CARTS_BY_CUSTOMER":
                return SHOP_CARTS_BY_CUSTOMER;
            case "DELETE_CART":
                return DELETE_CART;
            default:
                return -1;
        }
    }

//    public CustomerLoginAPIClient(final Context context, OnItemClickedListener onItemClickedListener) {
//        this.mContext = context;
//        this.mOnItemClickedListener = onItemClickedListener;
//        setStringRequestDesignAndDefault();
//    }

//    public CustomerLoginAPIClient(String shopCategoryNoList, final Context context, OnItemClickedListener onItemClickedListener) {
//        this.shopCategoryNoList = shopCategoryNoList;
//        this.mContext = context;
//        this.mOnItemClickedListener = onItemClickedListener;
//        setStringRequestProductByCategory();
//    }

//    public CustomerLoginAPIClient(String productNo, final Context context, OnResponseCallback onResponseCallback) {
//        this.productNo = productNo;
//        this.mContext = context;
//        this.mOnResponseCallback = onResponseCallback;
//        progressDialog(this.mContext, PROGRESS_DIALOG_OPEN);
//        setStringRequestProductByProduct();
//    }

//    public CustomerLoginAPIClient(String customerNo, ArrayList<String> shopOrderNumberList, final Context context, OnResponseCallback onResponseCallback) {
//        this.customerNo = customerNo;
//        this.orderNumberList = shopOrderNumberList;
//        this.count = shopOrderNumberList.size()-1;
//        this.mOnResponseCallback = onResponseCallback;
//        this.mContext = context;
//        this.mCustomerOrderList = new ArrayList<>();
//        this.mCustomerOrderInfoList = new ArrayList<>();
//        progressDialog(this.mContext, PROGRESS_DIALOG_OPEN);
//        setStringRequestOrderListDetail();
//    }

//    public CustomerLoginAPIClient(String buyNowCartNo, String buyKakaoCartNo, final Context context, OnItemClickedListener onItemClickedListener) {
//        this.buyNowCartNo = buyNowCartNo;
//        this.buyKakaoCartNo = buyKakaoCartNo;
//        this.mContext = context;
//        this.mOnItemClickedListener = onItemClickedListener;
//        setStringRequestCartsByCustomer();
//    }

//    public CustomerLoginAPIClient(String productNo, String cartQuantity, String cartType, final Context context, OnItemClickedListener onItemClickedListener) {
//        this.productNo = productNo;
//        this.cartQuantity = cartQuantity;
//        this.cartType = cartType==null ? cartType : "";
//        this.mContext = context;
//        this.mOnItemClickedListener = onItemClickedListener;
//        setStringRequestAddToCart();
//    }

//    public CustomerLoginAPIClient(String customerNo, String orderNo, final Context context, OnResponseCallback onResponseCallback) {
//        this.customerNo = customerNo;
//        this.orderNo = orderNo;
//        this.mOnResponseCallback = onResponseCallback;
//        this.mContext = context;
//        this.mCustomerOrderInfoList = new ArrayList<>();
//        progressDialog(this.mContext, PROGRESS_DIALOG_OPEN);
//        setStringRequestOrderInfoData();
//    }

//    public CustomerLoginAPIClient(String customerEmail, String customerPassword, String stringRequest, Context context, OnResponseCallback onResponseCallback) {
//        this.mCustomerInfoModel = new CustomerInfoModel();
//        this.mCustomerOrderModel = new CustomerOrderModel();
//        this.mCustomerOrderList = new ArrayList<>();
//        this.mCustomerOrderInfoModel = new CustomerOrderInfoModel();
//        this.mCustomerOrderInfoList = new ArrayList<>();
//
//        this.customerEmail = customerEmail;
//        this.customerPassword = customerPassword;
//        this.stringRequest = stringRequest;
//        this.mOnResponseCallback = onResponseCallback;
//        this.mContext = context;
//
//        stringRequestOrderList = new StringRequest(Method.POST, SHOP_ORDER_LIST_URL, new Listener<String>() {
//            @Override
//            public void onResponse(String response) { onResponseListener(response); }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                MainActivity.addSessionCookie(params);
//                return params;
//            }
//
//            @Override
//            public Map<String,String> getParams(){ return getParamsListener(SHOP_ORDER_LIST); }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                responseCode = response.statusCode;
//                MainActivity.checkSessionCookie(response.headers);
//                return super.parseNetworkResponse(response);
//            }
//        };
//
//        stringRequestEmail = new StringRequest(Method.POST, LOGIN_EMAIL_URL, new Listener<String>() {
//            @Override
//            public void onResponse(String response) { onResponseListener(response); }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                MainActivity.addSessionCookie(params);
//                return params;
//            }
//
//            @Override
//            public Map<String,String> getParams(){
//                return getParamsListener(LOGIN_EMAIL);
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                responseCode = response.statusCode;
//                MainActivity.checkSessionCookie(response.headers);
//                return super.parseNetworkResponse(response);
//            }
//
//        };
//
//        stringRequestFacebook = new StringRequest(Method.POST, LOGIN_FACEBOOK_URL, new Listener<String>() {
//            @Override
//            public void onResponse(String response) { onResponseListener(response); }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                MainActivity.addSessionCookie(params);
//                return params;
//            }
//
//            @Override
//            public Map<String,String> getParams(){
//                return getParamsListener(LOGIN_FACEBOOK);
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                responseCode = response.statusCode;
//                MainActivity.checkSessionCookie(response.headers);
//                return super.parseNetworkResponse(response);
//            }
//        };
//
//        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(500, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequestEmail.setRetryPolicy(retryPolicy);
//        stringRequestFacebook.setRetryPolicy(retryPolicy);
//        stringRequestOrderList.setRetryPolicy(retryPolicy);
//        if(stringRequest.matches("LOGIN_REQUEST_FACEBOOK")) {
//            SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestFacebook);
//        } else if(stringRequest.matches("LOGIN_REQUEST_EMAIL")) {
//            SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestEmail);
//        } else if(stringRequest.matches("SHOP_ORDER_LIST_URL")) {
//            SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestOrderList);
//        }
//       progressDialog(this.mContext, PROGRESS_DIALOG_OPEN);
//    }

    private void setStringRequestEmail() {
        stringRequestEmail = new StringRequest(Method.POST, LOGIN_EMAIL_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) { onResponseListener(response); }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(LOGIN_EMAIL);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestEmail.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestFacebook() {
        stringRequestFacebook = new StringRequest(Method.POST, LOGIN_FACEBOOK_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) { onResponseListener(response); }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(LOGIN_FACEBOOK);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestFacebook.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestOrderList() {
        stringRequestOrderList = new StringRequest(Method.POST, SHOP_ORDER_LIST_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) { onResponseListener(response); }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){ return getParamsListener(SHOP_ORDER_LIST); }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestOrderList.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestOrderListDetail() {
        stringRequestOrderListDetail = new StringRequest(Method.POST, SHOP_ORDER_LIST_DETAIL_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                count--;
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONObject shopOrderObject = responseObject.getJSONObject("shopOrder");
                    mCustomerOrderModel = parseCustomerOrderJSON(shopOrderObject);
                    String shopOrderOptionsString = shopOrderObject.getString("orderOptions");
                    JSONObject shopOrderOptionsObject = new JSONObject(shopOrderOptionsString);
                    if(shopOrderOptionsObject.has("rewardPoint")) {
                        mCustomerOrderModel.setRewardPoint(String.valueOf(shopOrderOptionsObject.getInt("rewardPoint")));
                    } else { mCustomerOrderModel.setRewardPoint("0"); }
                    if(shopOrderOptionsObject.has("orderUsePoint")) {
                        mCustomerOrderModel.setOrderUsePoint(String.valueOf(shopOrderOptionsObject.getInt("orderUsePoint")));
                    } else { mCustomerOrderModel.setOrderUsePoint("0"); }
                    if(shopOrderOptionsObject.has("totalOrderProductPrice")) {
                        mCustomerOrderModel.setTotalOrderProductPrice(String.valueOf(shopOrderOptionsObject.getInt("totalOrderProductPrice"))); }

                    JSONArray shopOrderInfoArrayObject = responseObject.getJSONArray("shopOrderInfoList");
                    mCustomerOrderInfoList.clear();
                    for(int i = 0; i < shopOrderInfoArrayObject.length(); i++) {
                        JSONObject shopOrderInfoObject = shopOrderInfoArrayObject.getJSONObject(i);
                        mCustomerOrderInfoModel = parseCustomerOrderInfoJSON(shopOrderInfoObject);
                        mCustomerOrderInfoList.add(mCustomerOrderInfoModel);
                    }
                    mCustomerOrderModel.setOrderInfoList(mCustomerOrderInfoList);
                    mCustomerOrderList.add(mCustomerOrderModel);
                    setStringRequestOrderListDetail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(SHOP_ORDER_LIST_DETAIL); }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        if(-1 < count) {
            SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestOrderListDetail);
        } else {
            progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
            mOnResponseCallback.onSuccessOrderListPull(mCustomerOrderList);
            mCustomerOrderList.clear();
        }
    }

    private void setStringRequestDesignAndDefault() {
        stringRequestDesignAndDefault = new StringRequest(Method.POST, SHOP_DESIGN_AND_DEFAULT_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                mPageDataList = new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray shopPageListArrayObject = responseObject.getJSONArray("pageList");
                    for(int i = 0; i < shopPageListArrayObject.length(); i++) {
                        ArrayList<String> imgURLList= new ArrayList<>();
                        JSONObject shopPageInfo = shopPageListArrayObject.getJSONObject(i);
                        String pageContentList = shopPageInfo.getString("pageContents");
                        JSONObject pageContentListJSONObject = new JSONObject(pageContentList);
                        String common = pageContentListJSONObject.getString("common");
                        JSONObject commonJSONObject = new JSONObject(common);
                        JSONArray heroContents = commonJSONObject.getJSONArray("heroContents");
                        for(int j = 0; j < heroContents.length(); j++) {
                            JSONObject heroContent = heroContents.getJSONObject(j);
                            String imgURL = heroContent.getString("src");
                            imgURLList.add(imgURL);
                        }
                        mPageDataModel = parsePageDataJSON(shopPageInfo);
                        mPageDataModel.setPageImageURLList(imgURLList);
                        mPageDataList.add(mPageDataModel);
                    }
                    progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                    mOnItemClickedListener.onSuccessPageDataList(mPageDataList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(SHOP_DESIGN_AND_DEFAULT);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestDesignAndDefault.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestProductByCategory() {
        stringRequestProductByCategory = new StringRequest(Method.POST, SHOP_PRODUCT_BY_CATEGORY_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProductList = new ArrayList<>();
                ArrayList<String> imageURLList= new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray shopProductListArrayObject = responseObject.getJSONArray("shopProductList");
                    for(int i = 0; i < shopProductListArrayObject.length(); i++) {
                        JSONObject shopProductInfo = shopProductListArrayObject.getJSONObject(i);
                        String imageURL = shopProductInfo.getString("productThumbnail");
                        mProductModel = parseProductJSON(shopProductInfo);
                        mProductModel.setProductImgs("https://contents.sixshop.com"+imageURL);
                        mProductList.add(mProductModel);
                        imageURLList.add("https://contents.sixshop.com"+imageURL);
                    }
                    mOnItemClickedListener.onSuccessProductListPull(mProductList);
                    mOnItemClickedListener.onSuccessProductImgList(imageURLList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(SHOP_PRODUCT_BY_CATEGORY);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestProductByCategory.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestProductByProduct() {
        stringRequestProductByProduct = new StringRequest(Method.POST, SHOP_PRODUCT_BY_PRODUCT_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProductReviewList = new ArrayList<>();
                mProductReviewCommentList = new ArrayList<>();
                ArrayList<String> productDescriptionImgURLList = new ArrayList<>();
                ArrayList<String> postProductImgURLList = new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONObject shopProductJSONObject = responseObject.getJSONObject("shopProduct");
                    mProductDetailModel = parseProductDetailJSON(shopProductJSONObject);
                    Pattern p = Pattern.compile("(https:\\/\\/(.*?)\\/uploadedFiles\\/19604\\/product\\/image_(.*?).jpeg)");
                    Matcher m = p.matcher(mProductDetailModel.getProductDescription());
                    while (m.find()) {
                        String src = m.group(0);
                        productDescriptionImgURLList.add(src); }
                    mProductDetailModel.setProductDescriptionImgURLList(productDescriptionImgURLList);


                    if(responseObject.has("shopProductOptionNameList")){
                        JSONArray shopProductOptionNameListJSONArray = responseObject.getJSONArray("shopProductOptionNameList");
                        mProductOptionNameList.clear();
                        for(int i = 0; i < shopProductOptionNameListJSONArray.length(); i++) {
                            JSONObject shopProductOptionNameModelJSONObject = shopProductOptionNameListJSONArray.getJSONObject(i);
                            mProductOptionNameModel = parseProductOptionNameJSON(shopProductOptionNameModelJSONObject);
                            mProductOptionNameList.add(mProductOptionNameModel);
                        }
                        mProductDetailModel.setProductOptionNameList(mProductOptionNameList);
                    }

                    if(responseObject.has("shopProductOptionValueList")){
                        JSONArray shopProductOptionValueListJSONArray = responseObject.getJSONArray("shopProductOptionValueList");
                        JSONArray shopProductOptionListJSONArray = responseObject.getJSONArray("shopProductOptionList");
                        mProductOptionValueList.clear();
                        for(int i = 0; i < shopProductOptionValueListJSONArray.length(); i++) {
                            JSONObject shopProductOptionValueModelJSONObject = shopProductOptionValueListJSONArray.getJSONObject(i);
                            mProductOptionValueModel = parseProductOptionValueJSON(shopProductOptionValueModelJSONObject);
                            for(int j = 0; j <shopProductOptionListJSONArray.length(); j++) {
                                JSONObject shopProductOptionModelJSONObject = shopProductOptionListJSONArray.getJSONObject(j);
                                if(mProductOptionValueModel.getOptionValueNo().matches(shopProductOptionModelJSONObject.getString("optionValueNo1"))) {
                                    mProductOptionValueModel.setOptionNo(shopProductOptionModelJSONObject.getString("optionNo"));
                                    mProductOptionValueModel.setOptionPrice(String.valueOf(shopProductOptionModelJSONObject.getInt("optionPrice")));
                                }
                            }
                            mProductOptionValueList.add(mProductOptionValueModel);
                        }
                        mProductDetailModel.setProductOptionValueList(mProductOptionValueList);
                    }

                    JSONArray postProductListJSONArray = responseObject.getJSONArray("postProductList");
                    for(int i = 0; i < postProductListJSONArray.length(); i++) {
                        JSONObject postProductModelJSONObject = postProductListJSONArray.getJSONObject(i);
                        mProductReviewModel = parseProductReviewJSON(postProductModelJSONObject);
                        String postProductImgURLString = postProductModelJSONObject.getString("postProductImg");
                        Pattern p2 = Pattern.compile("(?<=\"src\":\").*?((.*?).jp(.*?)g)");
                        Matcher m2 = p2.matcher(postProductImgURLString);
                        while(m2.find()) {
                            String src = m2.group();
                            postProductImgURLList.add(src); }
                        mProductReviewModel.setPostProductImg(postProductImgURLList);
                        postProductImgURLList.clear();
                        mProductReviewList.add(mProductReviewModel); }
                    mProductDetailModel.setProductReviewList(mProductReviewList);

                    JSONArray postCommentProductListJSONArray = responseObject.getJSONArray("postCommentProductList");
                    for(int i = 0; i < postCommentProductListJSONArray.length(); i++) {
                        JSONObject postProductCommentModelJSONObject = postCommentProductListJSONArray.getJSONObject(i);
                        mProductReviewCommentModel = parseProductReviewCommentJSON(postProductCommentModelJSONObject);
                        mProductReviewCommentList.add(mProductReviewCommentModel); }
                    mProductDetailModel.setProductReviewCommentList(mProductReviewCommentList);
                    mOnResponseCallback.onSuccessProductDetailPull(mProductDetailModel);
                    progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(SHOP_PRODUCT_BY_PRODUCT);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestProductByProduct.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestCartsByCustomer() {
        stringRequestCartsByCustomer = new StringRequest(Method.POST, SHOP_CARTS_BY_CUSTOMER_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                mCartList = new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    if(responseObject.has("buyNowShopCartList")){
                        JSONArray buyNowCartListJSONArray = responseObject.getJSONArray("buyNowShopCartList");
                        if(!buyNowCartListJSONArray.isNull(0)) {
                            for(int i=0; i<buyNowCartListJSONArray.length(); i++) {
                                mCartModel = parseCartJSON(buyNowCartListJSONArray.getJSONObject(i));
                                mCartList.add(mCartModel);
                            }
                        }
                    }
                    if(mCartList.isEmpty()){
                        JSONArray cartListJSONArray = responseObject.getJSONArray("shopCartList");
                        for(int i=0; i<cartListJSONArray.length(); i++) {
                            mCartModel = parseCartJSON(cartListJSONArray.getJSONObject(i));
                            mCartList.add(mCartModel);
                        }
                    }
                    JSONArray productListJSONArray = responseObject.getJSONArray("shopProductList");
                    for(int i=0; i<mCartList.size(); i++) {
                        mCartModel = mCartList.get(i);
                        for(int j=0; j<productListJSONArray.length(); j++) {
                            mProductModel = parseProductJSON(productListJSONArray.getJSONObject(j));
                            if (mProductModel.getProductNo().equals(String.valueOf(mCartModel.getProductNo()))) {
                                mCartModel.setProductModel(mProductModel);
                            }
                        }
                    }
//                    for(int i=0; i<productListJSONArray.length(); i++) {
//                        mProductModel = parseProductJSON(productListJSONArray.getJSONObject(i));
//                        for(int j=0; j<mCartList.size(); j++) {
//                            if (String.valueOf(mCartList.get(j).getProductNo()).equals(mProductModel.getProductNo())) {
//                                mCartList.get(j).setProductModel(mProductModel);
//                            }
//                        }
//                    }
                    mOnItemClickedListener.onSuccessCartList(mCartList);
                    progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(SHOP_CARTS_BY_CUSTOMER);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestCartsByCustomer.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestAddToCart() {
        stringRequestAddToCart = new StringRequest(Method.POST, ADD_TO_CART_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    String buyNowCartNo = cartType.isEmpty() ? "" : responseObject.getString("buyNowCartNo");
                    mOnItemClickedListener.onSuccessBuyNowCartNo(buyNowCartNo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(ADD_TO_CART);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestAddToCart.setRetryPolicy(retryPolicy);
    }

    private void setStringRequestDeleteCart() {
        stringRequestDeleteCart = new StringRequest(Method.POST, DELETE_CART_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    String result = responseObject.getString("RESULT");
                    if(result.matches("OK")) {
                        mOnItemClickedListener.onSuccessBuyNowCartNo("");
                    } else {
                        Log.i("RESULT", "onResponse: " + result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return getHeaderListener();
            }

            @Override
            public Map<String,String> getParams(){
                return getParamsListener(DELETE_CART);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                MainActivity.checkSessionCookie(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        stringRequestDeleteCart.setRetryPolicy(retryPolicy);
    }

//    private void setStringRequestOrderInfoData() {
//        stringRequestOrderInfoData = new StringRequest(Method.POST, SHOP_ORDER_INFO_LIST_URL, new Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject responseObject = new JSONObject(response);
//                    JSONArray shopOrderInfoArrayObject = responseObject.getJSONArray("shopOrderInfoList");
//                    for(int i = 0; i < shopOrderInfoArrayObject.length(); i++) {
//                        JSONObject shopOrderInfoObject = shopOrderInfoArrayObject.getJSONObject(i);
//                        mCustomerOrderInfoModel = parseCustomerOrderInfoJSON(shopOrderInfoObject);
//                        mCustomerOrderInfoList.add(mCustomerOrderInfoModel);
//                    }
//                    progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
//                    mOnResponseCallback.onSuccessOrderInfoPull(mCustomerOrderInfoList);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { onErrorResponseListener(error); }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                MainActivity.addSessionCookie(params);
//                return params;
//            }
//
//            @Override
//            public Map<String,String> getParams(){
//                return getParamsListener(SHOP_ORDER_INFO_LIST);
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                responseCode = response.statusCode;
//                MainActivity.checkSessionCookie(response.headers);
//                return super.parseNetworkResponse(response);
//            }
//        };
//        SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestOrderInfoData);
////        Volley.newRequestQueue(mContext).add(stringRequestOrderInfoData);
//    }

    private void onResponseListener(String response) {
        try {
            JSONObject responseObject = new JSONObject(response);
            String result = responseObject.getString("RESULT");
            if(result.matches("INVALID_ID_ERROR")) {
                progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                MainActivity.setAlert("알림","존재하지않는 회원입니다.", mContext);
            } else if(result.matches("INVALID_PASSWORD_ERROR")) {
                progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                MainActivity.setAlert("알림", "비밀번호 오류입니다.", mContext);
            } else if(result.matches("PERMISSON_ERROR")) {
                progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
                MainActivity.setAlert("알림", "잘못된 접근입니다.", mContext);
            } else if(result.matches("OK") && customerNo.equals("-1")) {
                JSONObject shopCustomerObject = responseObject.getJSONObject("shopCustomer");
                mCustomerInfoModel = parseCustomerInfoJSON(shopCustomerObject);
                SingletonRequest.getInstance(mContext).addToRequestQueue(stringRequestOrderList);
                customerNo = mCustomerInfoModel.getCustomerNo();
                shopCustomerNo = mCustomerInfoModel.getCustomerNo();
            } else if(result.matches("OK") && !customerNo.equals("-1")) {
                JSONArray shopOrderArrayObject = responseObject.getJSONArray("shopOrderList");
                ArrayList<String> shopOrderNumberList = new ArrayList<>();
                for (int i = 0; i < shopOrderArrayObject.length(); i++) {
                    JSONObject shopOrderObject = shopOrderArrayObject.getJSONObject(i);
                    mCustomerOrderModel = parseCustomerOrderJSON(shopOrderObject);
                    shopOrderNumberList.add(mCustomerOrderModel.getOrderNo());
                }
                mCustomerInfoModel.setShopOrderNumberList(shopOrderNumberList);
                mOnResponseCallback.onSuccessLogin(mCustomerInfoModel);
                mOnResponseCallback.onResponseLogin(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onErrorResponseListener(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
            MainActivity.setAlert("네트워크 에러", "네트워크 연결 상태를 확인하여 주십세요.", mContext);
        } else if (error instanceof AuthFailureError) {
            //TODO
        } else if (error instanceof ServerError) {
            progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
            MainActivity.setAlert("네트워크 에러", "서버 장에를 격고 있습니다.", mContext);
        } else if (error instanceof NetworkError) {
            progressDialog(mContext, PROGRESS_DIALOG_CLOSE);
            MainActivity.setAlert("네트워크 에러", "네트워크 연결 상태를 확인하여 주십세요.", mContext);
        } else if (error instanceof ParseError) {
            //TODO
        }
    }

    private Map<String, String> getHeaderListener() {
        Map<String, String> params = new HashMap<>();
        MainActivity.addSessionCookie(params);
        return params;
    }

    private Map<String, String> getParamsListener(int i){
        Map<String, String> params = new HashMap<>();
        if(i == LOGIN_FACEBOOK) {
            params.put("name", "Han-Keun Lee");
            params.put("email", "saygeun_@hotmail.com");
            params.put("id", "10154580383868803");
            params.put("type", "facebook");
        } else if(i == LOGIN_EMAIL) {
            params.put("customerPassword", customerPassword);
//            params.put("buyNowCartNo", buyNowCartNo);
            params.put("signupType", signupType);
            params.put("kakaoId", kakaoId);
            params.put("kakaoReturnUrl", kakaoReturnUrl);
            params.put("kakaoConnect", kakaoConnect);
            params.put("recaptchaVerified", recaptchaVerified);
            params.put("customerEmail", customerEmail);
        } else if(i == SHOP_ORDER_LIST) {
            params.put("customerNo", customerNo);
            params.put("displayPage", "myPage");
            params.put("page", page);
            params.put("npp", "5");
        } else if(i == SHOP_ORDER_LIST_DETAIL) {
            params.put("orderNo", orderNumberList.get(count));
//        } else if(i == SHOP_ORDER_INFO_LIST) {
//            params.put("orderNo", orderNo);
        } else if(i == SHOP_PRODUCT_BY_CATEGORY) {
            params.put("shopCategoryNoList", "allFilter, 106216, 106354, 106355, 106356, 106468, 106223, 116059, 118199");
            params.put("itemType", "productList");
            params.put("page", "1");
            params.put("npp", "50");
        } else if(i == SHOP_DESIGN_AND_DEFAULT) {
            params.put("pageLink", pageLink);
            params.put("displayType", displayType);
            params.put("pageNo", pageNo);
        } else if(i == SHOP_PRODUCT_BY_PRODUCT) {
            params.put("productNo", productNo);
        } else if(i == ADD_TO_CART) {
            params.put("productNo", productNo);
            params.put("cartType", cartType);
            params.put("cartQuantity", cartQuantity);
            params.put("cartOptionNo", cartOptionNo);
            params.put("cartAdditionalOptionNo", "-1");
            params.put("cartOptions", "{}");
        } else if(i == SHOP_CARTS_BY_CUSTOMER) {
            params.put("customerNo", shopCustomerNo);
            params.put("buyNowCartNo", buyNowCartNo);
            params.put("buyKakaoCartNo", buyKakaoCartNo);
        } else if(i == DELETE_CART) {
            params.put("cartNo", cartNo);
            params.put("customerNo", shopCustomerNo);
        }
        params.put("memberNo", memberNo);
        params.put("siteNo", siteNo);
//        params.put("siteKey", siteKey);
        params.put("siteLink", siteLink);
        params.put("shopCustomerNo", shopCustomerNo);

        return params;
    }

    private CartModel parseCartJSON(JSONObject json) throws JSONException {
        CartModel cartModel = new CartModel();
        cartModel.setCartNo(json.getInt("cartNo"));
        cartModel.setMemberNo(json.getInt("memberNo"));
        cartModel.setProductNo(json.getInt("productNo"));
        cartModel.setCustomerNo(json.getInt("customerNo"));
        cartModel.setCartQuantity(json.getInt("cartQuantity"));
//        cartModel.setCartOptionNo(json.getInt("cartOptionNo"));
        cartModel.setCartOptions(json.getString("cartOptions"));
        cartModel.setCartType(json.getString("cartType"));
//        cartModel.setPayedPrice(json.getString("payedPrice"));
        cartModel.setCartApplyCouponDiscount(json.getString("cartApplyCouponDiscount"));
        cartModel.setCartApplyPromotionCodeDiscount(json.getString("cartApplyPromotionCodeDiscount"));
        cartModel.setUpdatedDate(json.getString("updatedDate"));
        cartModel.setCreatedDate(json.getString("createdDate"));
        return cartModel;
    }

    private ProductReviewCommentModel parseProductReviewCommentJSON(JSONObject json) throws JSONException {
        ProductReviewCommentModel productReviewCommentModel = new ProductReviewCommentModel();
        productReviewCommentModel.setCommentProductNo(json.getInt("commentProductNo"));
        productReviewCommentModel.setCustomerNo(json.getInt("customerNo"));
        productReviewCommentModel.setPostProductNo(json.getInt("postProductNo"));
        productReviewCommentModel.setProductNo(json.getInt("productNo"));
        productReviewCommentModel.setCommentWriter(json.getString("commentWriter"));
        productReviewCommentModel.setCommentContent(json.getString("commentContent"));
        productReviewCommentModel.setCreatedDate(json.getString("createdDate"));
        productReviewCommentModel.setUpdatedDate(json.getString("updatedDate"));
        return productReviewCommentModel;
    }

    private ProductReviewModel parseProductReviewJSON(JSONObject json) throws JSONException {
        ProductReviewModel productReviewModel = new ProductReviewModel();
        productReviewModel.setPostProductNo(json.getInt("postProductNo"));
//        productReviewModel.setCustomerNo(json.getInt("customerNo"));
        productReviewModel.setBoardProductNo(json.getInt("boardProductNo"));
        productReviewModel.setProductNo(json.getInt("productNo"));
        productReviewModel.setPostProductOrderNo(json.getInt("postProductOrderNo"));
        productReviewModel.setPostProductIdx(json.getInt("postProductIdx"));
        productReviewModel.setRecentLinkedProductName(json.getString("recentLinkedProductName"));
//        productReviewModel.setPostProductImg(json.getString("postProductImg"));
        productReviewModel.setPostProductContent(json.getString("postProductContent"));
        productReviewModel.setPostProductWriter(json.getString("postProductWriter"));
        productReviewModel.setProductName(json.getString("productName"));
        productReviewModel.setPostQnaStatus(json.getString("postQnaStatus"));
        productReviewModel.setUpdatedDate(json.getString("updatedDate"));
        productReviewModel.setCreatedDate(json.getString("createdDate"));
        productReviewModel.setPostReviewRate(json.getString("postReviewRate"));
        productReviewModel.setLinkedPostCommentProductList(json.getString("linkedPostCommentProductList"));
        return productReviewModel;
    }

    private ProductDetailModel parseProductDetailJSON(JSONObject json) throws JSONException {
        ProductDetailModel productDetailModel = new ProductDetailModel();
        productDetailModel.setProductNo(json.getString("productNo"));
        productDetailModel.setProductName(json.getString("productName"));
        productDetailModel.setProductPrice(json.getString("productPrice"));
        productDetailModel.setProductDiscountPrice(json.getString("productDiscountPrice"));
        productDetailModel.setProductThumbnail(json.getString("productThumbnail"));
        productDetailModel.setProductDescription(json.getString("productDescription"));
        productDetailModel.setRelatedProductNoList(json.getString("relatedProductNoList"));
        productDetailModel.setProductOptionList(json.getString("productOptionList"));
        return productDetailModel;
    }

    private ProductModel parseProductJSON(JSONObject json) throws JSONException {
        ProductModel productModel = new ProductModel();
        productModel.setProductNo(json.getString("productNo"));
        productModel.setMemberNo(json.getString("memberNo"));
        productModel.setProductName(json.getString("productName"));
        productModel.setProductPrice(json.getInt("productPrice"));
        productModel.setProductDiscountPrice(json.getInt("productDiscountPrice"));
        productModel.setProductImgs(json.getString("productImgs"));
        productModel.setProductThumbnail("https://contents.sixshop.com"+json.getString("productThumbnail"));
        productModel.setProductQuantity(json.getInt("productQuantity"));
        productModel.setSellStatus(json.getString("sellStatus"));
        productModel.setLinkedCategoryList(json.getString("linkedCategoryList"));
        productModel.setRelatedProductNoList(json.getString("relatedProductNoList"));
        productModel.setProductOptionList(json.getString("productOptionList"));
        productModel.setOptionTotalQuantity(json.getInt("optionTotalQuantity"));
        productModel.setOptionQuantity(json.getInt("optionQuantity"));
//        productModel.setProductOrderNo(json.getInt("orderNo"));
        productModel.setProductAddress(json.getString("productAddress"));
        productModel.setOptionSoldOut(json.getInt("optionSoldOut"));
        productModel.setRewardPointRate(json.getInt("rewardPointRate"));
        productModel.setProductAppliedDiscountEventPrice(json.getInt("productAppliedDiscountEventPrice"));
        productModel.setProductAppliedDiscountName(json.getString("productAppliedDiscountName"));
        productModel.setProductAppliedDiscountType(json.getString("productAppliedDiscountType"));
        productModel.setProductAppliedDiscountRate(json.getInt("productAppliedDiscountRate"));
        productModel.setUpdatedDate(json.getString("updatedDate"));
        productModel.setCreatedDate(json.getString("createdDate"));
//        productModel.setOptionNo(json.getString("optionNO"));
        productModel.setOptionSKU(json.getString("optionSKU"));
//        productModel.setCategoryNames(json.getString("categoryName"));
        return productModel;
    }

    private CustomerOrderInfoModel parseCustomerOrderInfoJSON(JSONObject json) throws JSONException {
        CustomerOrderInfoModel customerOrderInfoModel = new CustomerOrderInfoModel();
        customerOrderInfoModel.setOrderInfoNo(json.getString("orderInfoNo"));
        customerOrderInfoModel.setMemberNo(json.getString("memberNo"));
        customerOrderInfoModel.setCustomerNo(json.getString("customerNo"));
        customerOrderInfoModel.setOrderNo(json.getString("orderNo"));
        customerOrderInfoModel.setOrderName(json.getString("orderName"));
        customerOrderInfoModel.setGuestOrderName(json.getString("guestOrderName"));
        customerOrderInfoModel.setOrderUniqueNo(json.getString("orderUniqueNo"));
        customerOrderInfoModel.setUpdateData(json.getString("updatedDate"));
        customerOrderInfoModel.setCreatedData(json.getString("createdDate"));
        customerOrderInfoModel.setOrderProductName(json.getString("orderProductName"));
        customerOrderInfoModel.setOrderProductSKU(json.getString("orderProductSKU"));
        customerOrderInfoModel.setOrderQuantity(json.getString("orderQuantity"));
        customerOrderInfoModel.setPayedPrice(String.valueOf(json.getInt("payedPrice")));
        customerOrderInfoModel.setAdditionalFixedOptionPrice(json.getString("additionalFixedOptionPrice"));
        customerOrderInfoModel.setOrderInfoUniqueNo(json.getString("orderInfoUniqueNo"));
        customerOrderInfoModel.setOrderInfoRequest(json.getString("orderInfoRequest"));
        customerOrderInfoModel.setOrderInfoParcelCompany(json.getString("orderInfoParcelCompany"));
        customerOrderInfoModel.setOrderInfoParcelNumber(json.getString("orderInfoParcelNumber"));
        customerOrderInfoModel.setOrderInfoStatusAdditionalInfo(json.getString("orderInfoStatusAdditionalInfo"));
        customerOrderInfoModel.setOrderInfoStatus(json.getString("orderInfoStatus"));
        customerOrderInfoModel.setOrderInfoDeliveryMethod("orderInfoDeliveryMethod");
//        customerOrderInfoModel.setIndividualCustomerUniqueCode(json.getString("individualCustomerUniqueCode"));
        customerOrderInfoModel.setOrderInfoShippingDueDate(json.getString("orderInfoShippingDueDate"));

        return customerOrderInfoModel;
    }

    private CustomerOrderModel parseCustomerOrderJSON(JSONObject json) throws JSONException {
        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        customerOrderModel.setOrderNo(json.getString("orderNo"));
        customerOrderModel.setMemberNo(json.getString("memberNo"));
        customerOrderModel.setCustomerNo(json.getString("customerNo"));
        customerOrderModel.setOrderSequence(json.getString("orderSequence"));
        customerOrderModel.setOrderName(json.getString("orderName"));
        customerOrderModel.setOrderEmail(json.getString("orderEmail"));
        customerOrderModel.setOrderAddress(json.getString("orderAddress"));
        customerOrderModel.setOrderPhone(json.getString("orderPhone"));
        customerOrderModel.setOrderTitle(json.getString("orderTitle"));
        customerOrderModel.setOrderPrice(String.valueOf(json.getInt("orderPrice")));
        customerOrderModel.setDeliveryPrice(String.valueOf(json.getInt("deliveryPrice")));
        customerOrderModel.setOrderCartList(json.getString("orderCartList"));
        customerOrderModel.setOrderTid(json.getString("orderTid"));
        customerOrderModel.setOrderAccount(json.getString("orderAccount"));
        customerOrderModel.setPayMethod(json.getString("payMethod"));
        customerOrderModel.setPgType(json.getString("pgType"));
        customerOrderModel.setOrderStatus(json.getString("orderStatus"));
        customerOrderModel.setOrderOptions(json.getString("orderOptions"));
        customerOrderModel.setOrderRequest(json.getString("orderRequest"));
        customerOrderModel.setParcelCompany(json.getString("parcelCompany"));
        customerOrderModel.setParcelNumber(json.getString("parcelNumber"));
        customerOrderModel.setMoneySender(json.getString("moneySender"));
        customerOrderModel.setRefundAccountInfo(json.getString("refundAccountInfo"));
        customerOrderModel.setGuestOrderName(json.getString("guestOrderName"));
        customerOrderModel.setGuestOrderPhone(json.getString("guestOrderPhone"));
        customerOrderModel.setOrderRefundError(json.getString("orderRefundError"));
        customerOrderModel.setOrderRefundErrorMsg(json.getString("orderRefundErrorMsg"));
        customerOrderModel.setConfirmRefundError(json.getString("confirmRefundError"));
        customerOrderModel.setOrderError(json.getString("orderError"));
        customerOrderModel.setOrderUniqueNo(json.getString("orderUniqueNo"));
        customerOrderModel.setOrderMemo(json.getString("orderMemo"));
        customerOrderModel.setOrderCancelReason(json.getString("orderCancelReason"));
        customerOrderModel.setOrderBankExpireDate(json.getString("orderBankExpireDate"));
        customerOrderModel.setBankExpireDateText(json.getString("bankExpireDateText"));
        customerOrderModel.setUpdateDate(json.getString("updatedDate"));
        customerOrderModel.setCreatedDate(json.getString("createdDate"));
        customerOrderModel.setOrderHistoryType(json.getString("orderHistoryType"));
        customerOrderModel.setShopOrderInfos(json.getString("shopOrderInfos"));
        customerOrderModel.setCustomerName(json.getString("customerName"));
        customerOrderModel.setCustomerPhone(json.getString("customerPhone"));
        customerOrderModel.setCustomerEmail(json.getString("customerEmail"));
        customerOrderModel.setCustomerId(json.getString("customerId"));
        customerOrderModel.setOrderTotalQuantity(json.getString("orderTotalQuantity"));
        customerOrderModel.setCancelOrderQuantity(json.getString("cancelOrderQuantity"));
        customerOrderModel.setOrderProductName(json.getString("orderProductName"));
        customerOrderModel.setOrderProductSKU(json.getString("orderProductSKU"));
        customerOrderModel.setOrderQuantity(json.getString("orderQuantity"));
        customerOrderModel.setPayedPrice(json.getString("payedPrice"));
        customerOrderModel.setAdditionalFixedOptionPrice(json.getString("additionalFixedOptionPrice"));
        customerOrderModel.setOrderCustomizedOption(json.getString("orderCustomizedOption"));
        customerOrderModel.setOrderCanceled(json.getString("orderCanceled"));
        customerOrderModel.setOrderPaymentDate(json.getString("orderPaymentDate"));
        customerOrderModel.setOrderInfoUniqueNo(json.getString("orderInfoUniqueNo"));
        customerOrderModel.setOrderInfoRequest(json.getString("orderInfoRequest"));
        customerOrderModel.setOrderInfoParcelCompany(json.getString("orderInfoParcelCompany"));
        customerOrderModel.setOrderInfoParcelNumber(json.getString("orderInfoParcelNumber"));
        customerOrderModel.setOrderInfoStatusAdditionalInfo(json.getString("orderInfoStatusAdditionalInfo"));
        customerOrderModel.setOrderInfoStatus(json.getString("orderInfoStatus"));
        customerOrderModel.setOrderInfoDeliveryMethod("orderInfoDeliveryMethod");
//        customerOrderModel.setIndividualCustomerUniqueCode(json.getString("individualCustomerUniqueCode"));
        customerOrderModel.setOrderInfoShippingDueDate(json.getString("orderInfoShippingDueDate"));
        customerOrderModel.setOrderInfoTemp(json.getString("orderInfoTemp"));
        customerOrderModel.setOrderStatusDescription(json.getString("orderStatusDescription"));
        customerOrderModel.setPayMethodDescription(json.getString("payMethodDescription"));
        return customerOrderModel;
    }

    private CustomerInfoModel parseCustomerInfoJSON(JSONObject json) throws JSONException {
        CustomerInfoModel customerInfoModel = new CustomerInfoModel();
        customerInfoModel.setCustomerNo(json.getString("customerNo"));
        customerInfoModel.setMemberNo(json.getString("memberNo"));
        customerInfoModel.setCustomerGradeNo(json.getString("customerGradeNo"));
        customerInfoModel.setCustomerName(json.getString("customerName"));
        customerInfoModel.setCustomerEmail(json.getString("customerEmail"));
        customerInfoModel.setCustomerId(json.getString("customerId"));
        customerInfoModel.setCustomerPassword(json.getString("customerPassword"));
        customerInfoModel.setSignupFrom(json.getString("signupFrom"));
        customerInfoModel.setSignupKey(json.getString("signupKey"));
        customerInfoModel.setUniqueKey(json.getString("uniqueKey"));
        customerInfoModel.setKakaoKey(json.getString("kakaoKey"));
        customerInfoModel.setCustomerAddress(json.getString("customerAddress"));
        customerInfoModel.setCustomerPhone(json.getString("customerPhone"));
        customerInfoModel.setCustomerBirthDate(json.getString("customerBirthDate"));
        customerInfoModel.setCustomerOptions(json.getString("customerOptions"));
        customerInfoModel.setCustomerGrade(json.getString("customerGrade"));
        customerInfoModel.setCustomerPoint(json.getString("customerPoint"));
        customerInfoModel.setWithdrawal(json.getString("withdrawal"));
        customerInfoModel.setCustomerSumOfOrderPrice(json.getString("customerSumOfOrderPrice"));
        customerInfoModel.setSerialNumber(json.getString("serialNumber"));
        customerInfoModel.setCustomerOrderCountNo(json.getString("customerOrderCountNo"));
        customerInfoModel.setRefundBankName(json.getString("refundBankName"));
        customerInfoModel.setRefundBankAccountHolder(json.getString("refundBankAccountHolder"));
        customerInfoModel.setRefundBankAccount(json.getString("refundBankAccount"));
        customerInfoModel.setCustomerMemo(json.getString("customerMemo"));
        customerInfoModel.setLoginDate(json.getString("loginDate"));
        customerInfoModel.setCustomerGradeExpireDate(json.getString("customerGradeExpireDate"));
        customerInfoModel.setUpdateDate(json.getString("updatedDate"));
        customerInfoModel.setCreatedDate(json.getString("createdDate"));
        customerInfoModel.setBlogPostLikeList(json.getString("blogPostLikeList"));
        customerInfoModel.setBoardPostLikeList(json.getString("boardPostLikeList"));
        customerInfoModel.setCustomerGradeTitle(json.getString("customerGradeTitle"));
        customerInfoModel.setCustomerDi(json.getString("customerDi"));
        customerInfoModel.setCustomerCommId(json.getString("customerCommId"));
        customerInfoModel.setCustomerSex(json.getString("customerSex"));
        customerInfoModel.setCustomerSumOfUsedPoint(json.getString("customerSumOfUsedPoint"));
        customerInfoModel.setCustomerRecommenderNo(json.getString("customerRecommenderNo"));
        customerInfoModel.setCustomerRecommenderIdOrEmail(json.getString("customerRecommenderIdOrEmail"));
        customerInfoModel.setLoginFailCount(json.getString("loginFailCount"));
        customerInfoModel.setSmsMarketingAgreement(json.getString("smsMarketingAgreement"));
//        customerInfoModel.setSmsMarketingAgreementUpdateDate(json.getString("smsMarketingAgreementUpdateDate"));
        customerInfoModel.setEmailMarketingAgreement(json.getString("emailMarketingAgreement"));
        customerInfoModel.setEmailMarketingAgreementUpdateDate(json.getString("emailMarketingAgreementUpdatedDate"));
        return customerInfoModel;
    }

    private PageDataModel parsePageDataJSON(JSONObject json) throws JSONException {
        PageDataModel pageDataModel = new PageDataModel();
        pageDataModel.setPageNo(json.getInt("pageNo"));
        pageDataModel.setPageLink(json.getString("pageLink"));
        pageDataModel.setPageName(json.getString("pageName"));
        pageDataModel.setPageOrderNo(json.getInt("pageOrderNo"));
        return pageDataModel;
    }

    private ProductOptionNameModel parseProductOptionNameJSON(JSONObject json) throws JSONException {
        ProductOptionNameModel productOptionNameModel = new ProductOptionNameModel();
        productOptionNameModel.setOptionNameNo(String.valueOf(json.getInt("optionNameNo")));
        productOptionNameModel.setProductNo(String.valueOf(json.getInt("productNo")));
        productOptionNameModel.setOptionName(json.getString("optionName"));
        productOptionNameModel.setOptionNameOrderNo(json.getString("optionNameOrderNo"));
        productOptionNameModel.setUpdatedDate(json.getString("updatedDate"));
        productOptionNameModel.setCreatedDate(json.getString("createdDate"));
        productOptionNameModel.setOptionValueList(json.getString("optionValueList"));
        return productOptionNameModel;
    }

    private ProductOptionValueModel parseProductOptionValueJSON(JSONObject json) throws JSONException {
        ProductOptionValueModel productOptionValueModel = new ProductOptionValueModel();
        productOptionValueModel.setOptionValueNo(String.valueOf(json.getInt("optionValueNo")));
        productOptionValueModel.setProductNo(String.valueOf(json.getInt("productNo")));
        productOptionValueModel.setOptionNameNo(String.valueOf(json.getInt("optionNameNo")));
        productOptionValueModel.setOptionValue(json.getString("optionValue"));
        productOptionValueModel.setUpdatedDate(json.getString("updatedDate"));
        productOptionValueModel.setCreatedDate(json.getString("createdDate"));
        return productOptionValueModel;
    }

    public void progressDialog(Context context, int i) {
        switch (i) {
            case PROGRESS_DIALOG_OPEN:
                //        progressDialog = new ProgressDialog(mContext, R.style.MyProgressBarTheme);
                // To change the color of progressBar, change colorAccent from the colors.xml
                progressDialog = new ProgressDialog(context);
                progressDialog.show();
                progressDialog.getWindow().setLayout(700, 700);
                progressDialog.setContentView(R.layout.progress_dialog_custom);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                break;
            case PROGRESS_DIALOG_CLOSE:
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                break;
        }
    }
}
