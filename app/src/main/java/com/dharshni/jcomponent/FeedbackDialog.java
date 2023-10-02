package com.dharshni.jcomponent;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.dharshni.jcomponent.R;

public class FeedbackDialog extends DialogFragment {

    private int position;

    public FeedbackDialog() {
        // Empty constructor required for DialogFragment
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static FeedbackDialog newInstance(int position) {
        FeedbackDialog dialog = new FeedbackDialog();
        dialog.setPosition(position);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.feedback_dialog, null);
        builder.setView(view);

        EditText feedbackEditText = view.findViewById(R.id.feedbackEditText);
        Button submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackEditText.getText().toString().trim();
                if (!feedback.isEmpty()) {
                    FeedbackActivity feedbackActivity = (FeedbackActivity) requireActivity();
                    feedbackActivity.saveFeedbackToFirebase(position, feedback);
                    dismiss();
                } else {
                    feedbackEditText.setError("Feedback cannot be empty");
                }
            }
        });

        return builder.create();
    }
}
