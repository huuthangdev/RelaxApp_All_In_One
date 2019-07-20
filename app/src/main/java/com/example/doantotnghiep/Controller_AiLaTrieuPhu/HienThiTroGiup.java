package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doantotnghiep.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;



class HienThiTroGiup {

    @SuppressWarnings("deprecation")
    public static class AudienceHelping extends DialogFragment {
        ImageView ia, ib, ic, id;
        ImageView imSupposeAnswer;
        Button dismiss;
        int total = 10;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.nhokhangia_ailatrieuphu, container, false);
            getDialog().setTitle("Audience Joker");
            setInitAudienceHelping(rootView);

            ArrayList<ImageView> buttons = new ArrayList<>();
            buttons.add(ia);
            buttons.add(ib);
            buttons.add(ic);
            buttons.add(id);
            ArrayList<ImageView> xd = new ArrayList<>();
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getTag().equals(PhanCauTraLoiTroGiup.audienceReply)) {
                    imSupposeAnswer = buttons.get(i);
                    Random r = new Random();
                    int Low = 4;
                    int High = 8;
                    int Result = r.nextInt(High - Low) + Low;
                    total = total - Result;
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                            imSupposeAnswer.getLayoutParams();
                    params.weight = (float) Result;
                    imSupposeAnswer.setLayoutParams(params);
                } else {
                    xd.add(buttons.get(i));
                }
            }
            int[] gg = audienceFunction(total); // lấy ra các tỷ lệ được khán giả chọn của các đáp án sai
            for (int i = 0; i < xd.size(); i++) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                        xd.get(i).getLayoutParams();
                params.weight = (float) gg[i];
                xd.get(i).setLayoutParams(params);
            }

            dismiss.setOnClickListener(view -> getDialog().dismiss());
            return rootView;
        }

        void setInitAudienceHelping(View view) {
            dismiss = view.findViewById(R.id.dismiss);
            ia = view.findViewById(R.id.ia);
            ib = view.findViewById(R.id.ib);
            ic = view.findViewById(R.id.ic);
            id = view.findViewById(R.id.id);
        }
    }

    @SuppressWarnings("deprecation")
    public static class TelephoneHelping extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.trogiup_goidien_ailatrieuphu, container, false);
            getDialog().setTitle("Phone Joker");
            Button btnOK = rootView.findViewById(R.id.btn_thankyou);
            TextView phoneAnswer = rootView.findViewById(R.id.tv_telephone_respone);
            phoneAnswer.setText(PhanCauTraLoiTroGiup.phoneResponse);
            btnOK.setOnClickListener(view -> getDialog().dismiss());
            return rootView;
        }
    }

    private static int[] audienceFunction(int number) {
        Random r = new Random();
        HashSet<Integer> uniqueInts = new HashSet<>();
        uniqueInts.add(0);
        uniqueInts.add(number);
        int array_size = 3 + 1;
        while (uniqueInts.size() < array_size) {
            uniqueInts.add(1 + r.nextInt(number - 1));
        }
        Integer[] dividers = uniqueInts.toArray(new Integer[array_size]);
        Arrays.sort(dividers);
        int[] results = new int[3];
        for (int i = 1, j = 0; i < dividers.length; ++i, ++j) {
            results[j] = dividers[i] - dividers[j];
        }
        return results;
    }
}
