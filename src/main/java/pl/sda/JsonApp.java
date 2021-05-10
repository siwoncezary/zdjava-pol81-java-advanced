package pl.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        TextArea jsonView = new TextArea();
        Button createBtn = new Button("Create");
        Button jsonBtn = new Button("To JSON");
        TextField result = new TextField();
        jsonBtn.setOnAction(e -> {
           Player player = new Player("KAROL", 100, true, new String[]{"BBB","FFF"});
           PlayerDto playerDto = new PlayerDto(player);
           ObjectMapper mapper = new ObjectMapper();
            try {
                //Zamiana obiektu na JSON w łańcuchu
                String json = mapper.writeValueAsString(playerDto);
                jsonView.setText(json);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });
        createBtn.setOnAction(e ->{
            ObjectMapper mapper= new ObjectMapper();
            try {
                //mapowanie całego Json na klasę PLayer
                Player player = mapper.readValue(jsonView.getText(), Player.class);
//                JsonNode node = mapper.readTree(jsonView.getText());
//                int zip = node.get("address").get("zip").asInt();
//                int points = node.get("points").asInt();
//                String name = node.get("name").asText();
//                JsonNode games = node.get("games");
//                List<String> gamesList = new ArrayList<>();
//                if (games.isArray()){
//                    for(JsonNode game: games){
//                        gamesList.add(game.asText());
//                    }
//                }
//                boolean isActive = node.get("isActive").asBoolean();
//                result.setText("points " + points + " name " + name + " isActive " + isActive +" games " + gamesList +" zip "+ zip);
                result.setText(player.toString());
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });

        root.getChildren().addAll(jsonView, createBtn, jsonBtn, result);
        Scene scene = new Scene(root, 400,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
