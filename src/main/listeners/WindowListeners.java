package main.listeners;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListeners {

   public WindowAdapter windowClose() {
       return new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent event) {
               String[] options = {"Yes", "No"};
               int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Confirm Exit", JOptionPane.DEFAULT_OPTION,
                       JOptionPane.WARNING_MESSAGE, null, options, "No");

               if (result == 0) {
                   System.exit(0);
               }
           }

       };
   }

}
