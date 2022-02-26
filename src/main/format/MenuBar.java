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
import java.util.ArrayList;
import java.util.HashSet;

public class MenuBar {

    private JFrame parentFrame;

    private JMenuBar menuBar;
    private JMenu play;
    private JMenu record;
    private JMenu help;

    private JMenuItem listenFile;
    private JMenuItem selectFile;
    private JMenuItem stopFile;

    private JMenuItem recordSound;
    private JMenuItem stopSound;
    private JMenuItem nameFile;

    private JMenuItem shortcuts;
    private JMenuItem about;

    private MidiFile file = null;
    private MidiHandler midiHandler = new MidiHandler();

    private static ArrayList<JMenuItem> menuItems = new ArrayList<>();

    public MenuBar(JFrame frame) throws MidiUnavailableException {
        parentFrame = frame;
    }

    public JMenuBar createMenu() {
        menuBar = new JMenuBar();

        play = new JMenu("Play");
        record = new JMenu("Record");
        help = new JMenu("Help");

        listenFile = new JMenuItem("Listen");
        selectFile = new JMenuItem("Select File");
        stopFile = new JMenuItem("Stop Listening");

        recordSound = new JMenuItem("Record");
        stopSound = new JMenuItem("Stop");
        nameFile = new JMenuItem("Name File");

        shortcuts = new JMenuItem("Shortcuts");
        about = new JMenuItem("About");

        menuItems.add(listenFile);
        menuItems.add(selectFile);
        menuItems.add(stopFile);

        menuItems.add(recordSound);
        menuItems.add(stopSound);
        menuItems.add(nameFile);

        menuItems.add(shortcuts);
        menuItems.add(about);


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

        shortcuts.addActionListener(e -> {
            JOptionPane.showMessageDialog(parentFrame, "CRTL + 1: \nListen\nCRTL + 2: \nStop Listening\nCRTL + 3: \nSelect File\nCRTL + 4: \nRecord\nCRTL + 5: \nStop Recording\nCRTL + 6: \nName File");
        });

        play.add(listenFile);
        play.add(stopFile);
        play.add(selectFile);

        record.add(recordSound);
        record.add(stopSound);
        record.add(nameFile);

        help.add(shortcuts);
        help.add(about);

        menuBar.add(play);
        menuBar.add(record);
        menuBar.add(help);

        return menuBar;
    }

    public static ArrayList<JMenuItem> getMenuItems() {
        return menuItems;
    }

}
