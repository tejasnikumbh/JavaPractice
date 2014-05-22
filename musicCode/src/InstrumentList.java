import javax.sound.midi.*;
import javax.swing.*;

class InstrumentList {
    public static void main(String[] args) throws MidiUnavailableException {
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        Instrument[] orchestra = synthesizer.getAvailableInstruments();

        StringBuilder sb = new StringBuilder();
        String eol = System.getProperty("line.separator");
        sb.append(
            "The orchestra has " + 
            orchestra.length + 
            " instruments." + 
            eol);
        for (Instrument instrument : orchestra) {
            sb.append(instrument.toString());
            sb.append(eol);
        }
        synthesizer.close();
        JOptionPane.showMessageDialog(null,
            new JScrollPane(new JTextArea(sb.toString(),20,30)));
    }
}
