package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;

    private int totalSelectedBoxes = 1;

    private LinearLayout player1layout, player2layout;
    private TextView player1name, player2name;

    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1name = findViewById(R.id.player1name);
        player2name = findViewById(R.id.player2name);

        player1layout = findViewById(R.id.player1layout);
        player2layout = findViewById(R.id.player2layout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 4, 8});
        combinationsList.add(new int[]{2, 4, 6});

        Intent intent = getIntent();
        String getPlayer1name = intent.getStringExtra("Player1");
        String getPlayer2name = intent.getStringExtra("Player2");

        // Add defaults if names are null
        player1name.setText(getPlayer1name != null ? getPlayer1name : "Player 1");
        player2name.setText(getPlayer2name != null ? getPlayer2name : "Player 2");

        Log.d("MainActivity", "Player 1: " + player1name.getText().toString());
        Log.d("MainActivity", "Player 2: " + player2name.getText().toString());

        setupClickListeners();
    }

    private void setupClickListeners() {
        View.OnClickListener clickListener = v -> {
            int boxPosition = Integer.parseInt(v.getTag().toString());
            if (isBoxSelectable(boxPosition)) {
                performAction((ImageView) v, boxPosition);
            }
        };

        image1.setTag(0);
        image2.setTag(1);
        image3.setTag(2);
        image4.setTag(3);
        image5.setTag(4);
        image6.setTag(5);
        image7.setTag(6);
        image8.setTag(7);
        image9.setTag(8);

        image1.setOnClickListener(clickListener);
        image2.setOnClickListener(clickListener);
        image3.setOnClickListener(clickListener);
        image4.setOnClickListener(clickListener);
        image5.setOnClickListener(clickListener);
        image6.setOnClickListener(clickListener);
        image7.setOnClickListener(clickListener);
        image8.setOnClickListener(clickListener);
        image9.setOnClickListener(clickListener);
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.x);
            if (checkPlayerWin()) {
                showWinDialog(player1name.getText().toString() + " gagne!");
            } else if (totalSelectedBoxes == 9) {
                showWinDialog("match nul!");
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.circle);
            if (checkPlayerWin()) {
                showWinDialog(player2name.getText().toString() + " gagne !");
            } else if (totalSelectedBoxes == 9) {
                showWinDialog("match nul");
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            player1layout.setBackgroundResource(R.drawable.roundbackblue1000);
            player2layout.setBackgroundResource(R.drawable.roundbackdarkblue);
        } else {
            player2layout.setBackgroundResource(R.drawable.roundbackblue1000);
            player1layout.setBackgroundResource(R.drawable.roundbackdarkblue);
        }
    }

    private boolean checkPlayerWin() {
        for (int[] combination : combinationsList) {
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    private void showWinDialog(String message) {
        winDialogue winDialog = new winDialogue(this, message, this);
        winDialog.setCancelable(false);
        winDialog.show();
    }

    public void restartGame() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBoxes = 1;
        image1.setImageResource(R.drawable.transparent_nshit);
        image2.setImageResource(R.drawable.transparent_nshit);
        image3.setImageResource(R.drawable.transparent_nshit);
        image4.setImageResource(R.drawable.transparent_nshit);
        image5.setImageResource(R.drawable.transparent_nshit);
        image6.setImageResource(R.drawable.transparent_nshit);
        image7.setImageResource(R.drawable.transparent_nshit);
        image8.setImageResource(R.drawable.transparent_nshit);
        image9.setImageResource(R.drawable.transparent_nshit);
    }
}
