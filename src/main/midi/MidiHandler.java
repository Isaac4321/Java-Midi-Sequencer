package main.midi;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiHandler {

    private final Sequencer sequencer = MidiSystem.getSequencer();
    private Transmitter transmitter;
    private Receiver receiver;

    private String fileName;
    private static int fileNumCount = 0;
    public MidiHandler() throws MidiUnavailableException {

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void record() throws MidiUnavailableException, InvalidMidiDataException {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        MidiDevice inputDevice = MidiSystem.getMidiDevice(infos[5]);

        inputDevice.open();

        sequencer.open();

        transmitter = inputDevice.getTransmitter();
        receiver = sequencer.getReceiver();

        transmitter.setReceiver(receiver);

        Sequence sequence = new Sequence(Sequence.PPQ, 24);

        Track track = sequence.createTrack();

        sequencer.setSequence(sequence);
        sequencer.setTickPosition(0);
        sequencer.recordEnable(track, -1);

        sequencer.startRecording();

    }

    public void stopRecord() throws IOException {
        sequencer.stopRecording();

        Sequence sequence = sequencer.getSequence();

        if (new File("src/files/" + fileName).exists()) {
            MidiSystem.write(sequence, 0, new File("src/files/" + fileName + fileNumCount + ".mid"));
            fileNumCount++;
        }
        else {
            MidiSystem.write(sequence, 0, new File("src/files/" + fileName + ".mid"));
        }
    }

    public boolean hasName() {
        return fileName == null;
    }

    public Sequencer getSequencer() {
        return sequencer;
    }
}
