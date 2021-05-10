package pl.sda.brick;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Wall extends Pane {
    private final double gap = 6;
    public Wall(double brickWidth, double brickHeight, int rows, int columns){
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                Brick brick = new Brick((brickWidth + gap) * column + gap, (brickHeight + gap) * row + gap, brickWidth, brickHeight);
                this.getChildren().add(brick);
            }
        }
        this.setWidth((brickWidth + gap) * columns + gap);
        this.setHeight((brickHeight + gap) * rows + gap + 300);
    }
}
