package com.worthcare.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.worthcare.adapters.ReminderAdapter;
import com.worthcare.adapters.ViewPageAdapter;
import com.worthcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ReminderAdapter.RecyclerListener {

    @BindView(R.id.tabs) PagerSlidingTabStrip pagerSlidingTabStrip;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.fab_button) FloatingActionButton floatingActionButton;

    private boolean fabIsHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        pagerSlidingTabStrip.setViewPager(viewPager);
        int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);

    }

    @OnClick(R.id.fab_button)
    public void fabClicked() {
        Intent intent = new Intent(this, CreateEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void hideFab() {
        floatingActionButton.hide();
        fabIsHidden = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fabIsHidden) {
            floatingActionButton.show();
            fabIsHidden = false;
        }
    }

}