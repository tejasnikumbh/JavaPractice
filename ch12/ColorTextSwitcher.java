import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorTextSwitcher{

    JFrame frame;
    JLabel label;
    
    public static void main(String[] args){
        ColorTextSwitcher applet = new ColorTextSwitcher();
        applet.display();   
    }
    
    public void display(){
        // Creating a new frame
        frame = new JFrame();       
        // Widget Creation
        JButton buttonColor = new JButton("Change Color");
        JButton buttonText = new JButton("Change Text");
        MyDrawPanel drawPanel = new MyDrawPanel();
        label = new JLabel("Current Number : 23");
        // Adding event Listeners
        buttonColor.addActionListener(new ColorEventListener());
        buttonText.addActionListener(new TextEventListener());
        // Adding Widgets to frame ContentPane
        frame.getContentPane().add(BorderLayout.SOUTH,buttonColor);
        frame.getContentPane().add(BorderLayout.EAST,buttonText);
        frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
        frame.getContentPane().add(BorderLayout.WEST,label);
        // Default frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setVisible(true);
    }
    
    class ColorEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            frame.repaint();
        }
    }
    
    class TextEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            int num = (int)(Math.random()*255);
            label.setText("Current Number : " + num);
        }
    }
    
    
}
