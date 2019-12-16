package com.worthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MedicineSearchActivity extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mMedicinesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_search);



        mMedicinesDatabase = FirebaseDatabase.getInstance().getReference("Medicines");


        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseMedicinesSearch(searchText);

            }
        });

    }

    private void firebaseMedicinesSearch(String searchText) {

        Toast.makeText(MedicineSearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mMedicinesDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medicines, MedicinesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medicines, MedicinesViewHolder>(

                Medicines.class,
                R.layout.list_layout,
                MedicinesViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(MedicinesViewHolder viewHolder, Medicines model, int position) {


                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDescription(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class MedicinesViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public MedicinesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String MedicinesName, String MedicinesMedicines, String MedicinesImage){

            TextView Medicines_name = (TextView) mView.findViewById(R.id.name_text);
            TextView Medicines_Medicines = (TextView) mView.findViewById(R.id.status_text);
            ImageView Medicines_image = (ImageView) mView.findViewById(R.id.profile_image);


            Medicines_name.setText(MedicinesName);
            Medicines_Medicines.setText(MedicinesMedicines);

            Glide.with(ctx).load(MedicinesImage).into(Medicines_image);


        }




    }

}
