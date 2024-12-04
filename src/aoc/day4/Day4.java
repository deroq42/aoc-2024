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
        final long s = System.currentTimeMillis();
        int xmasSum = check("XMAS", 'X', false);
        System.out.println("xmas sum: " + xmasSum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {
        final long s = System.currentTimeMillis();
        int xmasSum = check("MAS", 'A', true);
        System.out.println("x-mas sum: " + xmasSum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private int check(String searchWord, char triggerChar, boolean crossCheck) {
        int xmasSum = 0;

        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            if (line == null || line.isEmpty()) {
                continue;
            }

            for (int charIndex = 0; charIndex < line.length(); charIndex++) {
                if (line.charAt(charIndex) != triggerChar) {
                    continue;
                }

                if (crossCheck) {
                    if (checkDirection(searchWord, lineIndex + 1, charIndex + 1, Direction.UP_LEFT)
                            && checkDirection(searchWord, lineIndex + 1, charIndex - 1, Direction.UP_RIGHT)) { // cross check
                        xmasSum++;
                    }
                } else {
                    for (Direction d : Direction.values()) { // go through directions
                        if (checkDirection(searchWord, lineIndex, charIndex, d)) { // check for XMAS in every direction
                            xmasSum++;
                        }
                    }
                }
            }
        }

        return xmasSum;
    }

    private boolean checkDirection(String searchWord, int lineIndex, int charIndex, Direction direction) {
        StringBuilder xmasBuilder = new StringBuilder();
        int currentLineIndex = lineIndex;
        int currentCharIndex = charIndex;

        while (xmasBuilder.length() < searchWord.length()) { // as long as xmasBuilder does not have the length of the search word
            if (currentLineIndex < 0 || currentLineIndex >= lines.size() ||
                    currentCharIndex < 0 || currentCharIndex >= lines.get(currentLineIndex).length()) { // prevent exceptions
                break;
            }

            xmasBuilder.append(lines.get(currentLineIndex).charAt(currentCharIndex)); // append xmasBuilder with current char of current line
            currentLineIndex += direction.getRowDelta(); // go to next line, depending on the direction
            currentCharIndex += direction.getColumnDelta(); // go to next char, depending on the direction
        }

        // returns true if xmasBuilder does have the length of the search word and the builders content
        // is equal to the search word, regardless of whether it is reversed or not
        return xmasBuilder.length() == searchWord.length()
                && (searchWord.contentEquals(xmasBuilder) || searchWord.contentEquals(xmasBuilder.reverse()));

    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}