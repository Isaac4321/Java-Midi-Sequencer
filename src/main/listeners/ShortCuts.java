package main.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShortCuts implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
