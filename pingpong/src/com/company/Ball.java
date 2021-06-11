package com.company;

import java.awt.*;
import java.util.*;

/**
 * Ball class represents the ball
 */
public class Ball extends Rectangle implements Runnable{
    Random random;
    int xSpeed;
    int ySpeed;
    int startSpeed = 2;

    /**
     * Contructor
     * @param x X coordinate of the ball
     * @param y Y coordinate of the ball
     * @param width Width of the ball
     * @param height Height of the ball
     */
    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);

        random = new Random();
        int randXDir = random.nextInt(2);

        if (randXDir == 0)
            randXDir--;

        setXDir(randXDir * startSpeed);

        int randYDir = random.nextInt(2);

        if (randYDir == 0)
            randYDir--;

        setYDir(randYDir * startSpeed);
    }

    /**
     * Sets the speed of a ball in the X direction
     * @param randXDir The distance by which the ball will move in X direction, in the next tick
     */
    public void setXDir(int randXDir) { xSpeed = randXDir; }

    /**
     * Sets the speed of a ball in the Y direction
     * @param randYDir The distance by which the ball will move in Y direction, in the next tick
     */
    public void setYDir(int randYDir) { ySpeed = randYDir; }

    /**
     * Handles the movement of the ball
     */
    public void move() {
        x += xSpeed;
        y += ySpeed;
        Panel.yBallLast = Panel.yBall;
        Panel.yBall = y;
        Panel.xBallLast = Panel.xBall;
        Panel.xBall = x;
    }

    /**
     * Resets the position and the speed of a ball
     */
    public void resetBall(){
        random = new Random();
        x = (Panel.GAME_WIDTH/2)-(Panel.BALL_DIAMETER/2);
        y = random.nextInt(Panel.GAME_HEIGHT - Panel.BALL_DIAMETER);

        int randXDir = random.nextInt(2);

        if (randXDir == 0)
            randXDir--;

        setXDir(randXDir * startSpeed);

        int randYDir = random.nextInt(2);

        if (randYDir == 0)
            randYDir--;

        setYDir(randYDir * startSpeed);
    }

    /**
     * Draws the ball
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, height, width);
    }

    /**
     * Checks if collision with edge of the screen, the obstacle or one of the paddles occured
     * @return true if the collision occured with left or right edge of the screen, false otherwise
     */
    public boolean checkCollision() {
        if(this.y <=0) {
            this.setYDir(-this.ySpeed);
        }

        if(this.y >= Panel.GAME_HEIGHT-Panel.BALL_DIAMETER) {
            this.setYDir(-this.ySpeed);
        }

        if(this.intersects(Panel.obstacle)) {
            this.xSpeed = Math.abs(this.xSpeed);

            if(this.ySpeed>0)
                this.ySpeed++;
            else
                this.ySpeed--;

            if(this.x < Panel.GAME_WIDTH/2 )
                this.setXDir(-this.xSpeed);
            else
                this.setXDir(this.xSpeed);

            this.setYDir(this.ySpeed);
        }

        if(this.intersects(Panel.aiPaddle)) {
            this.xSpeed = Math.abs(this.xSpeed);
            this.xSpeed++;

            if(this.ySpeed>0)
                this.ySpeed++;
            else
                this.ySpeed--;

            this.setXDir(this.xSpeed);
            this.setYDir(this.ySpeed);
        }

        if(this.intersects(Panel.playerPaddle)) {
            this.xSpeed = Math.abs(this.xSpeed);
            this.xSpeed++;

            if(this.ySpeed>0)
                this.ySpeed++;
            else
                this.ySpeed--;

            this.setXDir(-this.xSpeed);
            this.setYDir(this.ySpeed);
        }

        if(this.x <=0) {
            Panel.score.playerTwoScore++;
            Frame.s2++;
            System.out.println("Player 2: "+Panel.score.playerTwoScore);
            return true;
        }

        if(this.x >= Panel.GAME_WIDTH-Panel.BALL_DIAMETER) {
            Panel.score.playerOneScore++;
            Frame.s1++;
            System.out.println("Player 1: "+Panel.score.playerOneScore);
            return true;
        }
        return false;
    }

    /**
     * Run method for starting the thread
     */
    public void run() {
        this.move();

        if(this.checkCollision()){
            resetBall();
        }
    }
}