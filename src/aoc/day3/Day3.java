package aoc.day3;

import aoc.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        day3.solvePartOne();
        day3.solvePartTwo();
    }

    public Day3() {
        readInput();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();
        long sum = calculateMultiplicationSum(true);
        System.out.println("sum: " + sum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {
        long s = System.currentTimeMillis();
        long sum = calculateMultiplicationSum(false);
        System.out.println("sum enabled: " + sum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private long calculateMultiplicationSum(boolean ignoreDisabled) {
        long sum = 0;

        Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        boolean enabled = true;
        for (String memory : lines) {
            if (memory == null || memory.isEmpty()) {
                continue;
            }

            Matcher mulMatcher = mulPattern.matcher(memory);
            while (mulMatcher.find()) {
                String mul = mulMatcher.group();
                if (mul == null || mul.isEmpty()) {
                    continue;
                }

                switch (mul) {
                    case "do()":
                    case "don't()":
                        enabled = ignoreDisabled || mul.equals("do()");
                        continue;
                    default:
                        if (!enabled) {
                            continue;
                        }

                        String[] numbers = mul.split("\\(")[1].split("\\)")[0].split(",");
                        try {
                            sum += Long.parseLong(numbers[0]) * Long.parseLong(numbers[1]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number input within mul");
                        }
                        break;
                }
            }
        }

        return sum;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}
