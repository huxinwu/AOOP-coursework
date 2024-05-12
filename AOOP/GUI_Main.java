package com.AOOP;

import java.util.Locale;

public class GUI_Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);//设置按钮为英文
        Controller controller=new Controller();
      controller.setView(new View(controller));
      controller.setModel(new Model());

    };
}
