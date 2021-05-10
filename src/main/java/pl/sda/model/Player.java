package pl.sda.model;

import java.util.Arrays;

public class Player {
    private String name;
    private int points;
    private boolean isActive;
    private String[] games;
    private Address address;
    public Player(){
    }

    public Player(String name, int points, boolean isActive, String[] games) {
        this.name = name;
        this.points = points;
        this.isActive = isActive;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String[] getGames() {
        return games;
    }

    public void setGames(String[] games) {
        this.games = games;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", isActive=" + isActive +
                ", games=" + Arrays.toString(games) +
                ", address=" + address +
                '}';
    }
}
