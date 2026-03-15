package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class addPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_players);

        final EditText player1 = findViewById(R.id.player1name);
        final EditText player2 = findViewById(R.id.player2name);
        final Button startGameBtn = findViewById(R.id.startgame);
        final Button aboutUsBtn = findViewById(R.id.aboutus); // Reference to About Us button

        // Start Game Button Logic
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getPlayer1name = player1.getText().toString();
                final String getPlayer2name = player2.getText().toString();

                if (getPlayer1name.isEmpty() || getPlayer2name.isEmpty()) {
                    Toast.makeText(addPlayers.this, "Veuillez entrer le nom des joueurs", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(addPlayers.this, MainActivity.class);
                    intent.putExtra("Player1", getPlayer1name); // Corrected key
                    intent.putExtra("Player2", getPlayer2name); // Corrected key
                    startActivity(intent);
                }
            }
        });

        // About Us Button Logic
        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPlayers.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }
}

