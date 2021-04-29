package pl.sda;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pl.sda.animation.BouncingBallAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

class BallHandler implements EventHandler<MouseEvent> {
    private Circle ball;
    private Scene scene;

    public BallHandler(Circle ball, Scene scene) {
        this.ball = ball;
        this.scene = scene;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Thread thread = new Thread(new BouncingBallAnimation(ball, scene));
        thread.start();
    }
}

@FunctionalInterface
interface Operation {
    double apply(double a, double b);
}

// a + b
// a - b
// a * b
// a / b
public class BouncingBallApp extends Application {
    Operation add = (a, b) -> a + b;
    Operation diff = (a, b) -> a - b;
    List<Operation> operations = new ArrayList<>(List.of(add, diff));

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Circle ball = new Circle(300, 200, 50, Color.BLUEVIOLET);
        root.getChildren().add(ball);
        Scene scene = new Scene(root, 600, 400);
        //tworzenie obiektu implementującego interfejs poprzez klasę jawnie nazwaną
        //ball.setOnMouseClicked(new BallHandler(ball, ball.getScene()));
        //Tworzenie obiektu klasy anonimowej
//        ball.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Thread thread = new Thread(new BouncingBallAnimation(ball, scene));
//                thread.start();
//            }
//        });
        //Tworzenie obiektu metodą lambdy implementującej interfejs funkcyjny
        //Dodaj przycisk pauzujący kulkę
        ExecutorService service = Executors.newSingleThreadExecutor();
        Button buttonStart = new Button("START");
        Button buttonStop = new Button("STOP");
        buttonStop.setDisable(true);
        buttonStop.setLayoutX(200);
        BouncingBallAnimation animation = new BouncingBallAnimation(ball, scene);
        buttonStart.setOnAction(mouseEvent -> {
            if (!service.isShutdown()) {
                buttonStart.setDisable(true);
                buttonStop.setDisable(false);
                service.execute(animation);
            }
        });
        buttonStop.setOnAction(event -> {
                buttonStop.setDisable(true);
                service.shutdownNow();
        });
        root.getChildren().addAll(buttonStart, buttonStop);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
