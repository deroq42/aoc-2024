package aoc.day6;

import aoc.Puzzle;
import aoc.utils.Pair;

import java.util.*;

public class Day6 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public Day6() {
        readInput();
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.solvePartOne();
        day6.solvePartTwo();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();

        Map<Pair<Integer, Integer>, Position> positionMap = new HashMap<>();
        Guard guard = null;
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            if (line == null || line.isEmpty()) {
                continue;
            }

            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == ' ') {
                    continue;
                }

                Position position = new Position(x, y, c == '#');
                positionMap.put(new Pair<>(x, y), position);
                if (c != '.' && c != '#') {
                    guard = new Guard(Direction.getDirectionByChar(c), position);
                }
            }
        }

        if (guard == null) {
            return;
        }

        Set<Position> visitedPositions = new HashSet<>();
        visitedPositions.add(guard.getPosition());

        while (true) {
            int newX = guard.getPosition().getX() + guard.getDirection().getColumnDelta();
            int newY = guard.getPosition().getY() + guard.getDirection().getRowDelta();

            Position newPosition = positionMap.get(new Pair<>(newX, newY));
            if (newPosition == null) {
                break;
            }

            if (newPosition.isObstruction()) {
                guard.setDirection(guard.getDirection().turnRight());
                continue;
            }

            guard.setPosition(newPosition);
            visitedPositions.add(newPosition);
        }

        System.out.println("Visited positions: " + visitedPositions.size());
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {

    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}
