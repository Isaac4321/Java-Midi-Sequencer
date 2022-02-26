package main.format;

import main.midi.MidiFile;
import main.midi.MidiHandler;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class MenuBar {

    private JFrame parentFrame;

    private JMenuBar menuBar;
    private JMenu play;
    private JMenu record;

    private JMenuItem listenFile;
    private JMenuItem selectFile;
    private JMenuItem stopFile;

    private JMenuItem recordSound;
    private JMenuItem stopSound;
    private JMenuItem nameFile;

    private MidiFile file = null;
    private MidiHandler midiHandler = new MidiHandler();



    public MenuBar(JFrame frame) throws MidiUnavailableException {
        parentFrame = frame;
    }

    public JMenuBar createMenu() {
        menuBar = new JMenuBar();

        play = new JMenu("Play");
        record = new JMenu("Record");

        listenFile = new JMenuItem("Listen");
        selectFile = new JMenuItem("Select File");
        stopFile = new JMenuItem("Stop Listening");

        recordSound = new JMenuItem("Record");
        stopSound = new JMenuItem("Stop");
        nameFile = new JMenuItem("Name File");

        //Event Listening
        listenFile.addActionListener(e -> {
            if (file != null) {
                file.getSequencer().start();
            }
            else {
                JOptionPane.showMessageDialog(parentFrame, "Please select a file to be played");
            }
        });

        selectFile.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(new FileNameExtensionFilter("Midi Files", "mid"));
            String path;
            int x = chooser.showSaveDialog(null);

            if (x == JFileChooser.APPROVE_OPTION) {
                path = (chooser.getSelectedFile().getAbsolutePath());
                try {
                    file = new MidiFile(new File(path));
                } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stopFile.addActionListener(e -> {
            if (file != null && file.getSequencer().isRunning()) {
                file.getSequencer().stop();
            }
            else {
                JOptionPane.showMessageDialog(parentFrame, "Currently no file is being played");
            }

        });

        recordSound.addActionListener(e -> {
            if (!midiHandler.getSequencer().isRecording()) {
                try {
                    midiHandler.record();
                } catch (MidiUnavailableException | InvalidMidiDataException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(parentFrame, "Currently you are already recording, stop the recording then try again");
            }
        });

        stopSound.addActionListener(e -> {
            if (midiHandler.getSequencer().isOpen() && midiHandler.getSequencer().isRecording() && !midiHandler.hasName()) {
                try {
                    midiHandler.stopRecord();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(parentFrame, "Currently you are not recording or you have not named your file");
            }
        });

        nameFile.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(parentFrame, "Please enter in the file name");
            midiHandler.setFileName(name);
        });

        play.add(listenFile);
        play.add(stopFile);
        play.add(selectFile);

        record.add(recordSound);
        record.add(stopSound);
        record.add(nameFile);

        menuBar.add(play);
        menuBar.add(record);

        return menuBar;
    }

}
