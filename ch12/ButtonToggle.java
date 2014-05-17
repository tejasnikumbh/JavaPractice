import javax.swing.*;
import java.awt.event.*;

class ButtonToggle implements ActionListener{

    JButton button;
    boolean onState = false;
    
    public static void main(String[] args){
        ButtonToggle instance = new ButtonToggle();
        instance.go();
    }
    
    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("I am in OFF State!");
        
        button.addActionListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        
        frame.setSize(300,300);
        frame.setVisible(true);       
    }
    
    public void actionPerformed(ActionEvent event){
        onState = !onState;
        if(onState)
            button.setText("I am in ON State!");
        else
            button.setText("I am in OFF State!");    
    }

}
