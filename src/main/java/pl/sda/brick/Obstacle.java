package pl.sda.brick;

import javafx.geometry.Bounds;

public interface Obstacle {
    void interact(Movable item, Bounds box);
    boolean isActive();
}
