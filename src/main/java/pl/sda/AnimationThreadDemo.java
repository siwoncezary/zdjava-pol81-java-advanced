package pl.sda;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pl.sda.animation.MoveAnimation;

public class AnimationThreadDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Circle ball = new Circle(400, 300, 50, Color.BLUEVIOLET);
        Thread animation = new Thread(new MoveAnimation(ball, 600, 300));
        animation.start();
        root.getChildren().add(ball);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
