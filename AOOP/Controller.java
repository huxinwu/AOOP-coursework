package com.AOOP;

import java.util.Arrays;

public class Controller {
    private View view;
    private Model model;

    /**
     * 用户输入字符串
     * @param s 用户输入字符
     */
    public void Enter_String(String s){
        if(s.equals("enter")){
            int[] enter = model.Enter();
            if(enter==null){
                view.Error();
                return;
            }

            if(enter.length<2){
                if(enter[0]==7){

                    for (int i=0;i<7;i++){
                        view.Check_TextFiled((model.getIndex()*7)+i,0);
                    }
                    view.Win();
                    return;
                }
                if(enter[0]==6){
                    view.Lose();
                    return;
                }


                    view.Error();

                return;
            }else {

                for (int i=0;i<enter.length;i++){
                        view.Check_TextFiled((model.getIndex()*7)+i,enter[i]);

                }


                if(model.getIndex()==6){
                    view.Lose();
                }
                model.setIndex(model.getIndex()+1);
                view.frequency(6-model.getIndex());
            }
        }else if(s.equals("bank")){
            model.Bank();
        }else {
            model.Enter_String(s);
        }

        view.Update_View(model.getStrings());
    }

    //绑定view
    public void setView(View view) {
        this.view = view;
    }
    //绑定model
    public void setModel(Model model){
        this.model=model;
    }
}
