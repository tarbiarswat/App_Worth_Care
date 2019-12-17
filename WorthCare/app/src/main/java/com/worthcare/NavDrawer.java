package com.worthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.worthcare.activities.MainActivity;
import com.worthcare.ultravoicecompanion.UltraMainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FirebaseAuth mAuth;
    SpaceNavigationView navigationview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdrawer);

        navigationview = findViewById(R.id.space);
        navigationview.initWithSaveInstanceState(savedInstanceState);

        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_heart));
        navigationview.addSpaceItem(new SpaceItem("", R.drawable.ic_settings));

        navigationview.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                startActivity(new Intent(NavDrawer.this, UltraMainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(NavDrawer.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(NavDrawer.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_medicine_reminder:
                startActivity(new Intent(NavDrawer.this, MainActivity.class));

                break;
            case R.id.nav_consultation:
                startActivity(new Intent(NavDrawer.this, UltraMainActivity.class));
                break;
            case R.id.nav_bmi_calculator:
                startActivity(new Intent(NavDrawer.this, BmiMainActivity.class));
                break;
            case R.id.nav_medicine_directory:
                startActivity(new Intent(NavDrawer.this, MedicineSearchActivity.class));
                break;
            case R.id.nav_workout:
                startActivity(new Intent(NavDrawer.this, WorkoutStartActivity.class));
                break;
            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_privacy_policy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PrivacyPolicyFragment()).commit();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                showDialogue("You are signed out...");
                finish();
                startActivity(new Intent(this, LoginActivity.class));

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private void showDialogue(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
