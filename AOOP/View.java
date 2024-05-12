package com.AOOP;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class View extends JFrame {
    private JTextField [] textFields;
    private ArrayList<JButton> jButtons=new ArrayList<>();
    private Controller controller;
    /**
     * 初始化页面
     */
    public View(Controller controller){
        this.controller=controller;
        textFields=new JTextField[42];
        int x=280;
        int y=10;

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(6,7,20,20));
        jPanel.setBounds(280,10,630,430);

        for (int i=0;i<textFields.length;i++){
            JTextField textField=new JTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setEditable(false);
            textField.setBounds(x,y,50,50);
            textFields[i]=textField;
            jPanel.add(textField);


        }




        add(jPanel);
        x=20;
        y=480;
        String [] strings={"1","2","3","4","5","6","7","8","9","0","bank","+","-","*","/","=","enter"};

        for (int i=0;i<strings.length;i++){
            JButton jButton=new JButton(strings[i]);
            jButton.addActionListener(e -> {
                controller.Enter_String(jButton.getText());
            });
            jButton.setBounds(x,y,100,50);
            jButton.setBackground(Color.white);
            add(jButton);
            jButtons.add(jButton);
            x=x+120;
            if(i==7){
                x=20;
                y=y+80;

            }

        }


      this.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {

              if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                  controller.Enter_String("bank");
              }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                  controller.Enter_String("enter");
              }
              if(e.getKeyChar()=='*'){
                  controller.Enter_String("*");
              }else  if(e.getKeyChar()=='+'){
                  controller.Enter_String("+");
              }else  if(e.getKeyChar()=='-'){
                  controller.Enter_String("-");
              }else  if(e.getKeyChar()=='/'){
                  controller.Enter_String("/");
              }else if(e.getKeyChar()=='='){
                  controller.Enter_String("=");
              }else if(e.getKeyCode()>=48&&e.getKeyCode()<=57){
                  controller.Enter_String(String.valueOf(e.getKeyChar()));
              }


          }
      });

        this.setFocusable(true);

        setLayout(null);
        setBounds(200,100,1100,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 更新用户输入的字符至界面
     * @param strings
     */
    public void Update_View(String [] strings){
        for (int i=0;i<textFields.length;i++){
            textFields[i].setText("");
        }
        for (int i=0;i<strings.length;i++){
            if(strings[i]!=null){
                textFields[i].setText(strings[i]);
            }
        }
        repaint();
    }


    /**
     * 表达式错误
     */
    public void Error(){
        JOptionPane.showMessageDialog(View.this,"Equality error","error",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 游戏胜利
     */
    public void Win(){
        Object[] options ={ "Restart", "Exit" };
        int m = JOptionPane.showOptionDialog(View.this, "You Win", "Game Over",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Restart(m);
    }

    /**
     * 游戏输对话框
     */
    public void Lose(){
        Object[] options ={ "Restart", "Exit" };
        int m = JOptionPane.showOptionDialog(View.this, "You Lose", "Game Over",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        Restart(m);
    }

    /**
     * 剩余次数提示框
     * @param i 剩余次数
     */
    public void frequency(int i){
        JOptionPane.showMessageDialog(View.this," Error,Residual degree"+i,"error",JOptionPane.ERROR_MESSAGE);

    }
    private void Restart(int m) {
        if (m==0){
            dispose();
            Controller controller=new Controller();
            controller.setView(new View(controller));
            Model model=new Model();
            model.Update_answer();
            controller.setModel(model);
        }else {
            System.exit(0);
        }
    }

    /**
     * 检查输入框，并改变颜色
     * @param index
     * @param stage
     */
    public void Check_TextFiled(int index,int stage){
        if(stage==0){
            textFields[index].setBackground(Color.GREEN);
        }
        if(stage==1){
            textFields[index].setBackground(Color.orange);
        }
        if(stage==2){
            textFields[index].setBackground(Color.gray);
        }

        CheK_Button(textFields[index].getText(),stage);
    }



    /**
     * 检查按钮颜色
     * @param s 哪个按钮
     * @param index 按钮状态
     */
    public void CheK_Button(String s,int index){
        for (int i=0;i<jButtons.size();i++){
            if (s.equals(jButtons.get(i).getText())){
                if(index==0){
                    jButtons.get(i).setBackground(Color.GREEN);
                }
                if(index==1){
                    jButtons.get(i).setBackground(Color.orange);
                }
                if(index==2){
                    jButtons.get(i).setBackground(Color.gray);
                }
            }
        }
    }
}
