package aoc.day5;

import aoc.Puzzle;


import java.util.*;

public class Day5 implements Puzzle {

    private final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        day5.solvePartOne();
        day5.solvePartTwo();
    }

    public Day5() {
        readInput();
    }

    @Override
    public void solvePartOne() {
        long s = System.currentTimeMillis();
        long middleSum = processUpdates(false);
        System.out.println("Middle sum: " + middleSum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    @Override
    public void solvePartTwo() {
        long s = System.currentTimeMillis();
        long middleSum = processUpdates(true);
        System.out.println("Middle sum: " + middleSum);
        System.out.println("Took " + (System.currentTimeMillis() - s) + "ms");
    }

    private long processUpdates(boolean correctInvalidUpdates) {
        List<int[]> pageOrderingRules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        long middleSum = 0;

        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                continue;
            }

            boolean isPageOrderingRule = line.contains("|");
            if (isPageOrderingRule) {
                String[] pageOrderingRule = line.split("\\|");
                if (pageOrderingRule.length != 2) {
                    continue;
                }
                pageOrderingRules.add(new int[]{Integer.parseInt(pageOrderingRule[0]), Integer.parseInt(pageOrderingRule[1])});
            } else {
                String[] pageNumbers = line.split(",");
                if (pageNumbers.length == 0) {
                    continue;
                }

                List<Integer> update = new ArrayList<>();
                for (String pageNumber : pageNumbers) {
                    update.add(Integer.parseInt(pageNumber));
                }
                updates.add(update);
            }
        }

        for (List<Integer> update : updates) {
            boolean isValid = isUpdateValid(update, pageOrderingRules);
            if ((correctInvalidUpdates && !isValid) || (!correctInvalidUpdates && isValid)) {
                if (!isValid) {
                    update = sortUpdate(update, pageOrderingRules);
                }
                middleSum += update.get(update.size() / 2);
            }
        }

        return middleSum;
    }

    private boolean isUpdateValid(List<Integer> update, List<int[]> pageOrderingRules) {
        Map<Integer, Integer> pageIndices = new HashMap<>();
        for (int pageIndex = 0; pageIndex < update.size(); pageIndex++) {
            pageIndices.put(update.get(pageIndex), pageIndex);
        }

        for (int[] pageOrderingRule : pageOrderingRules) {
            int before = pageOrderingRule[0];
            int after = pageOrderingRule[1];
            if (pageIndices.containsKey(before) && pageIndices.containsKey(after)) {
                if (pageIndices.get(before) > pageIndices.get(after)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<Integer> sortUpdate(List<Integer> update, List<int[]> pageOrderingRules) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int page : update) {
            graph.put(page, new HashSet<>());
            inDegree.put(page, 0);
        }

        for (int[] rule : pageOrderingRules) {
            int before = rule[0];
            int after = rule[1];

            if (update.contains(before) && update.contains(after)) {
                graph.get(before).add(after);
                inDegree.put(after, inDegree.get(after) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int page : update) {
            if (inDegree.get(page) == 0) {
                queue.offer(page);
            }
        }

        List<Integer> sortedUpdate = new ArrayList<>();
        while (!queue.isEmpty()) {
            int page = queue.poll();
            sortedUpdate.add(page);

            for (int neighbor : graph.get(page)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return sortedUpdate;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}