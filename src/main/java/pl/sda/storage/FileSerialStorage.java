package pl.sda.storage;

import pl.sda.model.PhoneBookItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSerialStorage implements Storage {
    private final String pathToFile;

    public FileSerialStorage(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public List<PhoneBookItem> load() {
        try (
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathToFile));
        ) {
            return (List<PhoneBookItem>) input.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(List<PhoneBookItem> items) {
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathToFile));
        ) {
            output.writeObject(new ArrayList<>(items));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
