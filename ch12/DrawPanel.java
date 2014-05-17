// The main Draw Panel class that draws a frame on screen.
import java.awt.*;
import javax.swing.*;

public class DrawPanel{

    public static void main(String[] args){
        DrawPanel panel = new DrawPanel();
        panel.display();
    }
    
    public void display(){
        // Creating a new frame
        JFrame frame = new JFrame();
        // Widget Creation Code
        MyDrawPanel drawPanel = new MyDrawPanel();
        // Adding Widget to the Frame's Content Pane
        frame.getContentPane().add(drawPanel);
        // Setting default parameters and visiblity
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    
}
