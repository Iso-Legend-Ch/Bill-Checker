package frm;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Frm {
    private static Frm frm;

    private JFrame frame;
    private JTextField textField;

    private List<String> numbers;

    public Frm(String time , List<String> numbers){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        frame.add(new JLabel("對應日期：" + time));
        frame.add(panel);
        panel.add(new JLabel("請輸入發票末三碼："));
        textField = new JTextField();
        panel.add(textField);
        frame.pack();

        frame.setVisible(true);
        this.numbers = numbers;
        frm = this;
        this.check();
    }

    private void check() {
        while(true){
            if(textField.getText() != null){
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING,"[Frm]We have a exception.",e);
                }
                if(textField.getText().length() >= 3){
                    if(numbers.contains(textField.getText())){
                        if(textField.getText().equals(numbers.get(0))){
                            JOptionPane.showMessageDialog(null,"你有可能中了特別獎！特別獎號碼：" + Main.get().getSpecial().get(0),"中獎機會！",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(textField.getText().equals(numbers.get(1))){
                            JOptionPane.showMessageDialog(null,"你有可能中了特獎！特獎號碼：" + Main.get().getSpecial().get(1),"中獎機會！",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"你中獎了！","中獎！",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO,"Checked "+textField.getText());
                    textField.setText("");
                }
            }
        }
    }

    public static Frm get(){
        return frm;
    }
}
