package pl.sda;

import javafx.collections.FXCollections;
import pl.sda.model.PhoneBookItem;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<PhoneBookItem> items = new HashSet<>();
        PhoneBookItem item = new PhoneBookItem("aa","aa","aa", LocalDate.now());
        items.add(item);
        item = new PhoneBookItem("abc","aa","aa", LocalDate.now());
        items.add(item);
    }
}
