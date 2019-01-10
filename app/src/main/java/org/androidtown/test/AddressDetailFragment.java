package org.androidtown.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import org.androidtown.test.models.AddressListModel;
import org.androidtown.test.models.ProductModel;

import java.util.ArrayList;

public class AddressDetailFragment extends Fragment implements View.OnClickListener {

    private AddressListModel mUserAddressSelected;
    private AddressListModel mOrderAddressFinal;
    private ArrayList<ProductModel> mUserOrderItemList;
    private int position = -1;
    private EditText mEditTextUserName;
    private EditText mEditTextUserAddress;
    private EditText mEditTextUserContact;
    private EditText mEditTextUserEmail;
    private EditText mEditTextUserNote;
    private String mUserName;
    private String mUserAddress;
    private String mUserEmail;
    private String mUserContact;
    private String mUserNote;
    private String mUserAddressDefault;
    private OnItemClickedListener mOnItemClickedListener;
    private int GONE = getView().GONE;
    private int VISIBLE = getView().VISIBLE;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnItemClickedListener = (OnItemClickedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_detail, container, false);
        Button mButtonSave = view.findViewById(R.id.btn_address_save);
        Button mButtonRemove = view.findViewById(R.id.button_address_remove);
        Button mButtonDefault = view.findViewById(R.id.btn_address_default);
        Button mButtonConfirm = view.findViewById(R.id.btn_order_address_confirm);
        TableLayout addressEdit = view.findViewById(R.id.table_layout_address_edit);
        mButtonSave.setOnClickListener(this);
        mButtonRemove.setOnClickListener(this);
        mButtonDefault.setOnClickListener(this);
        mButtonConfirm.setOnClickListener(this);
        mEditTextUserName = view.findViewById(R.id.text_user_name);
        mEditTextUserAddress = view.findViewById(R.id.text_user_address);
        mEditTextUserContact = view.findViewById(R.id.text_user_contact1);
        mEditTextUserEmail = view.findViewById(R.id.text_user_email1);
        mEditTextUserNote = view.findViewById(R.id.text_user_note);

        // Getting arguments of array model from RecyclerViewAdapter
        mUserAddressSelected = (AddressListModel) getArguments().getSerializable("userAddressSelected");

        // Checking to see if the class is called from order address or user address
        mUserOrderItemList = (ArrayList<ProductModel>) getArguments().getSerializable("orderProductList");
        if(mUserOrderItemList == null) {
            // Class is called from User Address fragment
            mButtonConfirm.setVisibility(GONE);
            addressEdit.setVisibility(VISIBLE);
        } else {
            // Class is called from Order Address Fragment
            mButtonConfirm.setVisibility(VISIBLE);
            addressEdit.setVisibility(GONE);
        }

        // Checking to see if its edit or new. In this case, its edit.
        if(mUserAddressSelected != null) {
            position = (int) getArguments().getSerializable("position");
            mEditTextUserName.setText(mUserAddressSelected.getUserName());
            mEditTextUserAddress.setText(mUserAddressSelected.getUserAddress());
            mEditTextUserContact.setText(mUserAddressSelected.getUserContact());
            mEditTextUserEmail.setText(mUserAddressSelected.getUserEmail());
            mEditTextUserNote.setText(mUserAddressSelected.getUserNote()); }
            else {
            mButtonRemove.setClickable(false);
            mButtonDefault.setClickable(false);
            mButtonRemove.getBackground().setAlpha(70);
            mButtonDefault.getBackground().setAlpha(70);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_order_address_confirm:
                    getInput();
                    mOrderAddressFinal = new AddressListModel("false", mUserName, mUserAddress, mUserContact, mUserEmail, mUserNote);
                    mOnItemClickedListener.selectedAddress(mOrderAddressFinal);
                break;
            case R.id.btn_address_save:
                if(mEditTextUserName.getText().toString().matches("") || mEditTextUserAddress.getText().toString().matches("") || mEditTextUserEmail.getText().toString().matches("") || mEditTextUserContact.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(), "모두입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                } else  {
                    if(mUserAddressSelected != null) {
                        removeAddress(position);
                        addAddress(mUserAddressSelected.getDefault());
                    } else { addAddress("false"); }
                }
                break;
            case R.id.button_address_remove:
                removeAddress(position);
                break;
            case R.id.btn_address_default:
                if(mEditTextUserName.getText().toString().matches("") || mEditTextUserAddress.getText().toString().matches("") || mEditTextUserEmail.getText().toString().matches("") || mEditTextUserContact.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(), "모두입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    defaultAddress();
                }
                break;
        }
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

    public void getInput() {
        mUserName = mEditTextUserName.getText().toString();
        mUserAddress = mEditTextUserAddress.getText().toString();
        mUserContact = mEditTextUserContact.getText().toString();
        mUserEmail = mEditTextUserEmail.getText().toString();
        mUserNote = mEditTextUserNote.getText().toString();
    }

    public void addAddress(String isDefault) {
        getInput();
        mUserAddressDefault = isDefault;
        AddressListModel userAddressNew = new AddressListModel(isDefault, mUserName, mUserAddress, mUserContact, mUserEmail, mUserNote);
        mOnItemClickedListener.addAddress(userAddressNew);
    }

    public void removeAddress(int position) {
        mOnItemClickedListener.removeAddress(position);
    }

    public void defaultAddress() {
        addAddress("true");
        removeAddress(position);
        mOnItemClickedListener.defaultAddress();
    }
}
