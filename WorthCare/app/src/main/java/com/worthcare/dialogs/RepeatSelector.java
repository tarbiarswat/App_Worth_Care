package com.worthcare.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import com.worthcare.R;
import com.worthcare.models.Reminder;

public class RepeatSelector extends DialogFragment {

    public interface RepeatSelectionListener {
        void onRepeatSelection(DialogFragment dialog, int interval, String repeatText);
    }

    RepeatSelectionListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (RepeatSelectionListener) context;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] repeatArray = getResources().getStringArray(R.array.repeat_array);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Dialog);
        builder.setItems(repeatArray, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == Reminder.SPECIFIC_DAYS) {
                    DialogFragment daysOfWeekDialog = new DaysOfWeekSelector();
                    daysOfWeekDialog.show(getActivity().getSupportFragmentManager(), "DaysOfWeekSelector");
                }  else if (which == Reminder.ADVANCED) {
                    DialogFragment advancedDialog = new AdvancedRepeatSelector();
                    advancedDialog.show(getActivity().getSupportFragmentManager(), "AdvancedSelector");
                } else {
                    listener.onRepeatSelection(RepeatSelector.this, which, repeatArray[which]);
                }
            }
        });
        return builder.create();
    }
}