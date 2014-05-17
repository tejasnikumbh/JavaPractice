import javax.swing.*;

class SimpleGui{
    public static void main(String[] args){
    
        // Creating swing elements
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me");
        
        // Terminate program on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding elements to pane
        frame.getContentPane().add(button);
        
        //Setting size of frame 
        frame.setSize(300,300);
        
        //Setting visiblity to true.
        frame.setVisible(true);
        
    }
}
