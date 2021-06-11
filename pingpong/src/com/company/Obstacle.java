package com.company;

import java.awt.*;

/**
 * Obstacle class represents the obstacle
 */
public class Obstacle extends Rectangle implements Runnable{
    int speed = 3;

    /**
     * Contructor
     * @param x X coordinate of the obstacle
     * @param y Y coordinate of the obstacle
     * @param PADDLE_WIDTH Width of the obstacle
     * @param PADDLE_HEIGHT Height of the obstacle
     */
    Obstacle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    /**
     * Draws the obstacle
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void draw(Graphics g) {
            g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

    /**
     * Checks if collision with edge of the screen occured
     */
    public void checkCollision() {
        if(this.y <=0) {
            this.setYDir(-this.speed);
        }
        if(this.y >= Panel.GAME_HEIGHT - this.height) {
            this.setYDir(-this.speed);
        }
    }

    /**
     * Sets the speed of an obstacle
     * @param randYDir The distance by which the obstacle will move in Y direction, in the next tick
     */
    public void setYDir(int randYDir) {
        speed = randYDir;
    }

    /**
     * Handles the movement of the obstacle
     */
    public void move(){
        y += speed;
    }

    /**
     * Run method for starting the thread
     */
    public void run() {
        this.move();
        this.checkCollision();
    }
}
