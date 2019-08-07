package com.example.tictactoe;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button SingleButton, MultiButton;
    TextInputLayout Player1, Player2;
    LinearLayout linearLayout;
    Bundle B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SingleButton = findViewById(R.id.single);
        MultiButton = findViewById(R.id.multi);
        B = savedInstanceState;
        Player1 = findViewById(R.id.player1);
        Player2 = findViewById(R.id.player2);
        linearLayout = findViewById(R.id.linear);
    }

    public void Multi_Player(View view) {
        SingleButton.setVisibility(View.GONE);
        MultiButton.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        Player2.setVisibility(View.VISIBLE);
    }

    public void Single_Player(View view) {
        SingleButton.setVisibility(View.GONE);
        MultiButton.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        Player2.setVisibility(View.GONE);
    }

    public void Continue(View view) {
        if (Player2.getVisibility() == View.VISIBLE) {
            String Player1_name = Objects.requireNonNull(Player1.getEditText()).getText().toString().trim();
            String Player2_name = Objects.requireNonNull(Player2.getEditText()).getText().toString().trim();
            if (Player1_name.isEmpty())
                Player1.setError("Field can't be empty!!");
            else
                Player1.setError(null);
            if (Player2_name.isEmpty())
                Player2.setError("Field can't be empty!!");
            else
                Player2.setError(null);

            if( !Player1_name.isEmpty() && !Player2_name.isEmpty() ){
                Intent multi = new Intent(MainActivity.this, Multi_Player.class);
                multi.putExtra("Player1_Name", Player1_name);
                multi.putExtra("Player2_Name", Player2_name);
                startActivity(multi);
            }
        }
        else {
            String Player1_name = Objects.requireNonNull(Player1.getEditText()).getText().toString().trim();
            if (Player1_name.isEmpty()) {
                Player1.setError("Field can't be empty!!");
            } else {
                Player1.setError(null);
                Intent single = new Intent(MainActivity.this, Single_Player.class);
                single.putExtra("Player1_Name", Player1_name);
                startActivity(single);
            }
        }
    }
    public void Switch_Game(View V){
        SingleButton.setVisibility(View.VISIBLE);
        MultiButton.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

}
