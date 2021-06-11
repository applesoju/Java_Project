package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;

/**
 * SaveGameMenu represents the JFrame object - window with menu which has a "save game" option and an "exit game" option
 */
public class SaveGameMenu extends JFrame {

    /**
     * Contructor
     * @param s1 The score of the first player
     * @param s2 The score of the second player
     */
    SaveGameMenu(int s1, int s2){
        drawSaveGameMenu(s1, s2);

        this.setTitle("PingPong Game");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Draws the menu with a "save game" option and an "exit game" option
     * @param s1 The score of the first player
     * @param s2 The score of the second player
     */
    public void drawSaveGameMenu(int s1, int s2){
        Dimension menuSize = new Dimension(250,200);
        Dimension buttonSize = new Dimension(200,90);
        JPanel panel = new JPanel();
        JButton close = new JButton("Zamknij Grę");
        JButton save = new JButton("Zapisz Grę");

        close.setPreferredSize(buttonSize);
        save.setPreferredSize(buttonSize);
        panel.add(save);
        panel.add(close);

        close.addActionListener(actionEvent -> {
            System.exit(0);
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var saveDir = Paths.get("").toAbsolutePath().toString();
                saveDir += "\\src\\saves\\save.txt";
                File file = new File(saveDir);

                try {
                    var stringToSave = Integer.toString(s1) + '\n';
                    stringToSave +=  Integer.toString(s2);

                    if(file.exists()) {
                        FileOutputStream outputStream = new FileOutputStream(file);
                        var stringToBytes = stringToSave.getBytes();
                        outputStream.write(stringToBytes);

                        System.out.print("Zapisano do istiejącego pliku");
                    }
                    else{
                        file.createNewFile();

                        FileOutputStream outputStream = new FileOutputStream(file);
                        var stringToBytes = stringToSave.getBytes();
                        outputStream.write(stringToBytes);

                        System.out.print("Zapisano do nowo utworzonego pliku");
                    }
                    System.exit(0);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        this.add(panel);
        this.setSize(menuSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
