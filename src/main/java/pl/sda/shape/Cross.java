package pl.sda.shape;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * Zdefiniować własną klasę dowolnego obiektu graficznego, jakiś kształt np. strzałka, buźka
 */
public class Cross  extends Group {
    public Cross(double x, double y, double width, double height, double strokeWidth){
        Rectangle rectangle1 = new Rectangle(x + (width - strokeWidth)/2, y, strokeWidth, height);
        Rectangle rectangle2 = new Rectangle(x, y + (height - strokeWidth)/2, width, strokeWidth);
        this.getChildren().addAll(rectangle1, rectangle2);
    }
}
