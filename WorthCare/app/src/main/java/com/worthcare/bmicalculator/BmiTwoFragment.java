package com.worthcare.bmicalculator;



import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlidePolicy;
import com.worthcare.R;

public class BmiTwoFragment extends Fragment implements ISlidePolicy {

    private static final String USER_AGE = "userAge";
    private static final String USER_SEX ="userSex";

    private AppCompatEditText ageEt;
    private AppCompatRadioButton maleRadioButton;
    private AppCompatRadioButton femaleRadioButton;

    private String userAge;
    private String userSex;
//    private Button calculate_btn;

    SharedPreferences pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bmi_two_fragment, container, false);

        //get edit text
        ageEt = (AppCompatEditText) view.findViewById(R.id.age);

        //get radio buttons
        maleRadioButton = (AppCompatRadioButton) view.findViewById(R.id.radioMale);
        femaleRadioButton = (AppCompatRadioButton) view.findViewById(R.id.radioFemale);


        return view;
    }

    @Override
    public boolean isPolicyRespected() {

        // get age value
        userAge = ageEt.getText().toString();
        //check which one and set userSex
        userSex = (maleRadioButton.isChecked()) ? "Male" : (femaleRadioButton.isChecked()) ? "Female" : "";

        // Assign data in Shared Preference
        pref = getActivity().getPreferences(0);
        SharedPreferences.Editor edt = pref.edit();
        edt.putString(USER_AGE, userAge);
        edt.putString(USER_SEX, userSex);
        edt.commit();

        //return if all field set
        return userAge.length() > 0 && (maleRadioButton.isChecked() || femaleRadioButton.isChecked());

    }


    @Override
    public void onUserIllegallyRequestedNextPage() {

        //make toast error if all field not filled
        if(userAge.length() == 0 ){
            Toast.makeText(getContext(), R.string.age_policy_error, Toast.LENGTH_SHORT).show();
        }
        else if(userSex == "") {
            Toast.makeText(getContext(), R.string.gender_policy_error, Toast.LENGTH_SHORT).show();
        }

    }

}
