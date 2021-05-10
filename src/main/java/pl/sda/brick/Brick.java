package pl.sda.brick;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Brick extends Rectangle implements Obstacle {
    private boolean active;
    BoundingBox left;
    BoundingBox right;
    BoundingBox top;
    BoundingBox bottom;
    private final double thin = 1;

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setArcHeight(height / 2);
        this.setArcWidth(height / 2);
        this.setFill(Color.DARKGREEN);
        Bounds current = this.getBoundsInParent();
        left = new BoundingBox(current.getMinX(), current.getMinY(), thin, current.getHeight());
        right = new BoundingBox(current.getMaxX() - thin, current.getMaxY(), thin, current.getHeight());
        top = new BoundingBox(current.getMinX(), current.getMinY(), current.getWidth(), thin);
        bottom = new BoundingBox(current.getMinX(), current.getMaxY() - thin, current.getWidth(), thin);
        active = true;
    }

    @Override
    public void interact(Movable item, Bounds box) {
        if (!this.intersects(box)){
            return;
        }
        if (left.intersects(box) || right.intersects(box)) {
            item.getVector().reverseX();
            active = false;
        }
        if (top.intersects(box) || bottom.intersects(box)) {
            item.getVector().reverseY();
            active = false;
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void runOutAnimation(Runnable finish){
        this.setFill(Color.BROWN);
        FadeTransition transition = new FadeTransition(Duration.millis(800), this);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(800), this);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.setFromY(1);
        scaleTransition.setFromX(1);
        scaleTransition.playFromStart();
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        ParallelTransition parallelTransition = new ParallelTransition(transition, scaleTransition);
        parallelTransition.setOnFinished(e -> {
            finish.run();
        });
        Platform.runLater(() -> {
            parallelTransition.play();
        });
    }
}
