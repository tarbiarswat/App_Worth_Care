package com.worthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.worthcare.ultravoicecompanion.UltraMainActivity;

public class ProfileActivity extends AppCompatActivity {
    SpaceNavigationView navigationview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navigationview = findViewById(R.id.space);
        navigationview.initWithSaveInstanceState(savedInstanceState);

        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_settings));

        navigationview.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                startActivity(new Intent(ProfileActivity.this, NavDrawer.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(ProfileActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(ProfileActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
