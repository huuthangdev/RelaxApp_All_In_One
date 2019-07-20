package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;


public class GameScreenActivity extends AppCompatActivity {

    GameController game;
    Button a, b, c, d;
    Button withdraw;
    ImageButton ibSpreadTwo;
    ImageButton ibTelephoneHelp;
    ImageButton ibAudienceHelp;
    TextView tvQuestion;
    TextView timeCounter;
    Button[] btMoneys = new Button[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_screen_ailatrieuphu);
        setInit();
        game = new GameController(this, a, b, c, d, tvQuestion);
        game.setBtnMoney(btMoneys);
        game.setTvTimeCounter(timeCounter);
        game.setIbSpreadTwo(ibSpreadTwo);
        game.setIbTelephoneHelp(ibTelephoneHelp);
        game.setIbAudienceHelp(ibAudienceHelp);
        game.setBtnWithdraw(withdraw);
        game.setQuestionAmount();
        game.playGame();
    }

    void setInit() {
        a =  findViewById(R.id.a);
        b =  findViewById(R.id.b);
        c =  findViewById(R.id.c);
        d =  findViewById(R.id.d);
        ibAudienceHelp =  findViewById(R.id.ibAudienceHelp);
        ibTelephoneHelp =  findViewById(R.id.ibTelephoneHelp);
        ibSpreadTwo =  findViewById(R.id.ibSpreadTwo);
        tvQuestion =  findViewById(R.id.tvQuesContainer);
        btMoneys[0] =  findViewById(R.id.money1);
        btMoneys[1] =  findViewById(R.id.money2);
        btMoneys[2] =  findViewById(R.id.money3);
        btMoneys[3] =  findViewById(R.id.money4);
        btMoneys[4] =  findViewById(R.id.money5);
        btMoneys[5] =  findViewById(R.id.money6);
        btMoneys[6] =  findViewById(R.id.money7);
        btMoneys[7] =  findViewById(R.id.money8);
        btMoneys[8] =  findViewById(R.id.money9);
        btMoneys[9] = findViewById(R.id.money10);
        btMoneys[10] = findViewById(R.id.money11);
        btMoneys[11] = findViewById(R.id.money12);
        btMoneys[12] = findViewById(R.id.money13);
        btMoneys[13] = findViewById(R.id.money14);
        btMoneys[14] = findViewById(R.id.money15);
        timeCounter = findViewById(R.id.timeCounter);
        withdraw = findViewById(R.id.withdraw);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (GameController.mediaPlayer.isPlaying())
            GameController.mediaPlayer.stop();
        GameController.countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (GameController.mediaPlayer.isPlaying())
            GameController.mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (GameController.mediaPlayer.isPlaying())
            GameController.mediaPlayer.stop();
        GameController.timeStreaming.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (GameController.mediaPlayer.isPlaying())
            GameController.mediaPlayer.stop();
        GameController.timeStreaming.cancel();
    }
}
