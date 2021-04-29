package pl.sda.animation;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

public class MoveAnimation implements Runnable{
    private Circle ball;
    private double x ,y;
    private double dx, dy;

    public MoveAnimation(Circle ball, double x, double y) {
        this.ball = ball;
        this.x = x;
        this.y = y;
        dx = -(ball.getCenterX() - x) / 50;
        dy = -(ball.getCenterY() - y) / 50;
    }

    @Override
    public void run() {
        while (ball.getCenterX() <= x && ball.getCenterY() <= y){
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                ball.setCenterX(ball.getCenterX() + dx);
                ball.setCenterY(ball.getCenterY() + dy);
            });
        }
    }
}
