package org.androidtown.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.travijuu.numberpicker.library.NumberPicker;

import org.androidtown.test.models.ProductModel;
import org.androidtown.test.models.PaymentListModel;

import java.util.ArrayList;

public class PaymentDetailFragment extends Fragment implements View.OnClickListener {

    private ArrayList<ProductModel> mUserOrderItemList;
    private ArrayList<PaymentListModel> mUserPaymentList;
    private PaymentListModel mUserPaymentSelected, orderPaymentFinal;
    private EditText mEditTextUserPaymentName;
    private EditText mEtUserPaymentNumber1;
    private EditText mEtUserPaymentNumber2;
    private EditText mEtUserPaymentNumber3;
    private EditText mEtUserPaymentNumber4;
    private NumberPicker mNumberPickerMonth;
    private NumberPicker mNumberPickerYear;
    private EditText mEtUserPaymentPassword;
    private EditText mEtUserPaymentVerification;
    private String mUserPaymentDefault;
    private String mUserPaymentName;
    private int mUserPaymentNumber1;
    private int mUserPaymentNumber2;
    private int mUserPaymentNumber3;
    private int mUserPaymentNumber4;
    private int mUserExpirationMonth;
    private int mUserExpirationYear;
    private int mUserPaymentPassword = -1;
    private int mUserPaymentVerification = -1;
    private int position;
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<String> mUserPaymentNameList;
    private TableLayout orderVerification;
    private int GONE = getView().GONE;
    private int VISIBLE = getView().VISIBLE;
    private OnItemClickedListener mOnItemClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnItemClickedListener = (OnItemClickedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_payment_detail, container, false);
        Button mBtnSave = view.findViewById(R.id.btn_payment_save);
        Button mBtnRemove = view.findViewById(R.id.button_payment_remove);
        Button mBtnDefault = view.findViewById(R.id.btn_payment_default);
        Button mBtnConfirm = view.findViewById(R.id.btn_order_payment_confirm);
        mBtnSave.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
        mBtnDefault.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mEditTextUserPaymentName = view.findViewById(R.id.text_user_payment_name);
        mEtUserPaymentNumber1 = view.findViewById(R.id.text_user_payment_number1);
        mEtUserPaymentNumber2 = view.findViewById(R.id.text_user_payment_number2);
        mEtUserPaymentNumber3 = view.findViewById(R.id.text_user_payment_number3);
        mEtUserPaymentNumber4 = view.findViewById(R.id.text_user_payment_number4);
        mNumberPickerMonth = view.findViewById(R.id.number_picker_expiration_month);
        mNumberPickerYear = view.findViewById(R.id.number_picker_expiration_year);
        mEtUserPaymentPassword = view.findViewById(R.id.text_user_payment_password);
        mEtUserPaymentVerification = view.findViewById(R.id.text_user_payment_verification);
        orderVerification = view.findViewById(R.id.table_layout_order_verification);

        mUserPaymentList = (ArrayList<PaymentListModel>) getArguments().getSerializable("userPaymentList");
        mUserPaymentSelected = (PaymentListModel) getArguments().getSerializable("userPaymentSelected");

        // Set Spinner Adapter and Listener
        spinner = view.findViewById(R.id.spinner_payment);
        mUserPaymentNameList = new ArrayList<>();
        for(int i = 0; i < mUserPaymentList.size(); i++) {
            mUserPaymentNameList.add(mUserPaymentList.get(i).getPaymentName()); }
        spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.layout_spinner_custom, mUserPaymentNameList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                PaymentListModel userPaymentSelected = mUserPaymentList.get(position);
                mEtUserPaymentNumber1.setText(String.valueOf(userPaymentSelected.getPaymentNumber1()));
                mEtUserPaymentNumber2.setText(String.valueOf(userPaymentSelected.getPaymentNumber2()));
                mEtUserPaymentNumber3.setText(String.valueOf(userPaymentSelected.getPaymentNumber3()));
                mEtUserPaymentNumber4.setText(String.valueOf(userPaymentSelected.getPaymentNumber4()));
                mNumberPickerMonth.setValue(userPaymentSelected.getPaymentExpirationMonth());
                mNumberPickerYear.setValue(userPaymentSelected.getPaymentExpirationYear());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Checking to see if the class is called from order payment or user payment
        mUserOrderItemList = (ArrayList<ProductModel>) getArguments().getSerializable("orderProductList");
        if(mUserOrderItemList == null) {
            // Class is called from User Payment Fragment
            mBtnConfirm.setVisibility(GONE);
            mBtnSave.setVisibility(VISIBLE);
            spinner.setVisibility(GONE);
            mEditTextUserPaymentName.setVisibility(VISIBLE);
            orderVerification.setVisibility(GONE);
        } else {
            // Class is called from Order Payment Fragment
            mBtnConfirm.setVisibility(VISIBLE);
            mBtnSave.setVisibility(GONE);
            mBtnRemove.setVisibility(GONE);
            mBtnDefault.setVisibility(GONE);
            spinner.setVisibility(VISIBLE);
            mEditTextUserPaymentName.setVisibility(GONE);
            orderVerification.setVisibility(VISIBLE);
        }

        // Checking to see if its edit or new. In this case, its edit.
        if(mUserPaymentSelected != null) {
            position = (int) getArguments().getSerializable("position");
            mEditTextUserPaymentName.setText(mUserPaymentSelected.getPaymentName());
            mEtUserPaymentNumber1.setText(String.valueOf(mUserPaymentSelected.getPaymentNumber1()));
            mEtUserPaymentNumber2.setText(String.valueOf(mUserPaymentSelected.getPaymentNumber2()));
            mEtUserPaymentNumber3.setText(String.valueOf(mUserPaymentSelected.getPaymentNumber3()));
            mEtUserPaymentNumber4.setText(String.valueOf(mUserPaymentSelected.getPaymentNumber4()));
            mNumberPickerMonth.setValue(mUserPaymentSelected.getPaymentExpirationMonth());
            mNumberPickerYear.setValue(mUserPaymentSelected.getPaymentExpirationYear());
            mBtnSave.setOnClickListener(this);
            mBtnRemove.setOnClickListener(this);
            mBtnDefault.setOnClickListener(this);
        } else {
            mBtnRemove.setClickable(false);
            mBtnDefault.setClickable(false);
            mBtnRemove.getBackground().setAlpha(70);
            mBtnDefault.getBackground().setAlpha(70);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_order_payment_confirm:
                if(checkInput()) {
                    getInput();
                    getVerifications();
                    orderPaymentFinal = new PaymentListModel("false",mUserPaymentName,mUserPaymentNumber1, mUserPaymentNumber2, mUserPaymentNumber3, mUserPaymentNumber4,mUserExpirationMonth,mUserExpirationYear,mUserPaymentPassword,mUserPaymentVerification);
                    mOnItemClickedListener.selectedPayment(orderPaymentFinal);
                    break;
                } else {
                    return;
                }
            case R.id.btn_payment_save:
                if(mEditTextUserPaymentName.getText().toString().matches("") ||
                        mEtUserPaymentNumber1.getText().toString().matches("")||
                        mEtUserPaymentNumber2.getText().toString().matches("")||
                        mEtUserPaymentNumber3.getText().toString().matches("")||
                        mEtUserPaymentNumber4.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(),"모두 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if(mUserPaymentSelected != null) {
                        removePayment(position);
                        addPayment(mUserPaymentSelected.getDefault());
                    } else if(mUserPaymentList.size() >= 3) {
                        Toast.makeText(view.getContext(),"최대 3개까지 입력 가능합니다.", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        addPayment("false"); }
                }
                break;
            case R.id.button_payment_remove:
                removePayment(position);
                break;
            case R.id.btn_payment_default:
                if(mEditTextUserPaymentName.getText().toString().matches("") ||
                        mEtUserPaymentNumber1.getText().toString().matches("")||
                        mEtUserPaymentNumber2.getText().toString().matches("")||
                        mEtUserPaymentNumber3.getText().toString().matches("")||
                        mEtUserPaymentNumber4.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(),"모두 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    defaultPayment();
                }
                break;
        }
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    private void getInput() {
        mUserPaymentName = mEditTextUserPaymentName.getText().toString();
        mUserPaymentNumber1 = Integer.parseInt(String.valueOf(mEtUserPaymentNumber1.getText()));
        mUserPaymentNumber2 = Integer.parseInt(String.valueOf(mEtUserPaymentNumber2.getText()));
        mUserPaymentNumber3 = Integer.parseInt(String.valueOf(mEtUserPaymentNumber3.getText()));
        mUserPaymentNumber4 = Integer.parseInt(String.valueOf(mEtUserPaymentNumber4.getText()));
        mUserExpirationMonth = mNumberPickerMonth.getValue();
        mUserExpirationYear = mNumberPickerYear.getValue();
    }

    private void getVerifications() {
        mUserPaymentPassword = Integer.parseInt(String.valueOf(mEtUserPaymentPassword.getText()));
        mUserPaymentVerification = Integer.parseInt(String.valueOf(mEtUserPaymentVerification.getText()));
    }

    private void addPayment(String isDefault) {
        getInput();
        mUserPaymentDefault = isDefault;
        PaymentListModel userPaymentNew = new PaymentListModel(isDefault, mUserPaymentName, mUserPaymentNumber1, mUserPaymentNumber2, mUserPaymentNumber3, mUserPaymentNumber4, mUserExpirationMonth, mUserExpirationYear, -1, -1);
        mOnItemClickedListener.addPayment(userPaymentNew);
    }

    private void removePayment(int position) {
        mOnItemClickedListener.removePayment(position);
    }

    private void defaultPayment() {
        addPayment("true");
        removePayment(position);
        mOnItemClickedListener.defaultPayment();
    }

    private boolean checkInput() {
        if(mEtUserPaymentNumber1.getText().toString().matches("")||
                mEtUserPaymentNumber2.getText().toString().matches("")||
                mEtUserPaymentNumber3.getText().toString().matches("")||
                mEtUserPaymentNumber4.getText().toString().matches("")||
                mEtUserPaymentPassword.getText().toString().matches("")||
                mEtUserPaymentVerification.getText().toString().matches("")
                ) {
            Toast.makeText(getContext(), "모두 입력해주세요", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
}
