package aoc.day2;

import aoc.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        day2.solvePartOne();
        day2.solvePartTwo();
    }

    public Day2() {
        readInput();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();
        int safeReports = 0;

        for (String line : lines) {
            int[] levels = parseLevels(line);
            if (isSafeReport(levels)) {
                safeReports++;
            }
        }

        System.out.println("safe: " + safeReports);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {
        long s = System.currentTimeMillis();
        int safeReports = 0;

        for (String line : lines) {
            int[] levels = parseLevels(line);
            if (isSafeReportWithDampener(levels)) {
                safeReports++;
            }
        }

        System.out.println("safe with dampener: " + safeReports);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private int[] parseLevels(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private boolean isSafeReport(int[] levels) {
        if (levels.length < 2) {
            return true;
        }

        Direction direction = null;
        for (int index = 1; index < levels.length; index++) {
            int diff = levels[index] - levels[index - 1];
            if (diff == 0 || Math.abs(diff) > 3) { // if difference between two levels is zero or greater than 3, its invalid
                return false;
            }

            Direction currentDirection = diff > 0
                    ? Direction.INCREASING
                    : Direction.DECREASING;
            if (direction == null) {
                direction = currentDirection;
            } else if (direction != currentDirection) { // if direction is changing, its invalid
                return false;
            }
        }

        return true;
    }

    private boolean isSafeReportWithDampener(int[] levels) {
        for (int index = 0; index < levels.length; index++) {
            int[] modifiedLevels = removeLevel(levels, index); // remove one level to check if report is valid
            if (isSafeReport(modifiedLevels)) {
                return true;
            }
        }

        return isSafeReport(levels);
    }

    private int[] removeLevel(int[] levels, int index) {
        int[] result = new int[levels.length - 1];
        int j = 0;
        for (int i = 0; i < levels.length; i++) {
            if (i != index) {
                result[j++] = levels[i];
            }
        }

        return result;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}