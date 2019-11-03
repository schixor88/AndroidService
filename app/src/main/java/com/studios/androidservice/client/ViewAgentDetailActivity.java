package com.studios.androidservice.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.studios.androidservice.R;

public class ViewAgentDetailActivity extends AppCompatActivity {

    TextView name;
    TextView title;
    TextView phone;
    TextView category;
    TextView location;
    TextView time;
    TextView description;

    Button call;
    Button sendSMS;

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agent_detail);

        Bundle bundle =getIntent().getExtras();

        name = findViewById(R.id.detail_name);
        title = findViewById(R.id.detail_title);
        phone = findViewById(R.id.detail_phone);
        category = findViewById(R.id.detail_category);
        location = findViewById(R.id.detail_location);
        time = findViewById(R.id.detail_time);
        description = findViewById(R.id.detail_descirption);
        //
        call = findViewById(R.id.callbusiness);
        sendSMS=findViewById(R.id.smsbusiness);

        name.setText(bundle.getString(CategoryActivity.DETAIL_NAME));
        phone.setText(bundle.getString(CategoryActivity.DETAIL_PHONE));
        title.setText(bundle.getString(CategoryActivity.DETAIL_TITLE));
        time.setText(bundle.getString(CategoryActivity.DETAIL_TIME));
        category.setText(bundle.getString(CategoryActivity.DETAIL_CATEGORY));
        location.setText(bundle.getString(CategoryActivity.DETAIL_LOCATION));
        description.setText(bundle.getString(CategoryActivity.DETAIL_DESCRIPTION));

        phoneNumber = bundle.getString(CategoryActivity.DETAIL_PHONE);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + phoneNumber);

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(ViewAgentDetailActivity.this, s.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNumber, null)));

            }
        });



    }
}
