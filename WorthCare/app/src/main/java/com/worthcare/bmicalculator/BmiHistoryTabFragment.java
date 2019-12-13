package com.worthcare.bmicalculator;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.worthcare.R;

import java.util.ArrayList;
import java.util.List;

public class BmiHistoryTabFragment extends Fragment {


    private BmiDatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private List<BmiDataProvider> mBmiList;
    private BmiListDataAdapter mBmiListDataAdapter;
    ListView listView;
    Cursor cursor;

    private int list_id;
    private String list_date;
    private String list_age;
    private Float list_result;


    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;

    public static BmiHistoryTabFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        BmiHistoryTabFragment historyFragment = new BmiHistoryTabFragment();
        historyFragment.setArguments(args);
        return historyFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNo = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.bmi_fragment_history, container, false);

        listView = (ListView) mview.findViewById(R.id.lv_bmi);
        mBmiList = new ArrayList<>();
        dbHelper = new BmiDatabaseHelper(getContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getBmiInformation();
        if (cursor.moveToFirst()){
            do{

                list_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_ID)));
                list_date = cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_DATE));
                list_age = cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_AGE));
                list_result = Float.parseFloat(cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_RESULT)));
                BmiDataProvider bmiDataProvider = new BmiDataProvider(list_id,list_date,list_age,list_result);
                mBmiList.add(bmiDataProvider);
            }while (cursor.moveToNext());

        }

        mBmiListDataAdapter = new BmiListDataAdapter(getContext(),R.layout.bmi_row_history,mBmiList);
        listView.setAdapter(mBmiListDataAdapter);

        return mview;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //updatePersonList();
    }



    @Override
    public void onResume() {
        super.onResume();
        mBmiListDataAdapter.notifyDataSetChanged();
        updatePersonList();
    }


    public void updatePersonList(){
        mBmiList.clear();
        dbHelper = new BmiDatabaseHelper(getContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getBmiInformation();
        if (cursor.moveToFirst()){
            do{
                list_id = cursor.getInt(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_ID));
                list_date = cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_DATE));
                list_age = cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_AGE));
                list_result = Float.parseFloat(cursor.getString(cursor.getColumnIndex(BmiDatabaseHelper.COLUMN_RESULT)));

                BmiDataProvider bmiDataProvider = new BmiDataProvider(list_id,list_date,list_age,list_result);
                mBmiList.add(bmiDataProvider);
            }while (cursor.moveToNext());
        }
        mBmiListDataAdapter.notifyDataSetChanged();
    }



}
