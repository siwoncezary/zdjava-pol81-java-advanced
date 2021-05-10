package pl.sda.brick;

public class MoveVector {
    public double dx;
    public double dy;
    public MoveVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void reverseX(){
        dx = - dx;
    }

    public void reverseY(){
        dy = - dy;
    }
}
