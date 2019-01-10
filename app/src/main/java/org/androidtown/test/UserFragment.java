package org.androidtown.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class UserFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ImageButton userPayment = view.findViewById(R.id.btn_payment);
        ImageButton userAddress = view.findViewById(R.id.btn_user_address);
        ImageButton userLog = view.findViewById(R.id.btn_log);
        userPayment.setOnClickListener(this);
        userAddress.setOnClickListener(this);
        userLog.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }
}
