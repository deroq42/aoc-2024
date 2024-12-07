package aoc.day6;

public class Position {

    private final int x;
    private final int y;
    private final boolean obstruction;

    public Position(int x, int y, boolean obstruction) {
        this.x = x;
        this.y = y;
        this.obstruction = obstruction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstruction() {
        return obstruction;
    }
}
