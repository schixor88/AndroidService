package com.studios.androidservice.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.studios.androidservice.R;
import com.studios.androidservice.agent.fragments.HomeFragment;
import com.studios.androidservice.agent.fragments.MeFragment;
import com.studios.androidservice.core.UserCore;

public class LandingAgentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_agent);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hi, "+ UserCore.username);;

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe, HomeFragment.__()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_b_home:
                    fragment =HomeFragment.__();
                    break;
                case R.id.nav_b_me:
                    fragment = MeFragment.__();
                    break;
                default:
                    fragment = HomeFragment.__();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,fragment).commit();
            return true;
        }
    };
}
