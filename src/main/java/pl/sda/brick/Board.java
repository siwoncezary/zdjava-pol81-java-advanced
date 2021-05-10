package pl.sda.brick;

import javafx.scene.layout.Pane;

public class Board extends Pane {
    private final Paddle paddle;
    private final Wall wall;
    private final Ball ball;

    public Board(Paddle paddle, Wall wall, Ball ball) {
        this.paddle = paddle;
        this.wall = wall;
        this.ball = ball;
        this.setHeight(wall.getHeight() + 200);
        this.setWidth(wall.getWidth());
        this.getChildren().addAll(wall, ball, paddle);
        paddle.setOnMouseDragged(e ->{
            if (isPaddleInside()){
                paddle.setX(e.getX());
            }
        });
    }

    private boolean isPaddleInside(){
        return this.getLayoutX() < paddle.getX() && this.getLayoutX() + this.getWidth() > paddle.getX() + paddle.getWidth();
    }
}
