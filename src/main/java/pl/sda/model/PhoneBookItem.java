package pl.sda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PhoneBookItem implements Serializable {
    private static long LAST_ID = 0;
    private long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;

    public PhoneBookItem(String name, String phone, String email, LocalDate birthDate) {
        LAST_ID++;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.id = LAST_ID;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Equals!!!");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBookItem that = (PhoneBookItem) o;
        return name.equals(that.name) && phone.equals(that.phone) && email.equals(that.email) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        System.out.println("HashCode!!!");
        return Objects.hash(name, phone, email, birthDate);
    }
}
