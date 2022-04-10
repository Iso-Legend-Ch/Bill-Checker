package frm;

import javax.swing.*;
import java.awt.*;

public class Connecting {
    private static Connecting connecting = null;

    private JFrame frame;

    private Connecting(){}

    public static Connecting get(){
        if(connecting == null){
            connecting = new Connecting();
        }
        return connecting;
    }

    public void show(){
        frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));
        frame.add(new JLabel("Connecting..."));
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    public void hide(){
        frame.dispose();
        connecting = null;
    }
}
