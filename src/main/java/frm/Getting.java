package frm;

import javax.swing.*;
import java.awt.*;

public class Getting {
    private static Getting getting = null;

    private JFrame frame;

    private Getting(){}

    public static Getting get(){
        if(getting == null){
            getting = new Getting();
        }
        return getting;
    }

    public void show(){
        frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));
        frame.add(new JLabel("Getting..."));
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    public void hide(){
        frame.dispose();
        getting = null;
    }
}
