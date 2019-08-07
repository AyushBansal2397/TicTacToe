package com.example.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Multi_Player extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0, player1points = 0, player2points = 0;
    private TextView player1, player2;
    private String player1_name, player2_name;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi__player);
        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[1][0] = findViewById(R.id.button_10);
        buttons[1][1] = findViewById(R.id.button_11);
        buttons[1][2] = findViewById(R.id.button_12);
        buttons[2][0] = findViewById(R.id.button_20);
        buttons[2][1] = findViewById(R.id.button_21);
        buttons[2][2] = findViewById(R.id.button_22);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        coordinatorLayout = findViewById(R.id.container);
        Intent multi = this.getIntent();
        player1_name = Objects.requireNonNull(multi.getExtras()).getString("Player1_Name") + " : 0";
        player2_name = Objects.requireNonNull(multi.getExtras()).getString("Player2_Name") + " : 0";
        player1.setText(player1_name);
        player2.setText(player2_name);
        player1_name = Objects.requireNonNull(multi.getExtras()).getString("Player1_Name");
        player2_name = Objects.requireNonNull(multi.getExtras()).getString("Player2_Name");
    }

    public void onClick(View V) {
        if (!((Button) V).getText().toString().equals(""))
            return;
        if (player1Turn) {
            ((Button) V).setTextColor(Color.BLUE);
            ((Button) V).setText("X");
        } else {
            ((Button) V).setTextColor(Color.RED);
            ((Button) V).setText("O");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn)
                player1Wins();
            else
                player2Wins();
        } else if (roundCount == 9)
            draw();
        else
            player1Turn = !player1Turn;
    }

    private boolean checkForWin() {
        String[][] f = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                f[i][j] = buttons[i][j].getText().toString();
            }
        }

        String[] output = new String[8];
        output[0] = f[0][0] + f[0][1] + f[0][2];
        output[1] = f[1][0] + f[1][1] + f[1][2];
        output[2] = f[2][0] + f[2][1] + f[2][2];
        output[3] = f[0][0] + f[1][0] + f[2][0];
        output[4] = f[0][1] + f[1][1] + f[2][1];
        output[5] = f[0][2] + f[1][2] + f[2][2];
        output[6] = f[0][0] + f[1][1] + f[2][2];
        output[7] = f[0][2] + f[1][1] + f[2][0];

        for (int i = 0; i < 8; i++) {
            if (output[i].equals("XXX") || output[i].equals("OOO"))
                return true;
        }
        return false;
    }

    private void player2Wins() {
        player2points++;
        Snackbar.make(coordinatorLayout, player2_name + " Wins! \n" + player1_name + " Loses!", Snackbar.LENGTH_LONG).show();
        Update();
        resetBoard(buttons[0][0]);
    }

    private void player1Wins() {
        player1points++;
        Snackbar.make(coordinatorLayout, player1_name + " Wins!\n" + player2_name + " Loses!", Snackbar.LENGTH_LONG).show();
        Update();
        resetBoard(buttons[0][0]);
    }

    private void draw() {
        Snackbar.make(coordinatorLayout, "Draw!!", Snackbar.LENGTH_LONG).show();
        resetBoard(buttons[0][0]);
    }

    public void resetBoard(View V) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
            }
        }
        roundCount = 0;
    }

    public void resetScore(View V) {
        player1points = player2points = 0;
        String msg = player1_name + " : 0";
        player1.setText(msg);
        msg = player2_name + " : 0";
        player2.setText(msg);

    }

    private void Update() {
        String msg = player1_name + " : " + player1points;
        player1.setText(msg);
        msg = player2_name + " : " + player2points;
        player2.setText(msg);
    }
}