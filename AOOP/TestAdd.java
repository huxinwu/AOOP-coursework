package com.AOOP;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestAdd {

    Model model;

    @Before
    public void Init(){

        model=new Model();
        model.Update_answer();
        model.Enter_String("2");

    }

    @Test
    public void Test_Add(){

        Model model=new Model();
        model.Enter_String("1");
        Assert.assertEquals(model.getStrings()[0],"1");
    }


    @Test
    public void Test_Bank(){
        int x=0;
        for (int i=0;i<model.getStrings().length;i++){
            if(model.getStrings()[i]==null){
                x=i-1;
                break;
            }
        }

        model.Bank();
        int x1=0;
        for (int i=0;i<model.getStrings().length;i++){
            if(model.getStrings()[i]==null){
                x1=i-1;
                break;
            }
        }

        if(x!=0){
            Assert.assertEquals(x1,x-1);
        }


    }


    @Test
    public void Test_answer(){
        model.Update_answer();
        String a=model.getAnswer();
        model.Update_answer();
        String b=model.getAnswer();
        Assert.assertNotEquals(a,b);
    }
}
