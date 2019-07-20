package com.example.doantotnghiep.Activity.Controller_CoTuong;


public interface View {

    void postRepaint();

    void drawPiece(int pc, int xx, int yy);

    void drawSelected(int xx, int yy);
}
