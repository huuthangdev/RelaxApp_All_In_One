package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doantotnghiep.Model.AiLaTrieuPhu.ThanhTich;
import com.example.doantotnghiep.R;

class HienThiKetThuc {

    @SuppressWarnings("deprecation")
    public static class GameOver extends DialogFragment {
        @SuppressLint("SetTextI18n")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.ketthuc_ailatrieuphu, container, false);
            getDialog().setTitle("The amount you earn");
            TextView money = rootView.findViewById(R.id.tv_earnedMoney);
            String winningMoney = "";
            if (GameController.exitStatus == 1)
                winningMoney = GameController.moneyWithdraw;
            else if (GameController.exitStatus == 0)
                winningMoney = GameController.moneyWithLose;
            GameController.countDownTimer.cancel();
            money.setText(winningMoney + " " + getActivity().getResources().getString(R.string.money));

            GameController.mediaPlayer.stop();
            GameController.timeStreaming.cancel();
            new ThanhTich(winningMoney, GameController.passingTime);
            TextView explanation = rootView.findViewById(R.id.description);
            explanation.setText(getActivity().getResources().getString(R.string.game_over));
            this.setCancelable(false);


            Button mainMenu = rootView.findViewById(R.id.btn_goHome);
            mainMenu.setOnClickListener(view -> getActivity().finish());
            return rootView;
        }
    }


    @SuppressWarnings("deprecation")
    public static class finalGameWin extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.ketthuc_ailatrieuphu, container, false);
            getDialog().setTitle("Số tiền bạn đạt được");
            TextView money = rootView.findViewById(R.id.tv_earnedMoney);
            String winningMoney = "1.000.000";
            GameController.countDownTimer.cancel();
            money.setText(String.format("%s %s", winningMoney, getActivity().getResources().getString(R.string.money)));
            GameController.mediaPlayer.stop();
            GameController.timeStreaming.cancel();
            new ThanhTich(winningMoney, GameController.passingTime);
            TextView explanation = rootView.findViewById(R.id.description);
            explanation.setText(getActivity().getResources().getString(R.string.game_over_final));
            this.setCancelable(false);
            Button mainMenu = rootView.findViewById(R.id.btn_goHome);
            mainMenu.setOnClickListener(view -> getActivity().finish());

            return rootView;
        }
    }
}
