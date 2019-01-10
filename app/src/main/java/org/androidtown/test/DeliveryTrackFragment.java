package org.androidtown.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeliveryTrackFragment extends Fragment implements View.OnClickListener {

    private String invoiceNumber;
    private String senderName;
    private String receiverName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_track, container, false);
        Context context = view.getContext();


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
