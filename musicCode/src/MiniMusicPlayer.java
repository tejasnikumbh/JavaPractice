import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MiniMusicPlayer{

    static JFrame frame = new JFrame("Random Music Video");
    static MyDrawPanel ml;
    
    public static void main(String[] args){
        MiniMusicPlayer applet = new MiniMusicPlayer();
        applet.run();
    }
    
    public void setUpGui(){
        ml = new MyDrawPanel();
        frame.setContentPane(ml);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }
    
    public void run(){
        setUpGui();
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            
            // Adding listeners 
            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(ml,eventsIWant);
            
            // Sequence generator code
            Sequence sequence = new Sequence(Sequence.PPQ,4);
            
           
            Track track = sequence.createTrack();
            for(int i=5;i<=1024;i+=4){
                int r = (int)(Math.random()*50 + 1);
                // Note on 144 - Note ON
                track.add(makeEvent(144,0,r,100,i));
                // Controller event for Note on 176 - Controller
                track.add(makeEvent(176,0,127,0,i));
                // Note off 128 - Note OFF
                track.add(makeEvent(128,0,r,100,i+2));
            }
            
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
           
    }
    
   
    public static MidiEvent makeEvent(int comd,int ch,int one,int two,int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd,ch,one,two);
            event = new MidiEvent(a,tick);    
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return event;
    }
    
    public class MyDrawPanel extends JPanel implements ControllerEventListener{
    
        boolean msg = false;
        
        public void controlChange(ShortMessage ev){
            msg = true;
            
            repaint();
        }
        
        public void paintComponent(Graphics g){
            // Work only if triggered by control change and not window resize etc.
            if(msg){
                // Erase what is already present
                g.setColor(Color.white);
                g.fillRect(0,0,this.getWidth(),this.getHeight());
                
                Graphics2D g2D = (Graphics2D)(g);
                int r = (int)(Math.random()*255);
                int gr = (int)(Math.random()*255);
                int b = (int)(Math.random()*255);
                Color color = new Color(r,gr,b);
                g2D.setColor(color);
                
                int h = (int)((Math.random()*120) + 10);
                int w = (int)((Math.random()*120) + 10);
                int x = (int)((Math.random()*40) + 10);
                int y = (int)((Math.random()*40) + 10);
                
                g.fillRect(h,w,x,y);
                msg = false;          
            }
        }
    }
}
