package com.studios.androidservice.client.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.studios.androidservice.R;
import com.studios.androidservice.client.CategoryActivity;
import com.studios.androidservice.client.TermsCondition;

public class HomeFragment extends Fragment {

    public static String CATEGORY_TYPE = "CATEGORY_TYPE";
    public static String CATEGORY_PLUMB = "Plumbing";
    public static String CATEGORY_FOOD = "Food/Catering";
    public static String CATEGORY_TRAVEL = "Travel";
    public static String CATEGORY_MECHANICAL = "Mechanical";
    public static String CATEGORY_PARLOR = "Parlor(Female)";
    public static String CATEGORY_SALOON = "Saloon(Male)";
    public static String CATEGORY_MAKEUP = "Make Up Artist";
    public static String CATEGORY_ELECTRONICS = "Electronics";
    public static String CATEGORY_HOMECARE = "Home Care";
    public static String CATEGORY_MEDICAL = "Medical";
    public static String CATEGORY_TAILOR = "Tailoring";
    public static String CATEGORY_RENTAL = "Rentals";
    public static String CATEGORY_MISC = "Misc";


    public HomeFragment() {
    }

    public static HomeFragment __(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_home,null);

        initViewComponents(view);

        return view;
    }

    private void initViewComponents(View view) {

        ImageButton plumbing = view.findViewById(R.id.category_plumb);
        ImageButton food = view.findViewById(R.id.category_food);
        ImageButton travel = view.findViewById(R.id.category_travel);
        ImageButton parlor = view.findViewById(R.id.category_parlor);
        ImageButton saloon = view.findViewById(R.id.category_saloon);
        ImageButton makeup = view.findViewById(R.id.category_makeup);
        ImageButton mechanical  =view.findViewById(R.id.category_mechanical);
        ImageButton medical  =view.findViewById(R.id.category_medical);
        ImageButton electronics = view.findViewById(R.id.category_electronics);
        ImageButton homecare = view.findViewById(R.id.category_homecare);
        ImageButton tailor = view.findViewById(R.id.category_tailor);
        ImageButton rentals = view.findViewById(R.id.category_rental);
        ImageButton misc = view.findViewById(R.id.category_misc);

        //
        CardView topagentcard = view.findViewById(R.id.topCardAgents);
        topagentcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), TermsCondition.class);
                startActivity(intent);


            }
        });

        plumbing.setOnClickListener(handleCategory);
        food.setOnClickListener(handleCategory);
        travel.setOnClickListener(handleCategory);
        mechanical.setOnClickListener(handleCategory);
        parlor.setOnClickListener(handleCategory);
        saloon.setOnClickListener(handleCategory);
        makeup.setOnClickListener(handleCategory);
        electronics.setOnClickListener(handleCategory);
        homecare.setOnClickListener(handleCategory);
        medical.setOnClickListener(handleCategory);
        tailor.setOnClickListener(handleCategory);
        rentals.setOnClickListener(handleCategory);
        misc.setOnClickListener(handleCategory);

    }

    private View.OnClickListener handleCategory = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentCategory = new Intent(getActivity(), CategoryActivity.class);
            switch (v.getId()){
                case R.id.category_plumb:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_PLUMB);
                    break;
                case R.id.category_food:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_FOOD);
                    break;
                case R.id.category_travel:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_TRAVEL);
                    break;
                case R.id.category_parlor:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_PARLOR);
                    break;
                case R.id.category_saloon:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_SALOON);
                    break;
                case R.id.category_makeup:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_MAKEUP);
                    break;
                case R.id.category_electronics:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_ELECTRONICS);
                    break;
                case R.id.category_medical:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_MEDICAL);
                    break;
                case R.id.category_tailor:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_TAILOR);
                    break;
                case R.id.category_rental:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_RENTAL);
                    break;
                case R.id.category_misc:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_MISC);
                    break;
                case R.id.category_mechanical:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_MECHANICAL);
                    break;
                case R.id.category_homecare:
                    intentCategory.putExtra(CATEGORY_TYPE,CATEGORY_HOMECARE);
                    break;


            }
            startActivity(intentCategory);
        }
    };
}
