package com.AOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI_Main {
    public static void main(String[] args) {
        Model model=new Model();
        System.out.println("Start the game:");
        Scanner scanner=new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();

            if(chars.length!=7){
                System.out.println("Equation format error");
            }else {
                for(int i=0;i<chars.length;i++){
                    model.Enter_String(String.valueOf(chars[i]));
                }

                String[] s2=new String[7];
                ArrayList<String> strings1 = new ArrayList<>(Arrays.asList(model.getStrings()).subList(model.getIndex() * 7, (model.getIndex() * 7) + 7));
                for (int i=0;i<strings1.size();i++){
                    s2[i]=strings1.get(i);
                }
                System.out.println(Arrays.toString(s2));
                int[] verify = model.Verify(s2);
                if(verify[0]==-1){
                    System.out.println("Equality error");
                }else  if(verify[0]==-2){
                    System.out.println("Equality error");
                }else  if(verify[0]==-3){
                    System.out.println("Equality error");
                }else if(verify[0]==6) {

                    System.out.println("You Lose");

                    System.exit(0);
                }else if(verify[0]==7) {


                    System.out.println("You Win");

                    System.exit(0);

                }else {
                    if(verify.length==7){
                        for (int i=0;i<verify.length;i++){
                            if(verify[i]==2){
                                System.out.println(model.getStrings()[i]+"(This character is not included)");
                            }else  if(verify[i]==1){
                                System.out.println(model.getStrings()[i]+"(Contains the character but is not in the correct position)");
                            }else  if(verify[i]==0){
                                System.out.println(model.getStrings()[i]+"(The character is correct)");
                            }



                        }
                    }
                    model.setIndex(model.getIndex()+1);
                    System.out.println((6-model.getIndex())+"chances left");

                }


            }
        }
    }
}
