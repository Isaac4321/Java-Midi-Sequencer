package main.midi;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiFile {

    private final Sequencer sequencer = MidiSystem.getSequencer();

    public MidiFile(File file) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        Sequence sequence = MidiSystem.getSequence(file);
        sequencer.setSequence(sequence);
        sequencer.open();
    }

    public Sequencer getSequencer() {
        return sequencer;
    }


}
