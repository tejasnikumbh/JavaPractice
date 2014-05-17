//Creating the JPanel Custom Widget 
import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics g){
        g.fillRect(0,0,300,300);
         
        // Generating a random gradient
        int red = (int)(Math.random()*255);
        int blue = (int)(Math.random()*255);
        int green = (int)(Math.random()*255);
        
        Color color = new Color(red,green,blue);
        g.setColor(color);
        g.fillOval(0,0,100,100);
    }
}
