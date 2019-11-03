package com.studios.androidservice.starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.studios.androidservice.R;
import com.studios.androidservice.client.CategoryActivity;
import com.studios.androidservice.login.LoginActivity;

public class StarterActivity extends AppCompatActivity {

    /*
    Starter stuffs here
    1.check internet
    2.show dialog if no internet present
    3.check for agent or user (button)
     */

    public static String INTENT_TYPE = "INTENT_TYPE";
    public static String CLIENT_TYPE = "CLIENT_TYPE";
    public static String AGENT_TYPE = "AGENT_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        initViewComponents();
    }

    private void initViewComponents() {
        //inits
        Button asAgent = findViewById(R.id.asAgent);
        Button asClient = findViewById(R.id.asClient);
        //
        ImageButton infoAgent = findViewById(R.id.infoAgent);
        ImageButton infoClient = findViewById(R.id.infoClient);

        infoAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(StarterActivity.this)
                        .setTitle("Agent Mode!")
                        .setMessage("Agent mode are for those users who engage in selling their services.\nYou can promote your product and business here.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();

            }
        });

        infoClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(StarterActivity.this)
                        .setTitle("Sorry!")
                        .setMessage("Client mode are for those users who are seeking services.\nYou can choose for variety of services here.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();

            }
        });

        //handlers
        asAgent.setOnClickListener(handleOnClick);
        asClient.setOnClickListener(handleOnClick);
    }

    private View.OnClickListener handleOnClick = v -> {
        switch (v.getId()) {
            case R.id.asAgent:
                Intent intentLoginAgent = new Intent(StarterActivity.this, LoginActivity.class);
                intentLoginAgent.putExtra(INTENT_TYPE,AGENT_TYPE);
                startActivity(intentLoginAgent);
                break;

            case R.id.asClient:
                Intent intentLoginClient = new Intent(StarterActivity.this,LoginActivity.class);
                intentLoginClient.putExtra(INTENT_TYPE,CLIENT_TYPE);
                startActivity(intentLoginClient);
                break;
        }
    };
}
