package com.example.doantotnghiep.Activity.ConSoBiAn;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doantotnghiep.R;

import java.util.Objects;

import static com.example.doantotnghiep.R.id.number_of_attempts;

public class SecretNumActivity extends AppCompatActivity {
    private int giatri = 10;
    private int minVal = 0;
    private int maxVal = 100;
    Toolbar toolbar;
    Button button;

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    private int randomNumber = randomWithRange(minVal, maxVal);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consobian);
        toolbar = findViewById(R.id.toolbarguessinggame);
        button = findViewById(R.id.button);
        button.setOnClickListener(view -> Toast.makeText(SecretNumActivity.this, "Đáp án là " + randomNumber, Toast.LENGTH_SHORT).show());
        EditText guessField = findViewById(R.id.guess_field);
        guessField.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                gameStart();
                return true;
            }
            return false;
        });
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Con số bí ẩn");
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    public void gameStart() {

        EditText guessField = findViewById(R.id.guess_field);
        @SuppressLint("CutPasteId") TextView numberOfAttempts = findViewById(number_of_attempts);
        boolean hasWon = false;
        int guess;

        try {
            guess = Integer.parseInt(guessField.getText().toString());
        } catch (Exception e) {
            guess = 0;
        }

        String winMessage = this.getString(R.string.winMessage1) + " " + (11 - giatri) + " " + this.getString(R.string.winMessage2) + ".";
        String overMessage = this.getString(R.string.endMessage) + " " + randomNumber + ".";
        String higherMessage = this.getString(R.string.higherMessage) + " " + guess + ".";
        String lowerMessage = this.getString(R.string.lowerMessage) + " " + guess + ".";
        String invalidMessage = this.getString(R.string.invalidMessage) + "!";


        if (giatri > 0) {
            int minVal = 0;
            int maxVal = 100;
            if (guess > maxVal || guess < minVal) {
                Toast.makeText(this, invalidMessage, Toast.LENGTH_SHORT).show();
                guessField.getText().clear();
                guessField.requestFocus();
            }

            if ((randomNumber < guess) && (guess <= maxVal && guess >= minVal)) {
                Toast.makeText(this, lowerMessage, Toast.LENGTH_SHORT).show();
                giatri--;
                String kontrolniBroj = Integer.toString(giatri);
                numberOfAttempts.setText(kontrolniBroj);
                guessField.getText().clear();
                guessField.requestFocus();
            }

            if ((randomNumber > guess) && (guess <= maxVal && guess >= minVal)) {
                Toast.makeText(this, higherMessage, Toast.LENGTH_SHORT).show();
                giatri--;
                String kontrolniBroj = Integer.toString(giatri);
                numberOfAttempts.setText(kontrolniBroj);
                guessField.getText().clear();
                guessField.requestFocus();
            }

            if (randomNumber == guess) {
                hasWon = true;
            }

        }

        if (hasWon) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.consobian_end);
            TextView info = dialog.findViewById(R.id.dialog_info);
            info.setText(winMessage);
            ImageView emoticon = dialog.findViewById(R.id.emoticon);
            emoticon.setImageResource(R.drawable.happy);
            final Button reset = dialog.findViewById(R.id.dugmeReset);
            reset.setText(R.string.next_level);
            dialog.show();
            reset.setOnClickListener(v -> {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            });
        }

        if (giatri == 0) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.consobian_end);
            TextView info = dialog.findViewById(R.id.dialog_info);
            info.setText(overMessage);
            dialog.show();
            final Button reset = dialog.findViewById(R.id.dugmeReset);
            reset.setOnClickListener(v -> {
                dialog.dismiss();
                TextView numberOfAttempts1 = findViewById(number_of_attempts);
                int minVal = 0;
                int maxVal = 100;
                randomNumber = randomWithRange(minVal, maxVal);
                guessField.getText().clear();
                guessField.requestFocus();
                giatri = 10;
                String giatriconlai = Integer.toString(giatri);
                numberOfAttempts1.setText(giatriconlai);
            });
        }
    }
}