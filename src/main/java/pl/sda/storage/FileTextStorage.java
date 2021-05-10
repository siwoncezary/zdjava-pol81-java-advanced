package pl.sda.storage;

import pl.sda.model.PhoneBookItem;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileTextStorage implements Storage{
    private String pathToFile;

    public FileTextStorage(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public List<PhoneBookItem> load() {
        return null;
    }

    @Override
    public void save(List<PhoneBookItem> items) {
        Path fileToSave = Paths.get(pathToFile);
        try {
            Files.writeString(fileToSave, "LALALAAL", StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
