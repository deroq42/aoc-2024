package aoc.day4;

public enum Direction {

    LEFT(0, -1),
    RIGHT(0, 1),
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    UP_LEFT(-1, -1),
    DOWN(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1);

    private final int rowDelta;
    private final int columnDelta;

    Direction(int rowDelta, int columnDelta) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    public int getRowDelta() {
        return rowDelta;
    }

    public int getColumnDelta() {
        return columnDelta;
    }
}
