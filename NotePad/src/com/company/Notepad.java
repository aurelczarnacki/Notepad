package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    private final JTextArea text;
    private final JMenuItem openFile;
    private final JMenuItem saveFile;

    public Notepad() {
        setTitle("Notepad");

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);

        openFile = new JMenuItem("Open...");
        openFile.addActionListener(this);
        file.add(openFile);

        saveFile = new JMenuItem("Save as...");
        saveFile.addActionListener(this);
        file.add(saveFile);

        text = new JTextArea("");

        setContentPane(new JScrollPane(text));
        setJMenuBar(menuBar);

        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.openFile) {
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                text.setText("");
                try {
                    String line;
                    BufferedReader in = new BufferedReader(new FileReader(open.getSelectedFile().getPath()));
                    while ((line = in.readLine()) != null) {
                        text.append(line + "\n");
                    }
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to read.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        } else if (e.getSource() == this.saveFile) {
            JFileChooser save = new JFileChooser();
            int option = save.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    out.write(text.getText());
                    out.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to save.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        }
    }
}
