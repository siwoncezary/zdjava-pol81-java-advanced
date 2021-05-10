package pl.sda.brick;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public interface Movable {
    MoveVector getVector();
    void move();
}
