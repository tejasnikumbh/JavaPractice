import javax.sound.midi.*;

class FirstMusicApp_cmdLine{

    public static void main(String[] args){
        FirstMusicApp_cmdLine player = new FirstMusicApp_cmdLine();        
        if(args.length<2){
            System.out.println("Don't forget the arguments for instrument and" + 
                               "note! ");     
        }else{
            int instrument = Integer.parseInt(args[0]);
            int note  = Integer.parseInt(args[1]);
            player.play(instrument,note);
        }
    }// End of main method
    
    public void play(int instrument,int note){
        try{
            //Creating the Sequencer
            Sequencer player = MidiSystem.getSequencer();
            
            // Opening the player is important, because no sound plays if you
            // don't do it.
            player.open();
            
            //Creating a sequence and a track.
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();
            
            // Creating midi events to add to the track(based on args)
            // Default tone : 100
            
            //Changing the instrument at channel 1 to desired instrument
            ShortMessage a = new ShortMessage();
            a.setMessage(192,1,instrument,0); 
            MidiEvent changeInstrument = new MidiEvent(a,1);
            track.add(changeInstrument);
            
            //Making the Note ON
            ShortMessage b = new ShortMessage();
            b.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(b,1);
            track.add(noteOn);
            
            //Making the Note OFF
            ShortMessage c = new ShortMessage();
            c.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(c,5);
            track.add(noteOff);
            
            //Setting the sequence of the player to seq
            player.setSequence(seq);
            player.start();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }// End of play method

}// End of FirstMusicApp_cmdLine class.
