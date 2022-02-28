package main.listeners;


import main.format.MenuBar;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;

public class ShortCuts implements KeyListener {

    private final HashSet<Integer> keysDown = new HashSet<>();

    public ShortCuts() throws MidiUnavailableException {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysDown.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //CRTL + 1
        if (keysDown.contains(17) && keysDown.contains(49)) {
            keysDown.remove(17);
            keysDown.remove(49);
            MenuBar.getMenuItems().get(0).doClick();
        }

        //CRTL + 2
        if (keysDown.contains(17) && keysDown.contains(50)) {
            keysDown.remove(17);
            keysDown.remove(49);
            MenuBar.getMenuItems().get(2).doClick();
        }

        //CRTL + 3
        if (keysDown.contains(17) && keysDown.contains(51)) {
            keysDown.remove(17);
            keysDown.remove(51);
            MenuBar.getMenuItems().get(1).doClick();
        }

        //CRTL + 4
        if (keysDown.contains(17) && keysDown.contains(52)) {
            keysDown.remove(17);
            keysDown.remove(49);
            MenuBar.getMenuItems().get(3).doClick();
        }

        //CRTL + 5
        if (keysDown.contains(17) && keysDown.contains(53)) {
            keysDown.remove(17);
            keysDown.remove(49);
            MenuBar.getMenuItems().get(4).doClick();
        }

        //CRTL + 6
        if (keysDown.contains(17) && keysDown.contains(54)) {
            keysDown.remove(17);
            keysDown.remove(51);
            MenuBar.getMenuItems().get(5).doClick();
        }

        //Escape
        if (keysDown.contains(27)) {
            String[] options = {"Yes", "No"};
            int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Confirm Exit", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, "No");

            if (result == 0) {
                System.exit(0);
            }
            keysDown.remove(27);
        }
    }
}
