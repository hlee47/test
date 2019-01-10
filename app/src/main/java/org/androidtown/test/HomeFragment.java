package org.androidtown.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.androidtown.test.models.CustomerInfoModel;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private CustomerInfoModel customerInfoModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        customerInfoModel = (CustomerInfoModel) getArguments().getSerializable("customerInfoDataList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        Button orderButton = rootView.findViewById(R.id.button_order);
        orderButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        activity.onClick(view);
    }

}
