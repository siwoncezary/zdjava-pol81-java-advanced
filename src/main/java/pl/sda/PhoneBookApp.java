package pl.sda;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sda.model.PhoneBookItem;
import pl.sda.storage.FileSerialStorage;
import pl.sda.storage.FileTextStorage;
import pl.sda.storage.Storage;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
//TODO dodać przycisk Quick Save, który zapisuje dane do bieżącego pliku lib istniejącego obiektu storage
public class PhoneBookApp extends Application {
    BorderPane root = new BorderPane();
    HBox toolbar = new HBox();
    VBox form = new VBox();
    Stage stage;
    ListView<PhoneBookItem> list = new ListView<>();
    SimpleStringProperty nameProperty = new SimpleStringProperty();
    SimpleStringProperty phoneProperty = new SimpleStringProperty();
    SimpleStringProperty emailProperty = new SimpleStringProperty();
    SimpleObjectProperty<LocalDate> birthProperty = new SimpleObjectProperty<>();
    SimpleStringProperty idProperty = new SimpleStringProperty();
    Storage storage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        prepareStage(stage);
        prepareToolBar();
        prepareForm();
    }

    public void prepareStage(Stage stage) {
        root.setTop(toolbar);
        root.setCenter(form);
        root.setBottom(list);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PhoneBookItem>() {
            @Override
            public void changed(ObservableValue<? extends PhoneBookItem> observableValue, PhoneBookItem previous, PhoneBookItem current) {
                nameProperty.set(current.getName());
                emailProperty.set(current.getEmail());
                phoneProperty.set(current.getPhone());
                birthProperty.set(current.getBirthDate());
                idProperty.set("" + current.getId());
            }
        });
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
        filePath.setPrefWidth(10000);
        filePath.setEditable(false);
        Button loadBtn = new Button("_Load file ...");
        loadBtn.setMinWidth(80);
        loadBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File fileToOpen = fileChooser.showOpenDialog(stage);
            if (fileToOpen != null) {
                storage = new FileSerialStorage(fileToOpen.getAbsolutePath());
                list.setItems(FXCollections.observableList(storage.load()));
                filePath.setText(fileToOpen.getAbsolutePath());
            }
        });
        Button saveBtn = new Button("_Save File ...");
        saveBtn.setOnAction(event->{
            FileChooser fileChooser = new FileChooser();
            File fileToSave = fileChooser.showSaveDialog(stage);
            if (fileToSave != null) {
                if (fileToSave.getName().substring(fileToSave.getName().lastIndexOf(".")).equals(".txt")){
                    storage = new FileTextStorage(fileToSave.getAbsolutePath());
                    storage.save(list.getItems());
                    filePath.setText(fileToSave.getAbsolutePath());
                } else {
                    storage = new FileSerialStorage(fileToSave.getAbsolutePath());
                    storage.save(list.getItems());
                    filePath.setText(fileToSave.getAbsolutePath());
                }
            }
        });
        saveBtn.setMinWidth(80);
        toolbar.getChildren().addAll(filePath, loadBtn, saveBtn);
    }

    public void prepareForm() {
        form.setSpacing(10);
        form.setPadding(new Insets(10));
        Label idLabel = new Label("Id");
        TextField idField = new TextField();
        idField.setEditable(false);
        Label nameLabel = new Label("Imię, nazwa");
        TextField nameField = new TextField();
        Label phoneLabel = new Label("Nr telefonu");
        TextField phoneField = new TextField();
        Label emailLabel = new Label("Adres email");
        TextField emailField = new TextField();
        Label birthLabel = new Label("Data urodzin");
        DatePicker birthPicker = new DatePicker();
        birthPicker.setMaxWidth(10000);
        birthPicker.valueProperty().bindBidirectional(birthProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        phoneField.textProperty().bindBidirectional(phoneProperty);
        emailField.textProperty().bindBidirectional(emailProperty);
        idField.textProperty().bindBidirectional(idProperty);
        Button submit = new Button("Zapisz");
        submit.setDefaultButton(true);
        form.getChildren().addAll(
                idLabel,
                idField,
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
            //Jeśli jest pusty to znaczy, że istnieje już taki wpis
            if (idProperty.get() != null && !idProperty.get().isEmpty()){
                long id = Long.valueOf(idProperty.get());
                Optional<PhoneBookItem> edited = list.getItems().stream()
                        .filter(item -> item.getId() == id)
                        .findFirst();
                PhoneBookItem item = null;
                if (edited.isPresent()){
                    item = edited.get();
                    item.setPhone(phoneProperty.get());
                    item.setBirthDate(birthProperty.get());
                    item.setEmail(emailProperty.get());
                    item.setName(nameProperty.get());
                }
                int find = -1;
                if (item != null){
                    for (int i = 0; i < list.getItems().size(); i++){
                        if (list.getItems().get(i).getId() == item.getId()){
                            find = i;
                            break;
                        }
                    }
                }
                if (find != -1 && item != null) {
                    list.getItems().set(find, item);
                }
//                edited.map(item -> {
//                    item.setPhone(phoneProperty.get());
//                    item.setBirthDate(birthProperty.get());
//                    item.setEmail(emailProperty.get());
//                    item.setName(nameProperty.getName());
//                    return item;
//                }).orElse(null);
                idProperty.set("");
                nameProperty.set("");
                emailProperty.set("");
                phoneProperty.set("");
                birthProperty.set(null);
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