import javax.swing.*;
import java.awt.event.*;

class ButtonChanger implements ActionListener{

    JButton button;
    
    public static void main(String[] args){
        ButtonChanger instance = new ButtonChanger();
        instance.go();
    }
    
    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("Click Me!");
        
        button.addActionListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        
        frame.setSize(300,300);
        frame.setVisible(true);       
    }
    
    public void actionPerformed(ActionEvent event){
        button.setText("I have been clicked!");
    }

}
