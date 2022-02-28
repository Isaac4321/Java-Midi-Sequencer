package main;

import main.format.MenuBar;
import main.listeners.ShortCuts;
import main.listeners.WindowListeners;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main
{

    public Main() {
    }

    public static void main(String[] args) throws MidiUnavailableException {

        JFrame frame = new JFrame("Java MIDI Sequencer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        frame.addKeyListener(new ShortCuts());
        frame.addWindowListener(new WindowListeners().windowClose());

        frame.setJMenuBar(new MenuBar(frame).createMenu());
        frame.setLayout(new FlowLayout());

        frame.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
