package com.example.doantotnghiep.Activity.TicTacToe;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doantotnghiep.R;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class TicTacActivity extends AppCompatActivity implements Serializable {
    int[][] c;
    int i, j;
    Button[][] b;
    AI ai;
    int p_points;
    int ai_points;
    MediaPlayer mediaPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        setBoard();
        Toolbar toolbar = findViewById(R.id.toolbarcaro);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Caro 3x3");
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void setBoard() {
        ai = new AI();
        b = new Button[4][4];
        c = new int[4][4];

        b[1][3] = findViewById(R.id.one);
        b[1][2] = findViewById(R.id.two);
        b[1][1] = findViewById(R.id.three);


        b[2][3] = findViewById(R.id.four);
        b[2][2] = findViewById(R.id.five);
        b[2][1] = findViewById(R.id.six);


        b[3][3] = findViewById(R.id.seven);
        b[3][2] = findViewById(R.id.eight);
        b[3][1] = findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }


        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setText("O");
                b[x][y].setTextColor(Color.GREEN);
                c[x][y] = 0;
                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }


    private class AI {
        void takeTurn() {
            if (c[1][1] == 2 &&
                    ((c[1][2] == 0 && c[1][3] == 0) ||
                            (c[2][2] == 0 && c[3][3] == 0) ||
                            (c[2][1] == 0 && c[3][1] == 0))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 0 && c[3][2] == 0) ||
                            (c[1][1] == 0 && c[1][3] == 0))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 0 && c[1][2] == 0) ||
                            (c[3][1] == 0 && c[2][2] == 0) ||
                            (c[2][3] == 0 && c[3][3] == 0))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 0 && c[2][3] == 0) ||
                            (c[1][1] == 0 && c[3][1] == 0))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 0 && c[3][3] == 0) ||
                            (c[1][2] == 0 && c[3][2] == 0) ||
                            (c[3][1] == 0 && c[1][3] == 0) ||
                            (c[2][1] == 0 && c[2][3] == 0))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[3][3] == 0))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 0 && c[2][1] == 0) ||
                            (c[3][2] == 0 && c[3][3] == 0) ||
                            (c[2][2] == 0 && c[1][3] == 0))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 0 && c[2][2] == 0) ||
                            (c[3][1] == 0 && c[3][3] == 0))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[2][3] == 0) ||
                            (c[3][1] == 0 && c[3][2] == 0))) {
                markSquare(3, 3);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while (a == 0 || b == 0 || c[a][b] != 2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }
                markSquare(a, b);
            }
        }

        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            b[x][y].setText("X");
            b[x][y].setTextColor(Color.RED);
            c[x][y] = 1;
            checkBoard();
        }
    }

    @SuppressLint("SetTextI18n")
    private void P_points() {
        p_points++;
        TextView p_Points = findViewById(R.id.y_counter);
        p_Points.setText("BẠN : " + (p_points));
    }

    @SuppressLint("SetTextI18n")
    private void AI_points() {
        ai_points++;
        TextView ai_Points = findViewById(R.id.b_counter);
        ai_Points.setText("MÁY : " + (ai_points));
    }

    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            P_points();
            Toast.makeText(this, "Bạn THẮNG!", Toast.LENGTH_LONG).show();
            mediaPlayer = MediaPlayer.create(TicTacActivity.this, R.raw.win);
            mediaPlayer.start();
            gameOver = true;
            setBoard();

        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            AI_points();
            Toast.makeText(this, "BẠN THUA!", Toast.LENGTH_LONG).show();
            mediaPlayer = MediaPlayer.create(TicTacActivity.this, R.raw.loss);
            mediaPlayer.start();
            gameOver = true;
            setBoard();
        } else {
            boolean empty = false;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    if (c[i][j] == 2) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                gameOver = true;
                setBoard();
                Toast.makeText(this, "HÒA!", Toast.LENGTH_LONG).show();
                mediaPlayer = MediaPlayer.create(TicTacActivity.this, R.raw.draw);
                mediaPlayer.start();
            }
        }
        return gameOver;
    }
}
