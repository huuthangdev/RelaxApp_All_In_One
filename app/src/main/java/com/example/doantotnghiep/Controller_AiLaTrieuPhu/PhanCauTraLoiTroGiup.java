package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.doantotnghiep.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class PhanCauTraLoiTroGiup {

    private static boolean mDouble = true;
    static String audienceReply;
    static String phoneResponse;

    void fiftyFiftyHelp(final Activity activity, final ImageButton spreadTwo, final String correctAnswer) {
        spreadTwo.setOnClickListener(view -> {
            if (mDouble) {
                spreadTwo.setEnabled(false);
                spreadTwo.setImageDrawable(activity.getResources().getDrawable(R.drawable.out_spreading_help));
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(GameController.a);
                buttons.add(GameController.b);
                buttons.add(GameController.c);
                buttons.add(GameController.d);
                ArrayList<Button> btnErasable = new ArrayList<>();
                for (int i = 0; i < buttons.size(); i++) {
                    if (!buttons.get(i).getTag().equals(correctAnswer)) {
                        btnErasable.add(buttons.get(i));
                    }
                }
                Collections.shuffle(btnErasable);
                btnErasable.get(0).setText("");
                btnErasable.get(1).setText("");
            }
        });
    }

    void audienceHelp(final Activity activity, final ImageButton askAudience, String response) {
        audienceReply = response;
        askAudience.setOnClickListener(view -> {
            FragmentManager fm = activity.getFragmentManager();
            HienThiTroGiup.AudienceHelping dialogFragment = new HienThiTroGiup.AudienceHelping();
            dialogFragment.show(fm, "Joker");
            askAudience.setEnabled(false);
            askAudience.setImageDrawable(activity.getResources().getDrawable(R.drawable.out_audience_helping));
        });
    }

    void telephoneHelp(final Activity activity, final String correctAnswer, final ImageButton btnHelping) {
        btnHelping.setOnClickListener(view -> {
            String talk = "....";
            String idea;
            switch (correctAnswer) {
                case "1":
                    idea = "A";
                    break;
                case "2":
                    idea = "B";
                    break;
                case "3":
                    idea = "C";
                    break;
                default:
                    idea = "D";
                    break;
            }
            Random random = new Random();
            int i = random.nextInt(5);
            switch (i) {

                case 0:
                    talk = "Tôi không biết";
                    break;

                case 1:
                    talk = "Chắc chắn" + idea;
                    break;

                case 2:
                    talk = "Có lẽ là " + idea + ".";
                    break;

                case 3:
                    talk = "Tôi chọn " + idea + ".";
                    break;

                case 4:
                    talk = "Tôi chốt " + idea + ".";
                    break;
            }
            phoneResponse = talk;
            FragmentManager fm = activity.getFragmentManager();
            HienThiTroGiup.TelephoneHelping dialogFragment = new HienThiTroGiup.TelephoneHelping();
            dialogFragment.show(fm, "Joker");
            btnHelping.setEnabled(false);
            btnHelping.setImageDrawable(activity.getResources().getDrawable(R.drawable.out_telephone_helping));
        });
    }
}
