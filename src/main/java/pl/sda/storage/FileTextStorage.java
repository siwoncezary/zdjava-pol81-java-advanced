package pl.sda.storage;
import pl.sda.model.PhoneBookItem;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileTextStorage implements Storage {
    private String pathToFile;

    public FileTextStorage(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public List<PhoneBookItem> load() {
        //TODO uzupełnić odczyt pozostałych danych: email, phone, birth
        Path fileToLoad = Paths.get(pathToFile);
        try {
            return Files.lines(fileToLoad).map(line -> {
                String[] columns = line.split("\t");
                long id = Long.parseLong(columns[0]);
                String name = columns[1];
                return new PhoneBookItem(name, "","",null);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(List<PhoneBookItem> items) {
        //TODO dodać zapis pozostałych pól klasy PhoneBookItem
        Path fileToSave = Paths.get(pathToFile);
        try {
            Files.writeString(fileToSave,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        items.stream().map(item -> {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getId()).append("\t").append(item.getName()).append(System.lineSeparator());
            return sb.toString();
        }).forEach(str -> {
            try {
                Files.writeString(fileToSave, str, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
