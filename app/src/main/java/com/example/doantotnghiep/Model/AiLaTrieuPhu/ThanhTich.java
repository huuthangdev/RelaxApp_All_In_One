package com.example.doantotnghiep.Model.AiLaTrieuPhu;

public class ThanhTich {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private void calculatorPoints(String money, int time) {
        switch (money) {
            case "200.000":
                break;
            case "400.000":
                break;
            case "600.000":
                break;
            case "1.000.000":
                break;
            case "2.000.000":
                break;
            case "3.000.000":
                break;
            case "6.000.000":
                break;
            case "10.000.000":
                break;
            case "14.000.000":
                break;
            case "22.000.000":
                break;
            case "30.000.000":
                break;
            case "40.000.000":
                break;
            case "60.000.000":
                break;
            case "85.000.000":
                break;
            case "150.000.000":
                break;
        }
    }

    public ThanhTich(String money, int time) {
        calculatorPoints(money, time);
    }
}
