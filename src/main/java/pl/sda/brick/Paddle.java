package pl.sda.brick;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle implements Obstacle{
    private MoveVector vector = new MoveVector(0,0);
    public Paddle(double width, double height) {
        super(width, height, Color.BLUEVIOLET);
        this.setArcHeight(height);
        this.setArcWidth(height);
        this.setX(100);
        this.setY(300);
    }

    @Override
    public void interact(Movable item, Bounds box) {
        if (this.intersects(box)) {
            item.getVector().reverseY();
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
