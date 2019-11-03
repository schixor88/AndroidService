package com.studios.androidservice.agent.fragments;

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

    public MeFragment() {
    }

    public static MeFragment __(){
        return new MeFragment();
    }


    private TextView agentBusinessname;
    private TextView agentPhone;
    private TextView agentName;
    private TextView agentEmail;
    private TextView agentCategory;
    private TextView agentHomeService;
    private TextView agentLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agent_me,null);

        agentBusinessname = view.findViewById(R.id.agentmeTitle);
        agentPhone = view.findViewById(R.id.agentmePhone);
        agentName = view.findViewById(R.id.agentmeName);
        agentEmail = view.findViewById(R.id.agentmeEmail);
        agentCategory = view.findViewById(R.id.agentmeCategory);
        agentHomeService = view.findViewById(R.id.agentmeHomeservice);
        agentLocation = view.findViewById(R.id.agentmeLocation);


        agentBusinessname.setText(UserCore.business_name);
        agentPhone.setText(UserCore.phone);
        agentName.setText(UserCore.username);
        agentEmail.setText(UserCore.email);
        agentCategory.setText(UserCore.business_category);
        agentHomeService.setText(UserCore.homeservice);
        agentLocation.setText(UserCore.area);


        return view;
    }
}
