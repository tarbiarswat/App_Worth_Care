package com.worthcare.bmicalculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.worthcare.R;

public class BmiIntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setGoBackLock(true);
        showSkipButton(false);

        addSlide(BmiOneFragment.newInstance(R.layout.bmi_one_fragment));
        addSlide(new BmiTwoFragment());
        addSlide(new BmiThreeFragment());
        addSlide(new BmiFourFragment());
        addSlide(new BmiFinalFragment());

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        //do the rest
    }


   
    @Override
    protected void onDestroy() {
        super.onDestroy();
                Intent intent = new Intent(this, BmiMainActivity.class);
                startActivity(intent);
    }
}
