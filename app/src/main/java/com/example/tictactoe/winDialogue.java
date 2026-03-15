package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class winDialogue extends Dialog {

    private final String message;
    private final MainActivity mainActivity;

    public winDialogue(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_dialog);

        final TextView messageTXT = findViewById(R.id.messgTXT);
        final Button startAgain = findViewById(R.id.startagain);

        messageTXT.setText(message);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.restartGame();
                dismiss();
            }
        });
    }
}
