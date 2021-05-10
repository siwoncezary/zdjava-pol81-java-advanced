package pl.sda.storage;

import pl.sda.model.PhoneBookItem;

import java.util.List;

public interface Storage {
    List<PhoneBookItem> load();
    void save(List<PhoneBookItem> items);
}
