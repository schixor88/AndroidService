package com.studios.androidservice.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studios.androidservice.R;
import com.studios.androidservice.agent.LandingAgentActivity;
import com.studios.androidservice.client.LandingClientActivity;
import com.studios.androidservice.core.UserCore;
import com.studios.androidservice.models.Agent;
import com.studios.androidservice.models.Client;
import com.studios.androidservice.starter.StarterActivity;
import com.studios.androidservice.utils.TextUtils;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private String MODE = null;
    private String TYPE = null;

    TextInputLayout loginEmailLayout;
    TextInputEditText loginEmail;
    TextInputLayout loginPasswordLayout;
    TextInputEditText loginPassword;

    private String phone,password;

    Button login;
    Button toRegister;

    FirebaseDatabase database;
    DatabaseReference table_agent;
    DatabaseReference table_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //
        database = FirebaseDatabase.getInstance();
        table_agent = database.getReference("User").child("Agent");
        table_client = database.getReference("User").child("Client");
        //
        initViewComponents();

        //
        Intent extras = getIntent();
        TYPE = extras.getStringExtra(StarterActivity.INTENT_TYPE);

        if (TYPE.equals(StarterActivity.CLIENT_TYPE)){
            MODE = "CLIENT";
            login.setText("Login as Client");
            toRegister.setText("Register as Client");
        }
        else if(TYPE.equals(StarterActivity.AGENT_TYPE)){
            MODE = "AGENT";
            login.setText("Login as Agent");
            toRegister.setText("Register as Agent");
        }

        login.setOnClickListener(handleLoginClick);
        toRegister.setOnClickListener(handleToRegisterClick);


    }

    private void initViewComponents() {
        loginEmailLayout = findViewById(R.id.login_email_layout);
        loginEmail = findViewById(R.id.login_email);
        loginPasswordLayout = findViewById(R.id.login_password_layout);
        loginPassword = findViewById(R.id.login_password);

        login = findViewById(R.id.login);
        toRegister = findViewById(R.id.toRegister);

    }

    private View.OnClickListener handleLoginClick = v -> {

        switch (MODE){
            case "CLIENT":
                setActionAsClientLogin();
                break;

            case "AGENT":
                setActionAsAgentLogin();
                break;
        }

    };

    private View.OnClickListener handleToRegisterClick = v -> {
        switch (MODE){
            case "CLIENT":
               Intent intentRegClient = new Intent(LoginActivity.this,RegisterActivity.class);
               intentRegClient.putExtra(StarterActivity.INTENT_TYPE,StarterActivity.CLIENT_TYPE);
               startActivity(intentRegClient);
                break;

            case "AGENT":
                Intent intentRegAgent = new Intent(LoginActivity.this,RegisterActivity.class);
                intentRegAgent.putExtra(StarterActivity.INTENT_TYPE,StarterActivity.AGENT_TYPE);
                startActivity(intentRegAgent);
                break;
        }


    };

    private void setActionAsAgentLogin() {
        Toast.makeText(this, "agent login actions", Toast.LENGTH_SHORT).show();
        phone = loginEmail.getText().toString();
        password =loginPassword.getText().toString();

        if (phone.isEmpty()||password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please fill your credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        // Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        // startActivity(intent);

        firebaseLoginAgentWithEmail(phone,password);


    }

    private void setActionAsClientLogin() {
        Toast.makeText(this, "client login actions", Toast.LENGTH_SHORT).show();
        phone = loginEmail.getText().toString();
        password =loginPassword.getText().toString();

        if (phone.isEmpty()||password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please fill your credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseLoginClientWithEmail(phone,password);
    }

    private void firebaseLoginAgentWithEmail(String phone,String password) {

        table_agent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(phone).exists()) {
                    Agent agent = dataSnapshot.child(phone).getValue(Agent.class);
                    if (agent.Password.equals(password)) {

                        Intent intent = new Intent(LoginActivity.this,LandingAgentActivity.class);
                        intent.putExtra("phone",phone);

                        UserCore.phone = agent.Phone;
                        UserCore.username = agent.Name;
                        UserCore.area = agent.Area;
                        UserCore.email = agent.Email;
                        UserCore.business_name = agent.Business;
                        UserCore.business_category = agent.Category;
                        UserCore.homeservice = agent.HomeService;
                        UserCore.description = agent.Description;



                        startActivity(intent);
                        finish();


                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void firebaseLoginClientWithEmail(String phone,String password){
        table_client.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(phone).exists()){
                    Client client = dataSnapshot.child(phone).getValue(Client.class);
                    if (client.Password.equals(password)){
                        Intent intent = new Intent(LoginActivity.this,LandingClientActivity.class);
                        intent.putExtra("phone",phone);

                        UserCore.phone = client.Phone;
                        UserCore.username = client.Name;
                        UserCore.area = client.Area;
                        UserCore.email = client.Email;

                        startActivity(intent);
                        finish();
                    }
                    else {Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();}
                    }
                else {
                    Toast.makeText(LoginActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
