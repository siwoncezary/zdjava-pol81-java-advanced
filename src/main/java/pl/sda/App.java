package pl.sda;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import pl.sda.shape.Cross;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var root = new Group();
        Circle circle = new Circle(100, 100.25, 50);
        circle.setFill(Color.DARKGREEN);
        circle.setStroke(Color.rgb(255, 50, 255, 0.5));
        circle.setStrokeWidth(5);
        circle.setOnMouseClicked(event -> {
            circle.setFill(Color.BLUEVIOLET);
        });
        Rectangle rectangle = new Rectangle(100, 200, 100,100);
        rectangle.setFill(Color.CADETBLUE);

        Ellipse ellipse = new Ellipse(100, 300, 100, 50);
        ellipse.setFill(Color.ALICEBLUE);

        Polygon polygon = new Polygon(200, 200, 250, 250, 150, 250);
        Path path = new Path();
        path.getElements().addAll(
             new MoveTo(300, 100),
             new LineTo(400, 200),
             new CubicCurveTo(200, 200, 300, 100, 300, 400),
             new LineTo(300, 100)
        );

        Cross cross = new Cross(200, 100, 100, 100, 40);
        cross.getChildren().forEach(item -> {
            Shape shape = (Shape) item;
            shape.setFill(Color.BLUE);
        });
        root.getChildren().addAll(circle, rectangle, ellipse, polygon, path, cross);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Aplikacja JavaFX");
        stage.setOnCloseRequest(event -> {
            System.out.println("App closed!");
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}