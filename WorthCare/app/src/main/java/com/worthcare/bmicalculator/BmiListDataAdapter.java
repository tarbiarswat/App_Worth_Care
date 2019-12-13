package com.worthcare.bmicalculator;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.worthcare.R;

import java.util.List;

public class BmiListDataAdapter extends ArrayAdapter {

    List<BmiDataProvider> mlist;
    FragmentManager fm;
    private Context context;
    private BmiDatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private int pos;
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());

    public BmiListDataAdapter(Context context, int resource, List<BmiDataProvider> list )
    {
        super(context, resource);
        mlist = list;

    }

    static class LayoutHandler{
        TextView date, weight,height;
        Button infoDelete;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View mview = convertView;
        final LayoutHandler layoutHandler;
        if(mview==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mview = layoutInflater.inflate(R.layout.bmi_row_history,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.infoDelete = (Button)mview.findViewById(R.id.info_delete);
            layoutHandler.date = (TextView)mview.findViewById(R.id.tv_date);
            layoutHandler.weight = (TextView)mview.findViewById(R.id.tv_age);
            layoutHandler.height = (TextView)mview.findViewById(R.id.tv_result);

            mview.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler) mview.getTag();
        }

        dbHelper = new BmiDatabaseHelper(getContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        BmiDataProvider bmiDataProvider = (BmiDataProvider)this.getItem(position);

        layoutHandler.infoDelete.setTag(bmiDataProvider.getId());
        layoutHandler.date.setText("Date : "+ bmiDataProvider.getDate());
        layoutHandler.weight.setText("Age : "+String.valueOf(bmiDataProvider.getAge()));
        layoutHandler.height.setText("Result : "+String.valueOf(bmiDataProvider.getResult()));

        layoutHandler.infoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pos = (int)v.getTag();
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        BmiListDataAdapter.this.getContext());
                builder.setTitle("Delete Alert");
                builder.setMessage("Do you want to delete ? ");
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Toast.makeText(getContext(),"No is clicked",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                if(pos!=1){
                                    sqLiteDatabase.execSQL("DELETE FROM BmiPersons where id = "+pos );
                                    Toast.makeText(getContext(),"Delete Success !",Toast.LENGTH_SHORT).show();
//                                    BmiListDataAdapter.this.notifyDataSetChanged();

                                }else {
                                    Toast.makeText(getContext(),"You Can't delete initial data. ",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                builder.show();
            }
        });

        return mview;
    }


}