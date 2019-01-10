package org.androidtown.test;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.test.models.CartModel;
import org.androidtown.test.models.CustomerInfoModel;
import org.androidtown.test.models.CustomerOrderInfoModel;
import org.androidtown.test.models.CustomerOrderModel;
import org.androidtown.test.models.HistoryListModel;
import org.androidtown.test.models.PackageTrackingListModel;
import org.androidtown.test.models.PageDataModel;
import org.androidtown.test.models.PaymentListModel;
import org.androidtown.test.models.ProductDetailModel;
import org.androidtown.test.models.ProductModel;
import org.androidtown.test.models.AddressListModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickedListener, OnResponseCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final String SELECTED_ITEM = "arg_selected_item";
    private static final Object SimpleDateFormat = -1;
    private static final int PROGRESS_DIALOG_OPEN = 0;
    private static final int PROGRESS_DIALOG_CLOSE = 1;
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE = "Cookie";
    private static final String SESSION = "SESSION";
    private static SharedPreferences sharedPreferences = null;
//    private int selectedItem;
    private Fragment frag;
    private Fragment currentFragment;
    private static FragmentManager fm;
    private static FragmentTransaction ft;
    private Bundle bundles;
    private Bundle bundleAddress;
    private Bundle bundleProduct;
    private Bundle bundleHistory;
    private Bundle bundleCustomerInfo;
    private AddressListModel userAddressSelected, orderAddressFinal;
    private ArrayList<AddressListModel> userAddressList;
    private PaymentListModel userPaymentSelected, orderPaymentFinal;
    private ArrayList<PaymentListModel> userPaymentList;
    private ProductModel productModel, selectedProduct;
    private ArrayList<ProductModel> orderProductList, productList;
    private ArrayList<HistoryListModel> userHistoryList;
    private ArrayList<CustomerOrderModel> customerOrderList;
    private ArrayList<CustomerOrderInfoModel> customerOrderInfoList;
    private ArrayList<String> productImgList;
    private ArrayList<PageDataModel> pageDataList;
    private CustomerInfoModel customerInfoModel;
    private ProductDetailModel productDetailModel;
    private CartModel cartModel;
    private ArrayList<CartModel> cartList;
    private ProgressDialog progressDialog;
    private String buyNowCartNo;
    private String selectedCartNo;
    private int mCartItemCount;

    private NavigationView navigationView;
    private static String TAG = MainActivity.class.getSimpleName();
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private TextView tvCartItemCount;

    private OnItemClickedListener onItemClickedListener;
    private OnResponseCallback onResponseCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        selectedProduct = new ProductModel();

        productDetailModel = new ProductDetailModel();
        productModel = new ProductModel();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        orderProductList = new ArrayList<>();
        customerInfoModel = new CustomerInfoModel();
        customerOrderList = new ArrayList<>();
        customerOrderInfoList = new ArrayList<>();
        productImgList = new ArrayList<>();

        bundleCustomerInfo = new Bundle();
        bundleCustomerInfo.putSerializable("customerInfoDataList", customerInfoModel);

        // Initially set user address list data
        userAddressList = new ArrayList<>();
        bundleAddress = new Bundle();
        bundleAddress.putSerializable("userAddressList", userAddressList);

        // Initially set selectedAddress with DefaultAddress
        for(int i = 0; i < userAddressList.size(); i++) {
            if(userAddressList.get(i).getDefault().matches("true")){
                this.userAddressSelected = userAddressList.get(i); } }

        // Initially set product list data
        productList = new ArrayList<ProductModel>();
        initProductListSet();
        bundleProduct = new Bundle();
        bundleProduct.putSerializable("productList", productList);

        // Initially set order history
        userHistoryList = new ArrayList<>();
        bundleHistory = new Bundle();
        bundleHistory.putSerializable("userHistoryList", userHistoryList);
        bundleHistory.putSerializable("customerOrderList", customerOrderList);

        // Setting spinner for user cards
        userPaymentList = new ArrayList<>();
        initPaymentListSet();

        // Toolbar & navigation drawer setup
        final Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.setElevation(20); }
            toolbar.setTitle("");
            toolbar.findViewById(R.id.logo).setOnClickListener(this);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mDrawerLayout = findViewById(R.id.drawerLayout);
            setupDrawer();
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            frag = newInstance(new LoginFragment(), null);
            replaceFragment(frag, false);
        }

//         Setting Bottom Navigation View
//        btmNav = findViewById(R.id.btm_nav);
//        btmNav.setVisibility(View.GONE);
//        BottomNavigationViewHelper.disableShiftMode(btmNav);
//        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                selectFragment(item);
//                return true; } });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        outState.putInt(SELECTED_ITEM, selectedItem);
        super.onSaveInstanceState(outState); }

    @Override
    public void onBackPressed() {
//        MenuItem homeItem = btmNav.getMenu().getItem(0);
//        if(selectedProduct != homeItem.getItemId()) {
//            // select home item
//            selectFragment(homeItem);
//            btmNav.setSelectedItemId(homeItem.getItemId()); }
//            else { super.onBackPressed(); }
//        btmNav.setVisibility(View.GONE);

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        if(!(fm.findFragmentById(R.id.container) instanceof OrderFragment) && fm.getBackStackEntryCount() == 0) {
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logo:
                onNavigationItemSelected(navigationView.getMenu().getItem(0));
                navigationView.getMenu().getItem(0).setChecked(true);
                return;
            case R.id.ll_item_card:
                bundles = new Bundle();
                bundles.putSerializable("productDetailModel", productDetailModel);
                bundles.putSerializable("productModel", productModel);
                frag = newInstance(new ProductDetailFragment(), bundles);
                break;
            case R.id.btn_log:
                frag = newInstance(new LoginFragment(), null);
                break;
            case R.id.btn_login:
                frag = newInstance(new HomeFragment(), bundleCustomerInfo);
                break;
            case R.id.btn_login_facebook:
//                initializeData();
//                frag = newInstance(new HomeFragment(), bundleCustomerInfo);
                bundles = new Bundle();
                bundles.putSerializable("pageDataModel", pageDataList.get(0));
                bundles.putSerializable("productList", productList);
                bundles.putSerializable("customerInfoModel", customerInfoModel);
                bundles.putSerializable("customerOrderList", customerOrderList);
                frag = newInstance(new OrderFragment(), bundles);
                replaceFragment(frag, false);
                navigationView.getMenu().getItem(0).setChecked(true);
                return;
            case R.id.btn_buy_now:
                addToCart(view);
//                orderAddressFinal = userAddressSelected;
//                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
//                bundles.putSerializable("productList", productList);
//                bundles.putSerializable("customerInfoModel", customerInfoModel);
//                bundles.putSerializable("cartList", cartList);
                frag = newInstance(new OrderProductFragment(), null);
                replaceFragment(frag, true);
                return;
            case R.id.btn_put_cart:
                addToCart(view);
                return;
            case R.id.tv_order_item_remove:
                removeFromCart();
                return;
            case R.id.btn_order_address_default:
                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
                bundles.putSerializable("userAddressSelected", userAddressSelected);
                bundles.putSerializable("position",userAddressList.indexOf(userAddressSelected));
                frag = newInstance(new AddressDetailFragment(), bundles);
                break;
            case R.id.btn_order_address_new:
                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
                bundles.putSerializable("userAddressSelected", null);
                frag = newInstance(new AddressDetailFragment(), bundles);
                break;
            case R.id.btn_order_address_confirm:
                orderAddressFinal = userAddressSelected;
                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
                bundles.putSerializable("productList", productList);
                bundles.putSerializable("customerInfoModel", customerInfoModel);
                frag = newInstance(new OrderProductFragment(), bundles);
                break;
            case R.id.btn_order_product_confirm:
                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
                bundles.putSerializable("userPaymentList", userPaymentList);
                frag = newInstance(new PaymentDetailFragment(), bundles);
                break;
            case R.id.btn_order_payment_confirm:
                bundles = new Bundle();
//                addOrderHistory(userAddressSelected, orderProductList, userPaymentSelected);
                bundles.putSerializable("userOrderData", null);
                frag = newInstance(new DeliveryFragment(), bundleHistory);
                break;
            case R.id.btn_payment:
            case R.id.btn_payment_save:
            case R.id.btn_payment_default:
                bundles = new Bundle();
                bundles.putSerializable("userPaymentList", userPaymentList);
                frag = newInstance(new PaymentFragment(), bundles);
                break;
            case R.id.btn_payment_add:
                bundles = new Bundle();
                bundles.putSerializable("userPaymentList", userPaymentList);
                frag = newInstance(new PaymentDetailFragment(), bundles);
                break;
            case R.id.btn_user_address:
//            case R.id.button_address_list:
            case R.id.btn_address_save:
            case R.id.btn_address_default:
                bundles = new Bundle();
                bundles.putSerializable("userAddressList", userAddressList);
                bundles.putInt("buttonId", view.getId());
                frag = newInstance(new AddressFragment(), bundles);
                break;
            case R.id.btn_address_add:
                frag = newInstance(new AddressDetailFragment(), null);
                break;
            case R.id.btn_address_select:
                bundles = new Bundle();
                bundles.putSerializable("userAddressSelected", userAddressSelected);
                bundles.putSerializable("productList", productList);
                bundles.putSerializable("userAddressList", userAddressList);
                frag = newInstance(new OrderFragment(), bundles);
                break;
            case R.id.btn_package_track:
                SmartTrackingAPITask t = new SmartTrackingAPITask();
                String invoiceNumber = "6704304060820";
                try{
                    PackageTrackingListModel packageTrackingListModel = t.execute(invoiceNumber).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                bundles = new Bundle();
                frag = newInstance(new DeliveryTrackFragment(), null);
                break;
        }

        replaceFragment(frag, true);
    }


    public static Fragment newInstance(Fragment frag, Bundle args) {
        if(args==null) { Bundle mArgs = new Bundle(); frag.setArguments(mArgs);
        } else { frag.setArguments(args); }
        return frag;
    }

    private void replaceFragment(Fragment frag, boolean addToBackStack) {
        ft = fm.beginTransaction();
        currentFragment = fm.findFragmentById(R.id.container);
        if (frag != null) {
            if (currentFragment != null) {
                if (currentFragment.getClass() == frag.getClass()) {
                    NestedScrollView nsv = currentFragment.getView().findViewById(R.id.nsv);
                    nsv.fullScroll(View.FOCUS_UP);
                    return;
                }
                else if(frag instanceof OrderFragment && fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, fm.POP_BACK_STACK_INCLUSIVE);
                    return;
                }
            }
            ft.replace(R.id.container, frag);
            if(addToBackStack) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }
    }

    public void addOrderHistory(AddressListModel addressOrdered, ArrayList<ProductModel> itemOrdered, PaymentListModel paymentMethod) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String currentDate = dateFormat.format(date);
        userHistoryList.add(new HistoryListModel(addressOrdered, itemOrdered, paymentMethod, currentDate, "50000", "1000", "-1","12312",0, 0));
    }

    private void initializeData() {
//        userAddressList.add(new AddressListModel("true",customerInfoModel.getCustomerName(),customerInfoModel.getCustomerAddress(),customerInfoModel.getCustomerPhone(), customerInfoModel.getCustomerEmail(),null));
//        userAddressList.add(new AddressListModel("true", customerInfoModel.getCustomerName(), customerInfoModel.getCustomerAddress(), customerInfoModel.getCustomerPhone(), customerInfoModel.getCustomerEmail(),""));
    }

    private void initProductListSet() {
//        progressDialog(this, PROGRESS_DIALOG_OPEN);
        new CustomerLoginAPIClient( "SHOP_PRODUCT_BY_CATEGORY", "allFilter, 106216, 106354, 106355, 106356, 106468, 106223, 116059, 118199", null, null, null, this, (OnItemClickedListener) this, null);
        new CustomerLoginAPIClient("SHOP_DESIGN_AND_DEFAULT", null, null, null, null,this, this, null);
    }

    private void initPaymentListSet() {
        userPaymentList.add(new PaymentListModel("true","국민", 1234, 5678, 9012, 3456, 01, 18, -1, -1));
        userPaymentList.add(new PaymentListModel("false","신한", 5678, 9012, 3456, 7890, 02, 19, -1, -1));
        userPaymentList.add(new PaymentListModel("false","농협", 9012, 3456, 7890, 1234, 03, 20, -1, -1));
    }


    public class SmartTrackingAPITask extends AsyncTask<String, Void, PackageTrackingListModel> {

        @Override
        protected PackageTrackingListModel doInBackground(String... strings) {
            SmartTrackingAPIClient client = new SmartTrackingAPIClient();
            String invoiceNo = strings[0];
            // API 호출
            return client.getPackageTrackingList(invoiceNo);
        }
    }

    public static void checkSessionCookie(Map<String, String> headers) {
        if (headers.containsKey(SET_COOKIE)
                && headers.get(SET_COOKIE).startsWith(SESSION)) {
            String cookie = headers.get(SET_COOKIE);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString(SESSION, cookie);
                prefEditor.commit();
            }
        }
    }

    public static void addSessionCookie(Map<String, String> headers) {
        String sessionId = sharedPreferences.getString(SESSION, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE));
            }
            headers.put(COOKIE, builder.toString());
        }
    }

    public static void setAlert(String alertTitle, String alertMessage, Context context) {
        final View dialogCustomView = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_NoActionBar);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        final Dialog dialog = builder.setView(dialogCustomView).create();
        TextView tvErrorMessage = dialogCustomView.findViewById(R.id.tv_error_message);
        tvErrorMessage.setText(alertMessage);
        Button buttonErrorConfirm = dialogCustomView.findViewById(R.id.button_error_confirm);
        buttonErrorConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void addToCart(View view) {
        String cartType = view.getId() == R.id.btn_buy_now ? "buyNow" : "" ;
        ArrayList<String> optionNoList = new ArrayList<>();
        optionNoList.add(selectedProduct.getOptionNo());
        new CustomerLoginAPIClient("ADD_TO_CART", selectedProduct.getProductNo(), String.valueOf(selectedProduct.getProductQuantity()), cartType, optionNoList,  this, this, null);
    }

    private void removeFromCart() {
        new CustomerLoginAPIClient("DELETE_CART", selectedCartNo, null, null, null, this, this, null);
    }

    // ************* Set drawer *************

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(fm.getBackStackEntryCount() > 0) {
                    // show back button
                    mDrawerToggle.setDrawerIndicatorEnabled(false);
                } else {
                    // show hamburger
                    mDrawerToggle.setDrawerIndicatorEnabled(true);
                    mDrawerToggle.syncState();
                }

            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_toolbar_items, menu);
        final MenuItem menuItem = menu.findItem(R.id.btn_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        tvCartItemCount = actionView.findViewById(R.id.cart_badge);
        setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                if(fm.getBackStackEntryCount() < 1) {
                    break;
                } else {
                    onBackPressed();
                }
                return true;
            case R.id.btn_cart:
//                bundles = new Bundle();
//                bundles.putSerializable("orderProductList", orderProductList);
//                bundles.putSerializable("productList", productList);
//                bundles.putSerializable("customerInfoModel", customerInfoModel);
//                bundles.putSerializable("cartList", cartList);
                if(!(frag instanceof OrderProductFragment)) {
                    frag = newInstance(new OrderProductFragment(), null);
                    replaceFragment(frag, true);
                } else {
                    replaceFragment(frag, true);
                }
                onSuccessBuyNowCartNo("");
                return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.logo:
            case R.id.menu_home:
                bundles = new Bundle();
                bundles.putSerializable("pageDataModel", pageDataList.get(0));
                bundles.putSerializable("productList", productList);
                bundles.putSerializable("customerInfoModel", customerInfoModel);
                frag = newInstance(new OrderFragment(), bundles);
                System.out.println("$$$$$$$$$ "+fm.getBackStackEntryCount());
                break;
            case R.id.menu_delivery:
                bundles = new Bundle();
                bundles.putSerializable("customerInfoModel", customerInfoModel);
                bundles.putSerializable("customerOrderList", customerOrderList);
                frag = newInstance(new DeliveryFragment(), bundles);
                break;
            case R.id.menu_user:
                frag = newInstance(new UserFragment(), bundles);
                break;
            default:
                break;
        }
        replaceFragment(frag, false);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupBadge() {
        if(tvCartItemCount != null) {
            if(mCartItemCount == 0) {
                if (tvCartItemCount.getVisibility() != View.GONE) {
                    tvCartItemCount.setVisibility(View.GONE);
                }
            } else {
                tvCartItemCount.setText(String.valueOf(cartList.size()));
                if(tvCartItemCount.getVisibility() != View.VISIBLE) {
                    tvCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    // ***************** OnItemClickedListener *****************

    @Override
    public void selectedProduct(ProductModel productModel) {
        selectedProduct = productModel;
    }

    @Override
    public void selectedAddress(AddressListModel selectedAddress) {
        userAddressSelected = selectedAddress;
    }

    @Override
    public void selectedPayment(PaymentListModel selectedPayment) {
        userPaymentSelected = selectedPayment;
    }

    @Override
    public void selectedCartNo(int cartNo) {
        selectedCartNo = String.valueOf(cartNo);
    }

    @Override
    public void addAddress(AddressListModel userAddress) { userAddressList.add(userAddress); }

    @Override
    public void removeAddress(int position) {
        if(userAddressList.size() <= 1) { Toast.makeText(this, "한개 이상의 주소가 필요합니다.", Toast.LENGTH_LONG).show();
        } else { userAddressList.remove(position); }
    }

    @Override
    public void defaultAddress() {
        for(int i = 0; i < userAddressList.size() - 1; i++) { userAddressList.get(i).setDefault("false"); }
    }

    @Override
    public void addPayment(PaymentListModel userPayment) {
        userPaymentList.add(userPayment);
    }

    @Override
    public void removePayment(int position) {
        if(userPaymentList.size() <= 1) { Toast.makeText(this, "한개 이상의 카드를 입력해야합니다.", Toast.LENGTH_LONG).show();
        } else {
            userPaymentList.remove(position); }
    }

    @Override
    public void defaultPayment() {
        for(int i = 0; i < userPaymentList.size() - 1; i++) { userPaymentList.get(i).setDefault("false"); }
    }

    @Override
    public void updateCustomerInfo(CustomerInfoModel customerInfoModel) {
        this.customerInfoModel = customerInfoModel;
    }

    @Override
    public void updateCustomerOrderList(ArrayList<CustomerOrderModel> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    @Override
    public void updateCustomerOrderInfoList(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {
        this.customerOrderInfoList = customerOrderInfoList;
    }

    @Override
    public void onSuccessProductListPull(ArrayList<ProductModel> productList) {
        this.productList = productList;
    }

    @Override
    public void onSuccessProductImgList(ArrayList<String> imgURLList) {
        this.productImgList = imgURLList;
    }

    @Override
    public void onSuccessPageDataList(ArrayList<PageDataModel> pageDataList) {
        this.pageDataList = pageDataList;
    }

    @Override
    public void onSuccessBuyNowCartNo(String buyNowCartNo) {
        this.buyNowCartNo = buyNowCartNo;
        new CustomerLoginAPIClient("SHOP_CARTS_BY_CUSTOMER", buyNowCartNo,"", null, null, this, this, null);
    }

    @Override
    public void onSuccessCartList(ArrayList<CartModel> cartList) {
        this.cartList = cartList;
        if(buyNowCartNo == null || buyNowCartNo.isEmpty()) {
            mCartItemCount = this.cartList.size();
            invalidateOptionsMenu();
        }
        if(frag instanceof OrderProductFragment) {
            ((OrderProductFragment)frag).notifyDataSetChange(this.cartList);
        }
    }

    // ***************** OnResponseCallback *****************

    @Override
    public void onResponseLogin(String response) {

    }

    @Override
    public void onSuccessLogin(CustomerInfoModel customerInfoModel) {

    }

    @Override
    public void onSuccessOrderListPull(ArrayList<CustomerOrderModel> customerOrderList) {

    }

    @Override
    public void onSuccessOrderInfoPull(ArrayList<CustomerOrderInfoModel> customerOrderInfoList) {

    }

    @Override
    public void onSuccessProductDetailPull(ProductDetailModel productDetailModel) {
        this.productDetailModel = productDetailModel;
        Bundle args = new Bundle();
        args.putSerializable("productDetailModel", this.productDetailModel);
        args.putSerializable("productModel", this.productDetailModel);
        Fragment frag = new ProductDetailFragment();
        frag.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).addToBackStack(null).commit();
    }

}
