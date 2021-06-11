package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import static com.company.Frame.stopGame;

/**
 * Menu represents the JFrame object - window with menu which has a "new game" and "load game" options
 */
public class Menu extends JFrame {

    /**
     * Constructor
     */
    Menu(){
        drawMenu();

        this.setTitle("PingPong Game");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Draws the menu with a "new game" option and a "load game" option
     */
    public void drawMenu(){
        Dimension menuSize = new Dimension(250,200);
        Dimension buttonSize = new Dimension(200,90);
        JPanel panel = new JPanel();
        JButton load = new JButton("Wczytaj Grę");
        JButton newGame = new JButton("Nowa Gra");

        load.setPreferredSize(buttonSize);
        newGame.setPreferredSize(buttonSize);
        panel.add(newGame);
        panel.add(load);

        load.addActionListener(actionEvent -> {
            this.dispose();
            try{
                var saveDir = Paths.get("").toAbsolutePath().toString();
                saveDir += "\\src\\saves\\save.txt";
                File file = new File(saveDir);

                if(file.exists()){
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    int s1 = Integer.parseInt(reader.readLine());
                    int s2 = Integer.parseInt(reader.readLine());

                    Frame frame = new Frame(s1, s2);
                    frame.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent windowEvent) {}

                        @Override
                        public void windowClosing(WindowEvent windowEvent) {
                            stopGame();
                            SaveGameMenu saveGameMenu = new SaveGameMenu(Frame.s1, Frame.s2);
                        }

                        @Override
                        public void windowClosed(WindowEvent windowEvent) {}

                        @Override
                        public void windowIconified(WindowEvent windowEvent) {}

                        @Override
                        public void windowDeiconified(WindowEvent windowEvent) {}

                        @Override
                        public void windowActivated(WindowEvent windowEvent) {}

                        @Override
                        public void windowDeactivated(WindowEvent windowEvent) {}
                    });
                }
                else{
                    return;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        newGame.addActionListener(actionEvent -> {
            this.dispose();
            Frame frame = new Frame(0, 0);
            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent windowEvent) {}

                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    stopGame();
                    SaveGameMenu saveGameMenu = new SaveGameMenu(Frame.s1, Frame.s2);
                }

                @Override
                public void windowClosed(WindowEvent windowEvent) {}

                @Override
                public void windowIconified(WindowEvent windowEvent) {}

                @Override
                public void windowDeiconified(WindowEvent windowEvent) {}

                @Override
                public void windowActivated(WindowEvent windowEvent) {}

                @Override
                public void windowDeactivated(WindowEvent windowEvent) {}
            });
        });

        this.add(panel);
        this.setSize(menuSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
