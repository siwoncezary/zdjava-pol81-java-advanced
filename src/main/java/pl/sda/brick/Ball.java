package pl.sda.brick;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle implements Movable{
    private final MoveVector vector;

    public Ball(double x, double y, double radius, MoveVector vector){
        super(x, y, radius, Color.DARKORANGE);
        this.setCenterX(300);
        this.setCenterY(200);
        this.vector = vector;
    }


    public MoveVector getVector() {
        return vector;
    }

    @Override
    public void move() {
        this.setCenterX(getCenterX() + vector.dx);
        this.setCenterY(getCenterY() + vector.dy);
    }
}
