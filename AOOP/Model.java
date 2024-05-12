package com.AOOP;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Model {
    private String[] strings=new String[42];
    private int index=0;//行数索引
    private int game_stage=6;//游戏剩余次数
    private String answer="8+2-3=7";
    /**
     * 用户输入字符串
     */
    public void Enter_String(String s){
        for (int i=0;i<strings.length;i++){
            if(index==0){
            if(i<7){
                if(strings[i]==null){
                    strings[i]=s;
                    break;
                }
            }
        }else {
                if(i<(index*7)+7&&i>6){
                    if(strings[i]==null){
                        strings[i]=s;
                        break;
                    }
                }
            }
        }

    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[] Enter(){

        String[] s=new String[7];
        ArrayList<String> strings1 = new ArrayList<>(Arrays.asList(strings).subList(index * 7, (index * 7) + 7));
        for (int i=0;i<strings1.size();i++){
            s[i]=strings1.get(i);
        }

        if(strings1.size()!=7){
            return null;
        }else {

            return Verify(s);

        }


    }
    public void Bank(){
        for (int i=0;i<strings.length;i++){
            if(strings[i]==null){
                if(i-1>-1){
                    if(i<=7+(index*7)&&i>index*7)
                    strings[i-1]=null;
                    break;
                }

            }

        }
      //  System.out.println(Arrays.toString(strings));
    }
    public String[] getStrings() {
        return strings;
    }

    /**
     * 验证用户输入的表达式
     * @return 验证结果
     */
    public int[] Verify(String[] strings){

        //验证表达式是否完整,是否有等号
        boolean is_ok=false;
        for(int i=0;i<strings.length;i++){
            if(strings[i].equals("=")){
                is_ok=true;
                break;
            }
        }

        if(!is_ok){

            return    new int[]{-1};
        }

        //验证表达式是否有加减乘除
        is_ok=false;
        for(int i=0;i<strings.length;i++){
            if(strings[i].equals("+")||strings[i].equals("-")||strings[i].equals("*")||strings[i].equals("/")){
                is_ok=true;
                break;
            }
        }

        if(!is_ok){
            return new int[]{-2};
        }
        //验证表达式是否正确
        int[] ints=new int[7];
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        String s="";
        String s1="";
        int i1=0;
        for(int i=0;i<strings.length;i++){
            if(strings[i].equals("=")){
                i1=i;
                break;
            }else {
                s=s+strings[i];
            }
        }

        for(int i=i1+1;i<strings.length;i++){
            s1=s1+strings[i];

        }

        try {


            double eval = Double.parseDouble( engine.eval(s).toString()) ;
            double eval1 = Double.parseDouble( engine.eval(s1).toString()) ;
            if(eval==eval1){
                char[] chars = answer.toCharArray();
                char[] chars1 = (s + "=" + s1).toCharArray();
                for(int i=0;i<chars.length;i++){
                    if(chars[i]==chars1[i]){
                        ints[i]=0;//字符包含且在正确位置上
                    }else {
                        boolean is=false;
                        for(int j=0;j<chars.length;j++){
                            if(chars[j]==chars1[i]){
                                ints[i]=1;//包含但是不在正确位置上
                                is=false;
                                break;
                            }else {
                                is=true;
                            }
                        }

                        if(is){
                            ints[i]=2;//不包含这个数字
                        }
                    }
                }

                boolean is=true;
                for (int i=0;i<ints.length;i++){
                    if(ints[i]==0){

                    }else {
                        is=false;
                        break;
                    }
                }
                if(is){
                    return new int[]{7};
                }else {
                    if(6-(index+1)==0){
                        return new int[]{6};
                    }else {


                    }
                }



            }else {
                return new int[]{-3};
            }
        } catch (ScriptException e) {
            return null;
        }
        return ints;

    }


    public void Update_answer(){
        answer=Read_Txt().get(new Random().nextInt(Read_Txt().size()-1));
    }
    /**
     * 读取公式文件
     * @return
     */
    public ArrayList<String> Read_Txt(){
        ArrayList<String> strings=new ArrayList<>();
        File filename = new File("src/com/AOOP/equations.txt");
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(
                    new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strings.add(line);
        }

        return strings;
    }

    public String getAnswer() {
        return answer;
    }
}
