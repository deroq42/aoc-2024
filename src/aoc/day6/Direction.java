package aoc.day6;

public enum Direction {

    UP('^', -1, 0),
    DOWN('v', 1, 0),
    LEFT('<', 0, -1),
    RIGHT('>', 0, 1);

    private final char c;
    private final int rowDelta;
    private final int columnDelta;

    Direction(char c, int rowDelta, int columnDelta) {
        this.c = c;
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    public static Direction getDirectionByChar(char c) {
        for (Direction direction : values()) {
            if (direction.getChar() == c) {
                return direction;
            }
        }
        return null;
    }

    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case RIGHT -> DOWN;
            case LEFT -> UP;
        };
    }

    public char getChar() {
        return c;
    }

    public int getRowDelta() {
        return rowDelta;
    }

    public int getColumnDelta() {
        return columnDelta;
    }
}
