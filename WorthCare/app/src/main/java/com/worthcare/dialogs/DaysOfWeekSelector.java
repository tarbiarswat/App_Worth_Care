package com.worthcare.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import com.worthcare.R;
import com.worthcare.utils.DateAndTimeUtil;

import java.util.Arrays;

public class DaysOfWeekSelector extends DialogFragment {

    public interface DaysOfWeekSelectionListener {
        void onDaysOfWeekSelected(boolean[] days);
    }

    DaysOfWeekSelectionListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DaysOfWeekSelectionListener) context;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final boolean[] values = new boolean[7];
        String[] weekDays = DateAndTimeUtil.getWeekDays();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Dialog);
        builder.setMultiChoiceItems(weekDays, values, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                values[which] = isChecked;
            }
        });

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (Arrays.toString(values).contains("true")) {
                    listener.onDaysOfWeekSelected(values);
                }
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}