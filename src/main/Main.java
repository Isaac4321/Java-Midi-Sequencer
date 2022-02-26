package main;

import main.format.MenuBar;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class Main
{

    public Main() {
    }

    public static void main(String[] args) throws MidiUnavailableException {

        JFrame frame = new JFrame("Java Sequencer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        frame.setJMenuBar(new MenuBar(frame).createMenu());
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

    }

}
