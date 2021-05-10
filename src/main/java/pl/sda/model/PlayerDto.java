package pl.sda.model;

public class PlayerDto {
    private String name;
    private int points;

    public PlayerDto(Player player){
        this.name = player.getName();
        this.points = player.getPoints();
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
}
