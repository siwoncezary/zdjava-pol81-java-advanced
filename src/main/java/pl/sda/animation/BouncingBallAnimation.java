package pl.sda.animation;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;

public class BouncingBallAnimation implements Runnable {
    private Circle ball;
    private Scene scene;
    private double dx, dy;
    private boolean isPaused = false;
    private Rectangle paddle;
    private ObservableList<Rectangle> bricks;

    public BouncingBallAnimation(Circle ball, Scene scene) {
        this.ball = ball;
        this.scene = scene;
        dx = 2;
        dy = 2;
    }


    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        //wątek będzie można przerwać wysyłając do wątku sygnał metodą interrupt()
        while (!thisThread.isInterrupted()) {
            if ((ball.getCenterY() + ball.getRadius() + 1) > scene.getHeight()
                    || (ball.getCenterY() - ball.getRadius() - 1) < 0) {
                dy = -dy;
            }
            if ((ball.getCenterX() + ball.getRadius() + 1) > scene.getWidth()
                    || (ball.getCenterX() - ball.getRadius() - 1) < 0) {
                dx = -dx;
            }
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                thisThread.interrupt();
            }
            Platform.runLater(() -> {
                ball.setCenterX(ball.getCenterX() + dx);
                ball.setCenterY(ball.getCenterY() + dy);
            });
        }
        System.out.println("KONIEC ANIMACJI!!!");
    }

    public void setPause(boolean pause){
        isPaused = pause;
    }

    private boolean isPaddle(){
        return paddle != null;
    }
    private boolean isBricks(){
        return bricks != null && bricks.size() > 0;
    }
}
