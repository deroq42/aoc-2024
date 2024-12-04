package aoc.day4;

import aoc.Puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @since 04.12.24
 */
public class Day4 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.solvePartOne();
        day4.solvePartTwo();
    }

    public Day4() {
        readInput();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();
        int xmasSum = 0;

        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            if (line == null || line.isEmpty()) {
                continue;
            }

            for (int charIndex = 0; charIndex < line.length(); charIndex++) {
                if (line.charAt(charIndex) != 'X') {
                    continue;
                }

                for (Direction d : Direction.values()) {
                    if (check(lineIndex, charIndex, d)) {
                        xmasSum++;
                    }
                }
            }
        }

        System.out.println("sum: " + xmasSum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private boolean check(int lineIndex, int charIndex, Direction direction) {
        StringBuilder xmasBuilder = new StringBuilder();
        xmasBuilder.append(lines.get(lineIndex).charAt(charIndex));

        int currentLineIndex = lineIndex + direction.getRowDelta();
        int currentCharIndex = charIndex + direction.getColumnDelta();

        while (xmasBuilder.length() < 4) {
            if (currentLineIndex < 0 || currentLineIndex >= lines.size() ||
                    currentCharIndex < 0 || currentCharIndex >= lines.get(currentLineIndex).length()) {
                break;
            }

            xmasBuilder.append(lines.get(currentLineIndex).charAt(currentCharIndex));
            currentLineIndex += direction.getRowDelta();
            currentCharIndex += direction.getColumnDelta();
        }

        return xmasBuilder.length() == 4 && "XMAS".contentEquals(xmasBuilder);
    }

    @Override
    public void solvePartTwo() {

    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}