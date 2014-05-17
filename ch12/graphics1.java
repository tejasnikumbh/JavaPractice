//Creating the JPanel Custom Widget 
import java.awt.*;
import java.swing.*;

class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(20,50,100,100);
    }
}
