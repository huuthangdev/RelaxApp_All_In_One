package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doantotnghiep.Model.AiLaTrieuPhu.Question;
import com.example.doantotnghiep.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    private int waitQuestion = 2500;
    private int switchNewQuestion = 2500;
    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    private ArrayList<Question> cauhoi;
    @SuppressLint("StaticFieldLeak")
    public static Button a;
    @SuppressLint("StaticFieldLeak")
    public static Button b;
    @SuppressLint("StaticFieldLeak")
    public static Button c;
    @SuppressLint("StaticFieldLeak")
    public static Button d;
    private Button btnWithdraw;
    private Button[] btnMoney;
    private ImageButton ibSpreadTwo;
    private ImageButton ibTelephoneHelp;
    private ImageButton ibAudienceHelp;
    private TextView tvQuesContainer;
    private TextView tvTimeCounter;
    private Question question;
    private int index;
    private int questionAmount = 0;
    static int passingTime = 0;
    static int exitStatus = 0;
    static String moneyWithLose = "0";
    static String moneyWithdraw;
    static Timer timeStreaming = new Timer();
    static CountDownTimer countDownTimer;
    static MediaPlayer mediaPlayer;
    private PhanCauTraLoiTroGiup helpingFunction = new PhanCauTraLoiTroGiup();

    public Button getA() {
        return a;
    }

    public void setA(Button a) {
        GameController.a = a;
    }

    public Button getB() {
        return b;
    }

    public void setB(Button b) {
        GameController.b = b;
    }

    public Button getC() {
        return c;
    }

    public void setC(Button c) {
        GameController.c = c;
    }

    public Button getD() {
        return d;
    }

    public void setD(Button d) {
        GameController.d = d;
    }

    private Button getBtnWithdraw() {
        return btnWithdraw;
    }

    void setBtnWithdraw(Button btnWithdraw) {
        this.btnWithdraw = btnWithdraw;
    }

    private Button[] getBtnMoney() {
        return btnMoney;
    }

    void setBtnMoney(Button[] btnMoney) {
        this.btnMoney = btnMoney;
    }

    private ImageButton getIbSpreadTwo() {
        return ibSpreadTwo;
    }

    void setIbSpreadTwo(ImageButton ibSpreadTwo) {
        this.ibSpreadTwo = ibSpreadTwo;
    }

    private ImageButton getIbTelephoneHelp() {
        return ibTelephoneHelp;
    }

    void setIbTelephoneHelp(ImageButton ibTelephoneHelp) {
        this.ibTelephoneHelp = ibTelephoneHelp;
    }

    private ImageButton getIbAudienceHelp() {
        return ibAudienceHelp;
    }

    void setIbAudienceHelp(ImageButton ibAudienceHelp) {
        this.ibAudienceHelp = ibAudienceHelp;
    }

    private TextView getTvTimeCounter() {
        return tvTimeCounter;
    }

    void setTvTimeCounter(TextView tvTimeCounter) {
        this.tvTimeCounter = tvTimeCounter;
    }

    private int getQuestionAmount() {
        return questionAmount;
    }

    void setQuestionAmount() {
        this.questionAmount = 15;
    }

    GameController(final Activity activity, Button a, Button b, Button c, Button d, TextView tvQuesContainer) {
        GameController.activity = activity;
        GameController.a = a;
        GameController.b = b;
        GameController.c = c;
        GameController.d = d;
        this.tvQuesContainer = tvQuesContainer;
        cauhoi = TaoCauHoi.createEasyQuestions(activity);
        mediaPlayer = MediaPlayer.create(activity, R.raw.game_start);
        SharedPreferences sharedPreferences = activity.getSharedPreferences("sound", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("sound", "").equals("ok")) {
            mediaPlayer.start();
            new Handler().postDelayed(() -> {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(activity, R.raw.display_question);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0.3f, 0.3f);
            }, 2500);
        }
    }

    private CountDownTimer countDown() {
        countDownTimer = new CountDownTimer(24000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                getTvTimeCounter().setText("" + millisUntilFinished / 1000);
                if (getTvTimeCounter().getText().equals("1")) {
                    finishGame();
                }
            }

            public void onFinish() {
            }
        };
        return countDownTimer;
    }

    private void btnGreenRight(final String correctAnswer) {
        final Button btnCorrectAnswer;

        if (a.getTag().equals(correctAnswer)) {
            btnCorrectAnswer = a;
        } else if (b.getTag().equals(correctAnswer)) {
            btnCorrectAnswer = b;
        } else if (c.getTag().equals(correctAnswer)) {
            btnCorrectAnswer = c;
        } else {
            btnCorrectAnswer = d;
        }

        final Handler handler = new Handler();
        handler.postDelayed(() -> btnCorrectAnswer.setBackground(activity.getDrawable(R.drawable.right_answer)), waitQuestion);

    }

    private static void finishGame() {
        FragmentManager fm = activity.getFragmentManager();
        HienThiKetThuc.GameOver dialogFragment = new HienThiKetThuc.GameOver();
        try {
            dialogFragment.show(fm, "Sample Fragment");
        } catch (Exception ignored) {

        }

    }

    private static void finalFinishGame() {
        FragmentManager fm = activity.getFragmentManager();
        HienThiKetThuc.finalGameWin dialogFragment = new HienThiKetThuc.finalGameWin();
        dialogFragment.show(fm, "Sample Fragment");
    }

    void playGame() {
        getBtnWithdraw().setOnClickListener(view -> OutGame());
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                passingTime++;
            }
        };
        timeStreaming = new Timer();
        timeStreaming.scheduleAtFixedRate(t, 1000, 1000);
        int myFirstQuestion = 0;
        askQuestion(myFirstQuestion, new reply() {
            @Override
            public void answer() {

            }

            @Override
            public void falseAnswer() {
                finishGame();
            }
        });
    }

    public interface reply {
        void answer();

        void falseAnswer();
    }

    private String setEarnedMoney(int QQ) {
        String earnedAmountQuestion = "0/0";
        for (int i = 0; i < getBtnMoney().length; i++) {
            if (i == QQ) {
                getBtnMoney()[i].setBackground(activity.getDrawable(R.drawable.money_archived));
            } else if (i == 1 || i == 6 || i == 10) {
                getBtnMoney()[i].setBackground(activity.getDrawable(R.drawable.money_landmark));
            } else {
                getBtnMoney()[i].setBackground(activity.getDrawable(R.drawable.money_normal));
            }
        }

        switch (QQ) {
            case 0:
                earnedAmountQuestion = "200.000";
                moneyWithLose = "200.000";
                break;
            case 1:
                earnedAmountQuestion = "400.000";
                moneyWithLose = "400.000";
                break;
            case 2:
                earnedAmountQuestion = "600.000";
                moneyWithLose = "600.000";
                break;
            case 3:
                earnedAmountQuestion = "1.000.000";
                moneyWithLose = "1.000.000";
                break;
            case 4:
                moneyWithLose = "2.000.000";
                earnedAmountQuestion = "2.000.000";
                break;
            case 5:
                moneyWithLose = "3.000.000";
                earnedAmountQuestion = "3.000.000";
                break;
            case 6:
                moneyWithLose = "6.000.000";
                earnedAmountQuestion = "6.000.000";
                break;
            case 7:
                moneyWithLose = "10.000.000";
                earnedAmountQuestion = "10.000.000";
                break;
            case 8:
                moneyWithLose = "14.000.000";
                earnedAmountQuestion = "14.000.000";
                break;
            case 9:
                moneyWithLose = "22.000.000";
                earnedAmountQuestion = "22.000.000";
                break;
            case 10:
                moneyWithLose = "30.000.000";
                earnedAmountQuestion = "30.000.000";
                break;
            case 11:
                moneyWithLose = "40.000.000";
                earnedAmountQuestion = "40.000.000";
                break;
            case 12:
                moneyWithLose = "60.000.000";
                earnedAmountQuestion = "60.000.000";
                break;
            case 13:
                moneyWithLose = "85.000.000";
                earnedAmountQuestion = "85.000.000";
                break;
            case 14:
                moneyWithLose = "150.000.000";
                earnedAmountQuestion = "150.000.000";
                break;

        }

        return earnedAmountQuestion;
    }

    @SuppressLint("SetTextI18n")
    private void askQuestion(int questionOrder, final reply reply) {
        index = questionOrder;
        question = cauhoi.get(index);
        Map<String, String> answer;
        answer = question.getAnswer();
        helpingFunction.fiftyFiftyHelp(activity, getIbSpreadTwo(), question.getRightAnswer());
        helpingFunction.telephoneHelp(activity, question.getRightAnswer(), getIbTelephoneHelp());
        helpingFunction.audienceHelp(activity, getIbAudienceHelp(), question.getRightAnswer());
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        a.setText("A:  " + answer.get("1"));
        b.setText("B:  " + answer.get("2"));
        c.setText("C:  " + answer.get("3"));
        d.setText("D:  " + answer.get("4"));
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        final CountDownTimer countDown = countDown();
        countDown.start();
        moneyWithdraw = setEarnedMoney(index);
        a.setBackground(activity.getDrawable(R.drawable.answer_normal));
        a.setTextColor(Color.WHITE);
        b.setBackground(activity.getDrawable(R.drawable.answer_normal));
        b.setTextColor(Color.WHITE);
        c.setBackground(activity.getDrawable(R.drawable.answer_normal));
        c.setTextColor(Color.WHITE);
        d.setBackground(activity.getDrawable(R.drawable.answer_normal));
        d.setTextColor(Color.WHITE);
        tvQuesContainer.setText(question.getQuestion());
        a.setOnClickListener(view -> {
            Audio.recallAudio(activity, R.raw.select_answer);
            btnGreenRight(question.getRightAnswer());
            countDown.cancel();
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);
            a.setBackground(activity.getDrawable(R.drawable.question_active_yellow));
            a.setTextColor(Color.BLACK);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (a.getTag().equals(question.getRightAnswer())) {
                    Audio.recallAudio(activity, R.raw.right_answer);
                    a.setBackground(activity.getDrawable(R.drawable.right_answer));
                    a.setTextColor(Color.WHITE);
                    final Handler handler1 = new Handler();
                    handler1.postDelayed(() -> {
                        reply.answer();
                        if (index < (getQuestionAmount() - 1)) {
                            index++;
                            askQuestion(index, new reply() {
                                @Override
                                public void answer() {

                                }

                                @Override
                                public void falseAnswer() {
                                    finishGame();
                                }
                            });
                        } else {
                            finalFinishGame();
                        }
                    }, switchNewQuestion);
                } else {
                    Audio.AudioCall(activity);
                    final Handler handler1 = new Handler();
                    handler1.postDelayed(reply::falseAnswer, switchNewQuestion);
                    a.setBackground(activity.getDrawable(R.drawable.wrong_answer));
                    a.setTextColor(Color.WHITE);
                }
            }, waitQuestion);
        });
        b.setOnClickListener(view -> {
            Audio.recallAudio(activity, R.raw.select_answer);

            btnGreenRight(question.getRightAnswer());
            countDown.cancel();
            b.setEnabled(false);
            a.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);
            b.setBackground(activity.getDrawable(R.drawable.question_active_yellow));
            b.setTextColor(Color.BLACK);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (b.getTag().equals(question.getRightAnswer())) {
                    Audio.recallAudio(activity, R.raw.right_answer);
                    b.setBackground(activity.getDrawable(R.drawable.right_answer));
                    b.setTextColor(Color.WHITE);
                    final Handler handler12 = new Handler();
                    handler12.postDelayed(() -> {
                        reply.answer();
                        if (index < (getQuestionAmount() - 1)) {
                            index++;
                            askQuestion(index, new reply() {
                                @Override
                                public void answer() {

                                }

                                @Override
                                public void falseAnswer() {
                                    finishGame();
                                }
                            });
                        } else {
                            finalFinishGame();
                        }
                    }, switchNewQuestion);
                } else {
                    Audio.AudioCall(activity);

                    final Handler handler12 = new Handler();
                    handler12.postDelayed(reply::falseAnswer, switchNewQuestion);
                    b.setBackground(activity.getDrawable(R.drawable.wrong_answer));
                    b.setTextColor(Color.WHITE);
                }
            }, waitQuestion);
        });
        c.setOnClickListener(view -> {
            Audio.recallAudio(activity, R.raw.select_answer);

            btnGreenRight(question.getRightAnswer());
            countDown.cancel();
            c.setEnabled(false);
            b.setEnabled(false);
            a.setEnabled(false);
            d.setEnabled(false);
            c.setBackground(activity.getDrawable(R.drawable.question_active_yellow));
            c.setTextColor(Color.BLACK);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (c.getTag().equals(question.getRightAnswer())) {
                    Audio.recallAudio(activity, R.raw.right_answer);
                    c.setBackground(activity.getDrawable(R.drawable.right_answer));
                    c.setTextColor(Color.WHITE);
                    final Handler handler13 = new Handler();
                    handler13.postDelayed(() -> {
                        reply.answer();
                        if (index < (getQuestionAmount() - 1)) {
                            index++;
                            askQuestion(index, new reply() {
                                @Override
                                public void answer() {

                                }

                                @Override
                                public void falseAnswer() {
                                    finishGame();
                                }
                            });
                        } else {
                            finalFinishGame();
                        }
                    }, switchNewQuestion);
                } else {
                    Audio.AudioCall(activity);

                    final Handler handler13 = new Handler();
                    handler13.postDelayed(reply::falseAnswer, switchNewQuestion);
                    c.setBackground(activity.getDrawable(R.drawable.wrong_answer));
                    c.setTextColor(Color.WHITE);
                }
            }, waitQuestion);
        });
        d.setOnClickListener(view -> {
            Audio.recallAudio(activity, R.raw.select_answer);
            btnGreenRight(question.getRightAnswer());
            countDown.cancel();
            d.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            a.setEnabled(false);
            d.setBackground(activity.getDrawable(R.drawable.question_active_yellow));
            d.setTextColor(Color.BLACK);
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (d.getTag().equals(question.getRightAnswer())) {
                    Audio.recallAudio(activity, R.raw.right_answer);
                    d.setBackground(activity.getDrawable(R.drawable.right_answer));
                    d.setTextColor(Color.WHITE);
                    final Handler handler14 = new Handler();
                    reply.answer();
                    handler14.postDelayed(() -> {

                        if (index < (getQuestionAmount() - 1)) {
                            index++;
                            askQuestion(index, new reply() {
                                @Override
                                public void answer() {

                                }

                                @Override
                                public void falseAnswer() {
                                    finishGame();
                                }
                            });
                        } else {
                            finalFinishGame();
                        }
                    }, switchNewQuestion);
                } else {
                    Audio.AudioCall(activity);

                    final Handler handler14 = new Handler();
                    handler14.postDelayed(reply::falseAnswer, switchNewQuestion);
                    d.setBackground(activity.getDrawable(R.drawable.wrong_answer));
                    d.setTextColor(Color.WHITE);
                }
            }, waitQuestion);
        });


    }


    private void OutGame() {
        FragmentManager fm = activity.getFragmentManager();
        OutOfGame dialogFragment = new OutOfGame();
        dialogFragment.show(fm, "Sample Fragment");
    }


    @SuppressWarnings("deprecation")
    public static class OutOfGame extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.bocuoc_ailatrieuphu, container, false);
            getDialog().setTitle("Out Game");
            Button btnWithdrawOK = rootView.findViewById(R.id.btn_yesToWithdraw);
            Button btnWithdrawNO = rootView.findViewById(R.id.btn_noToWithDraw);

            btnWithdrawOK.setOnClickListener(view -> {
                exitStatus = 1;
                finishGame();
            });

            btnWithdrawNO.setOnClickListener(view -> dismiss());


            return rootView;
        }
    }
}
