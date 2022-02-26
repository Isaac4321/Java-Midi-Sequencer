package main;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class Main
{

    public Main() throws MidiUnavailableException {
    }

    private static MidiFile file;

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        AtomicReference<String> path = new AtomicReference<>("");

        MidiHandler midiHandler = new MidiHandler();

        JFrame frame = new JFrame("Java Sequencer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JButton record = new JButton("Record");
        JButton stop = new JButton("Stop");
        JButton select = new JButton("Select");
        JButton play = new JButton("Play");
        JButton name = new JButton("Name File");
        TextField textField = new TextField("Enter File Name Here");

        name.addActionListener(e -> {
            midiHandler.setFileName(textField.getText());
        });

        select.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());

            int x = chooser.showSaveDialog(null);

            if (x == JFileChooser.APPROVE_OPTION) {
                path.set(chooser.getSelectedFile().getAbsolutePath());
                try {
                    file = new MidiFile(new File(String.valueOf(path)));
                } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

        play.addActionListener(e -> {
            file.getSequencer().start();
        });

        record.addActionListener(e -> {
            System.out.println("Start");
            try {
                midiHandler.record();
            } catch (MidiUnavailableException | InvalidMidiDataException ex) {
                ex.printStackTrace();
            }
        });

        stop.addActionListener(e -> {
            System.out.println("Stop");
            try {
                midiHandler.stopRecord();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        frame.getContentPane().add(record);
        frame.getContentPane().add(stop);
        frame.getContentPane().add(select);
        frame.getContentPane().add(play);
        frame.getContentPane().add(name);
        frame.getContentPane().add(textField);

        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

    }

}
