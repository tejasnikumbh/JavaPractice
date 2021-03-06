// Notes : 
// JFrame object has a default layout of BorderLayout
// JPanel object has a default layout of FlowLayout
// Other layouts include : 
// 1. BoxLayout(Stacked vertically or Horizontally)
// 2. GridLayout(Cells constituting a grid) 
// 3. ... ... ...
// .. ... ... ...
// .. ... ... ...
// N. ... ... ...

// Import Necessary Libraries
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.midi.*;

public class BeatBox{

    // Instance Variables
    
    // I-Vars for the GUI Part
    private JFrame frame;
    private JPanel checkBoxPanel;
    private ArrayList<JCheckBox> checkBoxList;
    
    // I-Vars for the Sound Part
    private Sequencer sequencer;
    private Sequence seq;
    private Track track;
    private String[] instrumentNames = {
        "Bass Drum ", "Open Hi-Hat ", "Close Hi-Hat ", "Acoustic Snare ", 
        "Crash Cymbal " , "Hand Clap ", "High Tom ", "Hi Bongo ", "Maracas ",
        "Low Conga ", "Cow bell ", "Vibraslap ", "Low Mid Tom", "Hi Agogo", 
        "Open Hi Conga "};
    
    private int[] instruments = {
        35,46,42,38,49,39,50,60,70,72,64,56,58,47,67,63};     
        
    // Methods    
    //   The Main Methods
    public static void main(String[] args){
        BeatBox applet = new BeatBox();
        applet.run();
    }

    //   Public Methods
    //     Getter Setter Methods
    //     Static/Utility Public Methods
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

    //   Private Methods
    //     The Private Helper Methods  
        
    // The applet run Method
    // This methods primarily builds the GUI. So we use the GUI I-Vars mostly  
    private void run(){
      
        // Create the Outer GUI frame
        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creating a JPanel and assigning Border Layout as default, instead of
        // the default Flow Layout.
        BorderLayout layout = new BorderLayout();
        JPanel mainPanel = new JPanel(layout);
        // Purely Aesthetic, creating 10 padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        // Initializing the checkBox array list I-Var
        checkBoxList = new ArrayList<JCheckBox>();
        
        // Creating the Midi Button Box
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);
               
        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);
        
        JButton upTempo = new JButton("Up Tempo");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);
        
        JButton downTempo = new JButton("Down Tempo");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);
        
        JButton savePat = new JButton("Save Pattern");
        savePat.addActionListener(new MySavePatternListener());
        buttonBox.add(savePat);
        
        JButton restorePat = new JButton("Restore Pattern");
        restorePat.addActionListener(new MyRestorePatternListener());
        buttonBox.add(restorePat);
        // Creating the Box for Labels
        Box labelBox = new Box(BoxLayout.Y_AXIS);
        for(String instrumentStr : instrumentNames){
            Label label = new Label(instrumentStr);
            labelBox.add(label);
        }       
       
        // Adding GUI elements to the Frame
        mainPanel.add(BorderLayout.EAST,buttonBox);
        mainPanel.add(BorderLayout.WEST,labelBox);
        
        frame.getContentPane().add(mainPanel);
        
        // Creating the Grid Layout for the CheckBoxes
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        checkBoxPanel = new JPanel(grid);
        mainPanel.add(BorderLayout.CENTER,checkBoxPanel);
        
        // Populating the checkBoxPanel
        for(int i=0;i<256;i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            checkBoxPanel.add(c);
        }
        
        setUpMidi();
        
        frame.setBounds(50,50,300,300);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void setUpMidi(){
        try{
            // Sequencer settings
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setTempoInBPM(120);
            // Sequence and track settings
            seq = new Sequence(Sequence.PPQ,4);
            track = seq.createTrack();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void buildTrackAndStart(){
        // Note for all 16 time instants of one instrument
        int[] trackList = null;
        // Clear the previous track
        seq.deleteTrack(track);
        track = seq.createTrack();
        // Looping over all 16 instruments
        for(int i = 0;i < 16;i++){
            trackList = new int[16];
            int key = instruments[i];
            // Doing this for each beat
            for(int j = 0;j < 16;j++){
                JCheckBox jc = (JCheckBox)(checkBoxList.get(j + 16*i));
                if(jc.isSelected()){
                    trackList[j] = key;
                }else{
                    trackList[j] = 0;
                }
            }
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }
        
        track.add(makeEvent(192,9,1,0,15));
        
        try{
            sequencer.setSequence(seq);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void makeTracks(int[] list){
        for(int i = 0;i < 16;i++){
            int key = list[i];
            if(key != 0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+2));
            }
        }
    }
    
    
    // Inner Classes
    //   Inner Classes for Event Listeners
    //   Inner Classes for Other Purposes
    private class MyStartListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            buildTrackAndStart();
        }
    }
    
    private class MyStopListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            sequencer.stop();
        }
    }
    
    private class MyUpTempoListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            float curTempo = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(curTempo*1.03));
        }
    }
    
    private class MyDownTempoListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            float curTempo = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(curTempo*0.97));
        }
    }
    
    private class MySavePatternListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            boolean[] checkBoxState = new boolean[256];
            for(int i = 0;i < 256;i++){
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if(check.isSelected())
                    checkBoxState[i] = true;
                else
                    checkBoxState[i] = false;
            }
            try{
                // Allowing to Choose File
                JFileChooser fileSave = new JFileChooser();
                fileSave.showSaveDialog(frame);
                File saveFile = fileSave.getSelectedFile();
                // Writing pattern to file
                FileOutputStream fos = new FileOutputStream(saveFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(checkBoxState);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    private class MyRestorePatternListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            boolean[] checkBoxState = null;
            try{
                // Allowing to restore file
                JFileChooser fileRestore = new JFileChooser();
                fileRestore.showOpenDialog(fileRestore);
                File restoreFile = fileRestore.getSelectedFile();
                // Reading pattern from restored file
                FileInputStream fis = new FileInputStream(restoreFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                checkBoxState = (boolean[])ois.readObject();
            }catch(Exception ex){
                ex.printStackTrace();
            }    
            
            for(int i = 0;i < 256 ;i++){
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if(checkBoxState[i])
                    check.setSelected(true);
                else
                    check.setSelected(false);
            }
            sequencer.stop();
            buildTrackAndStart();
        }
    }
}
