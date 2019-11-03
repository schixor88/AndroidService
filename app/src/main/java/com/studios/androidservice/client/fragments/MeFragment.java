package com.studios.androidservice.client.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.studios.androidservice.R;
import com.studios.androidservice.core.UserCore;

public class MeFragment extends Fragment {

    TextView clientArea;
    TextView clientEmail;
    TextView clientName;
    TextView clientPhone;

    public MeFragment() {
    }

    public static MeFragment __(){
        return new MeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_me,null);

        clientArea = view.findViewById(R.id.clientmeLocation);
        clientEmail = view.findViewById(R.id.clientmeEmail);
        clientName = view.findViewById(R.id.clientmeName);
        clientPhone = view.findViewById(R.id.clientmePhone);

        clientArea.setText(UserCore.area);
        clientEmail.setText(UserCore.email);
        clientName.setText(UserCore.username);
        clientPhone.setText(UserCore.phone);

        return view;
    }
}
