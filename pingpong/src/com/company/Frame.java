package com.company;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * Frame class represents the JFrame object - the window in which the game takes place
 */
public class Frame extends JFrame{
    static Panel panel;
    static int s1 = 0;
    static int s2 = 0;

    /**
     * Contructor
     * @param score1 The score of the first player
     * @param score2 The score of the second player
     */
    Frame(int score1, int score2){
        s1 = score1;
        s2 = score2;
        panel = new Panel(score1, score2);
        this.add(panel);
        this.setTitle("PingPong Game");
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Stops the game after the game window is closed
     */
    public static void stopGame(){
        panel.stop();
    }
}




