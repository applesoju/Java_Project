package com.company;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Panel class represents the JPanel object - a contener for all the drawable objects
 */
public class Panel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final int OBSTACLE_HEIGHT = 200;
    static final int OBSTACLE_WIDTH = 15;
    static int yBall;
    static int yBallLast;
    static int xBall;
    static int xBallLast;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random = new Random();
    static Player aiPaddle;
    static Player playerPaddle;
    static Obstacle obstacle;
    static Result score;
    Ball ball;

    /**
     * Contructor
     * @param score1 The score of the first player
     * @param score2 The score of the second player
     */
    Panel(int score1, int score2){
        newPaddles();
        newObstacle();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
        score = new Result(GAME_WIDTH,GAME_HEIGHT);
        score.playerOneScore = score1;
        score.playerTwoScore = score2;
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Run method for starting the thread
     */
    public void run(){

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;

            if(delta >= 1) {
                moveObjectsAndCheckForCollisions();
                repaint();
                delta--;
            }

        }
    }

    /**
     * Creates threads for every object. The threads then handle the movement of the objects and they check if any collisions occured
     */
    public void moveObjectsAndCheckForCollisions(){
        Thread player1 = new Thread(aiPaddle);
        Thread player2 = new Thread(playerPaddle);
        Thread ball1 = new Thread(ball);
        Thread obstacle1 = new Thread(obstacle);

        player1.start();
        player2.start();
        ball1.start();
        obstacle1.start();

        try {
            player1.join();
            player2.join();
            ball1.join();
            obstacle1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new paddles for both players
     */
    public void newPaddles() {
        aiPaddle = new Player(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        playerPaddle = new Player(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    /**
     * Creates new obstacle
     */
    public void newObstacle() {
        obstacle = new Obstacle(GAME_WIDTH/2 - OBSTACLE_WIDTH/2,(GAME_HEIGHT/2)-(OBSTACLE_HEIGHT/2),OBSTACLE_WIDTH,OBSTACLE_HEIGHT);
    }

    /**
     * Redraws the panel
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    /**
     * Draws all objects
     * @param g Graphics object that encapsulates state information needed for the basic rendering operations, used for drawing onto the panel
     */
    public void draw(Graphics g) {
        aiPaddle.draw(g);
        playerPaddle.draw(g);
        ball.draw(g);
        obstacle.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * AL represents the receiver of the keystrokes
     */
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
                playerPaddle.keyPressed(e);
            }
        }
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
                playerPaddle.keyReleased(e);
            }
        }
    }

    /**
     * Gets rid of the objects so the game stops
     */
    public void stop(){
        ball = null;
        aiPaddle = null;
        playerPaddle = null;
        obstacle = null;
    }
}