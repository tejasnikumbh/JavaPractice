import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ColorSwitcher{
    
    // Frame where everything is drawn
    JFrame frame;
    int x;
    int y;
    
    public static void main(String[] args){
        ColorSwitcher applet = new ColorSwitcher();
        applet.display();
    }
    
    public void display(){
         
        // Creating a frame
        frame = new JFrame();
        
        // Setting some instance variables
        x = 0;
        y = 0;
        
        // Drawing the draw Panel
        MyDrawPanel drawPanel = new MyDrawPanel();
        frame.getContentPane().add(drawPanel);
        
        // Default operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        
        // Animating the MyDrawPanel widget
        for(int i=0;i<500;i++){
            x += 1;
            y += 1;
            drawPanel.repaint();
            
            try{
                Thread.sleep(30);
            }catch(Exception ex){};   
        }
        
    }

    class MyDrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.orange);
            g.fillOval(x,y,100,100);
        }
    }    
}
