package com.studios.androidservice.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studios.androidservice.R;
import com.studios.androidservice.core.UserCore;
import com.studios.androidservice.models.Agent;
import com.studios.androidservice.models.Client;
import com.studios.androidservice.starter.StarterActivity;
import com.studios.androidservice.utils.TextUtils;

public class RegisterActivity extends AppCompatActivity {


    String[] categoryArray = { "Plumbing", "Food/Catering","Travel", "Mechanical", "Parlor(Female)", "Saloon(Male)", "Make Up Artist", "Electronics", "Home Care", "Medical", "Tailoring", "Rentals", "Misc" };
    String[] homeServiceArray = {"Yes", "No"};

    FirebaseDatabase database;
    DatabaseReference table_agent;
    DatabaseReference table_client;

    private String MODE = null;
    private String TYPE = null;

    TextInputLayout registerPhoneLayout;
    TextInputEditText registerPhone;
    TextInputLayout registerPasswordLayout;
    TextInputEditText registerPassword;
    TextInputLayout registerConfirmPasswordLayout;
    TextInputEditText registerConfirmPassword;
    TextInputLayout registerNameLayout;
    TextInputEditText registerName;
    TextInputLayout registerLocationLayout;
    TextInputEditText registerLocation;
    TextInputLayout registerEmailLayout;
    TextInputEditText registerEmail;

    ConstraintLayout constraintLayoutAgent;
    TextInputLayout registerBusinessNameLayout;
    TextInputEditText registerBusinessName;
    TextInputLayout registerCategoryLayout;
    Spinner registerCategory;
    TextInputLayout registerHomeServiceLayout;
    Spinner registerHomeService;
    TextInputLayout registerDescriptionLayout;
    TextInputEditText registerDescription;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        Intent extras = getIntent();
        TYPE = extras.getStringExtra(StarterActivity.INTENT_TYPE);

        if (TYPE.equals(StarterActivity.CLIENT_TYPE)){
            MODE = "CLIENT";
            registerButton.setText("Register as Client");
        }
        else if(TYPE.equals(StarterActivity.AGENT_TYPE)){
            MODE = "AGENT";
            constraintLayoutAgent.setVisibility(View.VISIBLE);
            registerButton.setText("Register as Agent");
        }

        database = FirebaseDatabase.getInstance();
        table_agent = database.getReference("User").child("Agent");
        table_client = database.getReference("User").child("Client");


        registerButton.setOnClickListener(handleRegisterAction);


    }

    private void initViews() {

        registerPhoneLayout = findViewById(R.id.registerPhoneLayout);
        registerPhone = findViewById(R.id.registerPhone);
        registerPasswordLayout = findViewById(R.id.registerPasswordLayout);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPasswordLayout = findViewById(R.id.registerPasswordConfirmLayout);
        registerConfirmPassword = findViewById(R.id.registerPasswordConfirm);
        registerNameLayout = findViewById(R.id.registerNameLayout);
        registerName = findViewById(R.id.registerName);
        registerLocationLayout = findViewById(R.id.registerLocationHolder);
        registerLocation = findViewById(R.id.registerLocation);
        registerEmailLayout = findViewById(R.id.registerEmailHolder);
        registerEmail = findViewById(R.id.registerEmail);
        //
        //
        constraintLayoutAgent = findViewById(R.id.constAgent);
        constraintLayoutAgent.setVisibility(View.GONE);
        //
        //
        registerBusinessNameLayout = findViewById(R.id.registerBusinessLayout);
        registerBusinessName = findViewById(R.id.registerBusiness);
        registerCategoryLayout = findViewById(R.id.registerCategoryLayout);
        registerCategory = findViewById(R.id.registerCategory);
        registerHomeServiceLayout = findViewById(R.id.registerHomeserviceLayout);
        registerHomeService = findViewById(R.id.registerHomeService);
        registerDescriptionLayout = findViewById(R.id.registerDescriptionLayout);
        registerDescription = findViewById(R.id.registerDescription);

        //
        registerButton = findViewById(R.id.register_button);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item, categoryArray);
        ArrayAdapter<String> homeServiceAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item, homeServiceArray);

        registerCategory.setAdapter(categoryAdapter);


        registerHomeService.setAdapter(homeServiceAdapter);




    }

    private View.OnClickListener handleRegisterAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (MODE){
                case "CLIENT":
                    setActionAsClientRegister();
                    break;

                case "AGENT":
                    setActionAsAgentRegister();
                    break;
            }

        }
    };

    private void setActionAsAgentRegister() {

        if (
                        registerPhone.getText().toString().isEmpty() ||
                        registerPassword.getText().toString().isEmpty() ||
                        registerConfirmPassword.getText().toString().isEmpty() ||
                        registerName.getText().toString().isEmpty() ||
                        registerLocation.getText().toString().isEmpty() ||
                        registerEmail.getText().toString().isEmpty() ||
                        //agent stuffs
                        registerBusinessName.getText().toString().isEmpty() ||
//                        registerCategory.getText().toString().isEmpty() ||
                        //registerHomeService.getText().toString().isEmpty() ||
                        registerDescription.getText().toString().isEmpty()
            ){

            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isValidPhone(registerPhone.getText().toString())){
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
        }

        if (!TextUtils.isValidEmailId(registerEmail.getText().toString())){
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!registerPassword.getText().toString().equals(registerConfirmPassword.getText().toString())){

            Toast.makeText(this, "Password do not match!", Toast.LENGTH_SHORT).show();
            return;
        }


        table_agent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(registerPhone.getText().toString()).exists()){
                    Toast.makeText(RegisterActivity.this, "Account is already registered!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Agent agent = new Agent(
                            registerPhone.getText().toString(),
                            registerPassword.getText().toString(),
                            registerName.getText().toString(),
                            registerLocation.getText().toString(),
                            registerEmail.getText().toString(),
                            registerBusinessName.getText().toString(),
                            registerCategory.getSelectedItem().toString(),
                            registerHomeService.getSelectedItem().toString(),
                            registerDescription.getText().toString(),
                            "",""
                    );

                    table_agent.child(registerPhone.getText().toString()).setValue(agent);

                    RegisterActivity.this.finish();

//                    UserCore.phone = registerPhone.getText().toString();
//                    UserCore.username = registerName.getText().toString();
//                    UserCore.area = registerLocation.getText().toString();
//                    UserCore.email = registerEmail.getText().toString();
//                    UserCore.business_name =registerBusinessName.getText().toString();
//                    UserCore.business_category = registerCategory.getSelectedItem().toString();
//                    UserCore.homeservice = registerHomeService.getSelectedItem().toString();
//                    UserCore.description = registerDescription.getText().toString();

                    Toast.makeText(RegisterActivity.this, "Signup success, please login!", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void setActionAsClientRegister() {

        if (
                registerPhone.getText().toString().isEmpty() ||
                        registerPassword.getText().toString().isEmpty() ||
                        registerConfirmPassword.getText().toString().isEmpty() ||
                        registerName.getText().toString().isEmpty() ||
                        registerLocation.getText().toString().isEmpty() ||
                        registerEmail.getText().toString().isEmpty()
        ){

            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isValidPhone(registerPhone.getText().toString())){
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isValidEmailId(registerEmail.getText().toString())){
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!registerPassword.getText().toString().equals(registerConfirmPassword.getText().toString())){

            Toast.makeText(this, "Password do not match!", Toast.LENGTH_SHORT).show();
            return;
        }


        table_client.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(registerPhone.getText().toString()).exists()){
                    Toast.makeText(RegisterActivity.this, "Account is already registered!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Client client = new Client(
                            registerPhone.getText().toString(),
                            registerPassword.getText().toString(),
                            registerName.getText().toString(),
                            registerLocation.getText().toString(),
                            registerEmail.getText().toString(),
                            "",""
                    );

                    table_client.child(registerPhone.getText().toString()).setValue(client);

                    RegisterActivity.this.finish();

//                    UserCore.area = registerLocation.getText().toString();
//                    UserCore.email = registerEmail.getText().toString();
//                    UserCore.username = registerName.getText().toString();
//                    UserCore.phone = registerPhone.getText().toString();


                    Toast.makeText(RegisterActivity.this, "Signup success, please login!", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
