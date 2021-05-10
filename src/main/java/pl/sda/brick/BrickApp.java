package pl.sda.brick;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BrickApp extends Application {
    Wall wall = new Wall(40, 20, 4, 10);
    Paddle paddle = new Paddle(60, 20);
    Ball ball = new Ball(0, 0, 10, new MoveVector(3, 3));
    Pane root = new Pane(wall, paddle, ball);
    Scene scene = new Scene(root, wall.getWidth(), 400);
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    Queue<Brick> removed = new ArrayDeque<>();
    @Override
    public void start(Stage stage) throws Exception {
        wall.toFront();
        paddle.setOnMouseDragged(e -> {
            if (e.getX() > 0 && e.getX() < scene.getWidth() - paddle.getWidth()) {
                paddle.setX(e.getX());
            }
        });
        stage.setScene(scene);
        stage.setTitle("Brick game");
        stage.setOnCloseRequest(e -> {
            service.shutdownNow();
        });
        service.scheduleAtFixedRate(this::updateFrame, 0, 20, TimeUnit.MILLISECONDS);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void updateFrame() {
        if ((ball.getCenterY() + ball.getRadius() + 1) > root.getHeight()
                || (ball.getCenterY() - ball.getRadius() - 1) < 0) {
            ball.getVector().reverseY();
        }
        if ((ball.getCenterX() + ball.getRadius() + 1) > root.getWidth()
                || (ball.getCenterX() - ball.getRadius() - 1) < 0) {
            ball.getVector().reverseX();
        }
        paddle.interact(ball, ball.getBoundsInLocal());
        wall.getChildren().stream().map(brick -> (Brick) brick).forEach(brick -> {
            brick.interact(ball, ball.getBoundsInParent());
            if (!brick.isActive()) {
                removed.add(brick);
            }
        });
        if (!removed.isEmpty()) {
            Brick brick = removed.poll();
            brick.runOutAnimation(() -> wall.getChildren().remove(brick));
        }
        Platform.runLater(() -> {
            ball.move();
        });
    }
}
