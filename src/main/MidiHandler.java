package main;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiHandler {

    private final Sequencer sequencer1 = MidiSystem.getSequencer();
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

        sequencer1.open();

        transmitter = inputDevice.getTransmitter();
        receiver = sequencer1.getReceiver();

        transmitter.setReceiver(receiver);

        Sequence sequence = new Sequence(Sequence.PPQ, 24);

        Track track = sequence.createTrack();

        sequencer1.setSequence(sequence);
        sequencer1.setTickPosition(0);
        sequencer1.recordEnable(track, -1);

        sequencer1.startRecording();

    }

    public void stopRecord() throws IOException {
        sequencer1.stopRecording();

        Sequence sequence = sequencer1.getSequence();

        if (new File("src/files/" + fileName).exists()) {
            MidiSystem.write(sequence, 0, new File("src/files/" + fileName + fileNumCount + ".mid"));
            fileNumCount++;
        }
        else {
            MidiSystem.write(sequence, 0, new File("src/files/" + fileName + ".mid"));
        }


    }
}
