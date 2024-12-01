package aoc;

import java.io.*;
import java.util.List;
import java.util.Locale;

public interface Puzzle {

    default void readInput() {
        final String fileName = String.format("inputs/%s.txt", getClass().getSimpleName().toLowerCase(Locale.ENGLISH));
        try (FileReader fileReader = new FileReader(fileName)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    getLines().add(line);
                }
            }
        } catch (Exception e) {
            System.err.println("File '" + fileName + "' was not found");
            e.printStackTrace();
        }
    }

    void solvePartOne();

    void solvePartTwo();

    List<String> getLines();
}
