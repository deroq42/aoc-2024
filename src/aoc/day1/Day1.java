package aoc.day1;

import aoc.Puzzle;

import java.util.*;

public class Day1 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        day1.solvePartOne();
        day1.solvePartTwo();
    }

    public Day1() {
        readInput();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();

        List<Long>[] numbers = parseInput();
        List<Long> leftNumbers = numbers[0];
        List<Long> rightNumbers = numbers[1];

        if (leftNumbers.size() != rightNumbers.size()) {
            System.err.println("left side has more numbers than right side");
            return;
        }

        Collections.sort(leftNumbers); // sort left numbers ascending
        Collections.sort(rightNumbers); // sort right numbers ascending

        long totalDistance = 0;
        for (int index = 0; index < leftNumbers.size(); index++) {
            totalDistance += Math.abs(leftNumbers.get(index) - rightNumbers.get(index)); // add the difference between the left number and right number
        }

        System.out.println("distance: " + totalDistance);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {
        long s = System.currentTimeMillis();
        List<Long>[] numbers = parseInput();
        List<Long> leftNumbers = numbers[0];
        List<Long> rightNumbers = numbers[1];

        if (leftNumbers.size() != rightNumbers.size()) {
            System.err.println("left side has more numbers than right side");
            return;
        }

        Map<Long, Integer> rightNumbersCount = new HashMap<>();
        rightNumbers.forEach(right -> rightNumbersCount.put(right, rightNumbersCount.getOrDefault(right, 0) + 1));

        long similarityScore = 0;
        for (long left : leftNumbers) {
            similarityScore += left * rightNumbersCount.getOrDefault(left, 0); // add the product of the left number and its count in the right numbers list to the similarity score
        }

        System.out.println("similarity score: " + similarityScore);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private List<Long>[] parseInput() {
        List<Long> leftNumbers = new ArrayList<>();
        List<Long> rightNumbers = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                continue;
            }

            String[] leftRight = line.split(" {3}"); // split 3 spaces, because input = "98415   86712"
            if (leftRight.length != 2) { // length of array should be 2, because only two numbers per line
                continue;
            }

            try {
                leftNumbers.add(Long.parseLong(leftRight[0].trim())); // left = index 0
                rightNumbers.add(Long.parseLong(leftRight[1].trim())); // right = index 1
            } catch (NumberFormatException e) {
                System.err.println("Input contains invalid number: " + line);
            }
        }

        return new List[]{leftNumbers, rightNumbers};
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}
