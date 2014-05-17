// Importing the Midi Package.
import javax.sound.midi.*;

// Music App Class
public class FirstMusicApp{
    
    public static void main(String[] args){
        FirstMusicApp app = new FirstMusicApp();
        app.play();
    }
    
    public void play(){
        try{        
        
            // We need to open the sequencer, as it is not pre-open.
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            
            // Create a new sequence
            Sequence seq = new Sequence(Sequence.PPQ,4);
            
            // Referencing a track created inside the seq object. The track 
            // lives inside the sequence
            Track track = seq.createTrack();
            
            // Creating some midi events.
            
            //Changing the instrument at channel 1
            ShortMessage firstMsg = new ShortMessage();
            firstMsg.setMessage(192,1,9,0);
            MidiEvent changeInstrument = new MidiEvent(firstMsg,1);
            track.add(changeInstrument);
            
            // Adding a note on event at moment 1
            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,44,100);
            MidiEvent noteOn = new MidiEvent(a,4);
            track.add(noteOn);
            
            // Adding a note off event at moment 16
            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,44,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);
            
            // Setting the sequence of the player. [Putting the cd in player]
            player.setSequence(seq);
            player.start();
            
        }catch(Exception ex){
        
            ex.printStackTrace();
            
        }
    
    }

}//End of Music App Class
