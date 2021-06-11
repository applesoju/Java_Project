package com.company;
import java.awt.*;
import java.awt.event.*;

/**
 * Player class represents one of the two players
 */
public class Player extends Rectangle  implements Runnable {
    int id;
    int yVelocity;
    int speed = 10;

    /**
     * Contructor
     * @param x X coordinate of the player
     * @param y Y coordinate of the player
     * @param PADDLE_WIDTH Width of the paddle which represents the player
     * @param PADDLE_HEIGHT Height of the paddle which represents the player
     * @param id Id of the player, 1 - ai, 2 - human player
     */
    Player(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    /**
     * Handles the pressing of arrows keys
     * @param event Event which indicates that a keystroke occurred
     */
    public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
    }

    /**
     * Handles the releasing of arrows keys
     * @param event Event which indicates that a keystroke occurred
     */
    public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
    }

    /**
     * Sets the speed of a paddle
     * @param yDirection The distance by which the paddle will move in the next tick
     */
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    /**
     * Handles the algorithm that controls the movement of the paddle
     */
    public void aiMove(){
        int ballPaddleDiff = Panel.yBall - (y + Panel.PADDLE_HEIGHT / 2);
        int paddleMiddleDiff;
        int ballYDiff = Panel.yBall - Panel.yBallLast;
        int ballXDiff = Panel.xBall - Panel.xBallLast;

        if(ballXDiff > 0){
            paddleMiddleDiff = Panel.GAME_HEIGHT / 2 - y - Panel.PADDLE_HEIGHT / 2;
            if(paddleMiddleDiff > 5 ){
                setYDirection(speed / 2);
            }
            else if(paddleMiddleDiff < -5){
                setYDirection(-speed / 2);
            }
            else{
                setYDirection(0);
            }
        }
        else {
            if (ballYDiff > 0) {
                if (ballPaddleDiff > 0) setYDirection(speed);
                else setYDirection(0);
            }
            else {
                if (ballPaddleDiff < 0) setYDirection(-speed);
                else setYDirection(0);
            }
        }
    }

    /**
     * Handles the movement of the player
     */
    public void move() {
        if(id == 1){
            aiMove();
        }
        y += yVelocity;
    }

    /**
     * Draws the paddle that represents a player
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.orange);
        else
            g.setColor(Color.cyan);

        g.fillRect(x, y, width, height);
    }

    /**
     * Checks if collision with edge of the screen occured
     */
    public void checkCollision() {
        if(this.y<=0)
            this.y=0;

        if(this.y >= (Panel.GAME_HEIGHT-this.height))
            this.y = Panel.GAME_HEIGHT-this.height;

    }

    /**
     * Run method for starting the thread
     */
    public void run() {
        this.move();
        this.checkCollision();
    }
}
