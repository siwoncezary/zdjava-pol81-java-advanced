package pl.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.sda.model.Player;
import pl.sda.model.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class JsonApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        TextArea jsonView = new TextArea("{\n" +
                "\t\"name\": \"ALA\",\n" +
                "\t\"points\": 123,\n" +
                "\t\"isActive\": true,\n" +
                "\t\"games\":[\"AA\",\"BB\",\"CC\"],\n" +
                "\t\"address\":{\n" +
                "\t\t\"city\":\"Warsaw\",\n" +
                "\t\t\"zip\": 23456\n" +
                "\t}\n" +
                "}");
        jsonView.setEditable(false);
        Button treeJsonBtn = new Button("Parse tree JSON");
        Button valueJsonBtn = new Button("Parse JSON to class");
        Button jsonBtn = new Button("Class to JSON");
        TextArea result = new TextArea();

        jsonBtn.setOnAction(e -> {
            Player player = new Player("KAROL", 100, true, new String[]{"BBB", "FFF"});
            PlayerDto playerDto = new PlayerDto(player);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Konwersja obiektu PlayerDto do łańcucha JSON. Poniżej obiekt, na podstawie którego utworzono PlayerDto.");
            info.setContentText(player.toString());
            info.show();
            ObjectMapper mapper = new ObjectMapper();
            try {
                //Zamiana obiektu na JSON w łańcuchu
                String json = mapper.writeValueAsString(playerDto);
                result.setText(json);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });

        valueJsonBtn.setOnAction(e -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                //mapowanie całego Json na klasę PLayer
                Player player = mapper.readValue(jsonView.getText(), Player.class);
                result.setText(player.toString());
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });

        treeJsonBtn.setOnAction(e -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = null;
            try {
                //Odczytanie Json jako drzewa węzłów, node jest korzeniem drzewa
                node = mapper.readTree(jsonView.getText());
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            //odczyt bezpośrednich potomków węzła głównego
            int zip = node.get("address").get("zip").asInt();
            int points = node.get("points").asInt();
            String name = node.get("name").asText();
            JsonNode games = node.get("games");
            List<String> gamesList = new ArrayList<>();
            if (games.isArray()) {
                for (JsonNode game : games) {
                    gamesList.add(game.asText());
                }
            }
            boolean isActive = node.get("isActive").asBoolean();
            result.setText("points " + points + "\nname " + name + "\nisActive " + isActive + "\ngames " + gamesList + "\nzip " + zip);
        });

        root.getChildren().addAll(new Label("Przykładowy łańcuch JSON"),jsonView, treeJsonBtn, valueJsonBtn, jsonBtn, new Label("Wynik przetwarzania"),result);
        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
