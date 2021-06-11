package com.company;

import java.awt.*;

/**
 * Result class represents the score of a game
 */
public class Result extends Rectangle {
    static int windowWidth;
    static int windowHeight;
    int playerOneScore;
    int playerTwoScore;

    /**
     * Contructor
     * @param windowWidth Width of the window
     * @param windowHeight Height of the window
     */
    Result(int windowWidth, int windowHeight) {
        Result.windowWidth = windowWidth;
        Result.windowHeight = windowHeight;
    }

    /**
     * Draws the score
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawLine(0, 0, windowWidth , 0);
        g.drawLine(0, windowHeight - 1, windowWidth , windowHeight - 1 );
        g.drawString(String.valueOf(playerOneScore / 10) + String.valueOf(playerOneScore % 10), (windowWidth / 2) - 85, 50);
        g.drawString(String.valueOf(playerTwoScore / 10) + String.valueOf(playerTwoScore % 10), (windowWidth / 2) + 20, 50);
    }

}