package in.suprabhatkumar.chess;

public class Player {

    protected String name;
    protected int points;
    protected int color;

    public Player(String name, int color) {
        this.name = name;
        this.points = 0;
        this.color = color;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public int getColor() {
        return  color;
    }
}
