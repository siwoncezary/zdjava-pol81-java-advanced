package pl.sda;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.sda.model.PhoneBookItem;

import java.time.LocalDate;

public class PhoneBookApp extends Application {
    BorderPane root = new BorderPane();
    HBox toolbar = new HBox();
    VBox form = new VBox();
    ListView<PhoneBookItem> list = new ListView<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        prepareStage(stage);
        prepareToolBar();
        prepareForm();
    }

    public void prepareStage(Stage stage) {
        root.setTop(toolbar);
        root.setCenter(form);
        root.setBottom(list);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Phone book");
        stage.show();
    }

    public void prepareToolBar() {
        toolbar.setPadding(new Insets(10));
        toolbar.setSpacing(10);
        toolbar.setAlignment(Pos.BASELINE_CENTER);
        TextField filePath = new TextField();
        filePath.setEditable(false);
        Button loadBtn = new Button("_Load file ...");
        loadBtn.setOnAction(event -> {
            //TODO zdefiniować otwieranie okna dialogowego do wyboru pliku z danymi
            filePath.setText("Scieżka do pliku...");
        });
        toolbar.getChildren().addAll(filePath, loadBtn);
    }

    public void prepareForm() {
        form.setSpacing(10);
        form.setPadding(new Insets(10));
        Label nameLabel = new Label("Imię, nazwa");
        TextField nameField = new TextField();
        Label phoneLabel = new Label("Nr telefonu");
        TextField phoneField = new TextField();
        Label emailLabel = new Label("Adres email");
        TextField emailField = new TextField();
        Label birthLabel = new Label("Data urodzin");
        DatePicker birthPicker = new DatePicker();
        Button submit = new Button("Zapisz");
        submit.setDefaultButton(true);
        form.getChildren().addAll(
                nameLabel,
                nameField,
                phoneLabel,
                phoneField,
                emailLabel,
                emailField,
                birthLabel,
                birthPicker,
                submit
        );

        submit.setOnAction(event -> {
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty()){
                return;
            }
            PhoneBookItem item = new PhoneBookItem(
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText(),
                    birthPicker.getValue()
            );
            list.getItems().add(item);
            nameField.clear();
            phoneField.clear();
            emailField.clear();
            birthPicker.setValue(LocalDate.now());
        });
    }

}
