package main;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiFile {

    private final Sequencer sequencer = MidiSystem.getSequencer();

    private final Sequencer sequencer1 = MidiSystem.getSequencer();
    private Transmitter transmitter;
    private Receiver receiver;

    public MidiFile(File file) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        Sequence sequence = MidiSystem.getSequence(file);
        sequencer.setSequence(sequence);
        sequencer.open();
    }

    public Sequencer getSequencer() {
        return sequencer;
    }


}
