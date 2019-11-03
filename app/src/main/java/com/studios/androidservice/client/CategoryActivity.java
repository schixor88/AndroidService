package com.studios.androidservice.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studios.androidservice.R;
import com.studios.androidservice.client.fragments.HomeFragment;
import com.studios.androidservice.models.Agent;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    public static String DETAIL_NAME = "DETAIL_NAME";
    public static String DETAIL_PHONE = "DETAIL_PHONE";
    public static String DETAIL_LOCATION = "DETAIL_LOCATION";
    public static String DETAIL_TITLE = "DETAIL_TITLE";
    public static String DETAIL_CATEGORY = "DETAIL_CATEGORY";
    public static String DETAIL_DESCRIPTION = "DETAIL_DESCRIPTION";
    public static String DETAIL_TIME = "DETAIL_TIME";
    public static String DETAIL_HOMESERVICE = "DETAIL_HOMESERVICE";

    public LinearLayout.LayoutParams params;

    int counter;
    int nonvalue;

    DatabaseReference reference;

    private String EXTRA_CATEGORY;
    private List<Agent> agentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ServiceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent extras = getIntent();
        EXTRA_CATEGORY = extras.getStringExtra(HomeFragment.CATEGORY_TYPE);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child("Agent");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Agent agent = data.getValue(Agent.class);
                    agentList.add(agent);
                }
                adapter = new ServiceListAdapter(agentList);
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoryActivity.this, "OOPS", Toast.LENGTH_SHORT).show();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(EXTRA_CATEGORY);

        initRecyclerView();

    }


    private void initRecyclerView() {
        adapter = new ServiceListAdapter(agentList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder>{

        private List<Agent> mList=new ArrayList<>();

        public ServiceListAdapter(List<Agent> mList) {
            this.mList = agentList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.service_recycler_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            if (!mList.get(position).Category.equals(EXTRA_CATEGORY)) {
                holder.serivce_card.setLayoutParams(holder.params);
                nonvalue ++;
            }
            else {
                holder.service_title.setText(mList.get(position).Business);
                holder.service_name.setText(mList.get(position).Name);
                holder.service_phone.setText(mList.get(position).Phone);
                holder.service_location.setText(mList.get(position).Area);
                holder.serivce_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Toast.makeText(CategoryActivity.this, "see details", Toast.LENGTH_SHORT).show();
                        Intent intentDetail = new Intent(CategoryActivity.this, ViewAgentDetailActivity.class);
                        bundle.putString(DETAIL_NAME, mList.get(position).Name);
                        bundle.putString(DETAIL_PHONE, mList.get(position).Phone);
                        bundle.putString(DETAIL_LOCATION, mList.get(position).Area);
                        bundle.putString(DETAIL_TITLE, mList.get(position).Business);
                        bundle.putString(DETAIL_CATEGORY, mList.get(position).Category);
                        bundle.putString(DETAIL_TIME, "11-12");
                        bundle.putString(DETAIL_DESCRIPTION, mList.get(position).Description);
                        bundle.putString(DETAIL_HOMESERVICE, mList.get(position).HomeService);
                        intentDetail.putExtras(bundle);
                        startActivity(intentDetail);
                    }
                });

            }


        }

        @Override
        public int getItemCount() {
            int size = mList.size();

            if (size==nonvalue){
                new AlertDialog.Builder(CategoryActivity.this)
                        .setTitle("Sorry!")
                        .setMessage("Currently there are no services registered. \n Check again later.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CategoryActivity.this.finish();
                            }
                        })
                        .show();
                return 0;
            }
            else {
                return mList.size();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView service_image;
            TextView service_title;
            TextView service_name;
            TextView service_phone;
            TextView service_location;
            CardView serivce_card;
            public CardView.LayoutParams params;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                service_image = itemView.findViewById(R.id.service_image);
                service_name = itemView.findViewById(R.id.service_name);
                service_title = itemView.findViewById(R.id.service_title);
                service_phone = itemView.findViewById(R.id.service_phone);
                service_location = itemView.findViewById(R.id.service_location);
                serivce_card = itemView.findViewById(R.id.service_card);
                params = new CardView.LayoutParams(0, 0);
            }
        }
    }

}
