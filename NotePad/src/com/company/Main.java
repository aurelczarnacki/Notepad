package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ex) {
            System.err.println("Failed to initialize style");
        }
        SwingUtilities.invokeLater(Notepad::new);
    }
}

