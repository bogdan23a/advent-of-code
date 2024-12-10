package com.bogdan23a.adventOfCode.year2024;

import com.bogdan23a.adventOfCode.AdventDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class Day4 extends AdventDate {

    public Day4() {
        super(4, 2024);
    }

    @Override
    public String part1() {
        int xmasCounter = 0;
        String[][] letters = new String[140][140];
        String[] lines = getInput().split("\n");
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < lines[lineIndex].length(); columnIndex++) {
                letters[lineIndex][columnIndex] = lines[lineIndex].charAt(columnIndex) + "";
            }
        }
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < lines[lineIndex].length(); columnIndex++) {
                if (Objects.equals(letters[lineIndex][columnIndex], "X")) {
                    xmasCounter += countPart1(letters, lineIndex, columnIndex, 0, 0, "X");
                }
            }
        }
        return String.valueOf(xmasCounter);
    }

    private int countPart1(String[][] letters, int lineIndex, int columnIndex, int lineDirection, int columnDirection, String letter) {
        if (isOutsideMatrix(lineIndex, columnIndex, lineDirection, columnDirection, letters.length, letters[lineIndex].length) ||
                !Objects.equals(letters[lineIndex + lineDirection][columnIndex + columnDirection], letter)) {
            return 0;
        }
        if (Objects.equals(letter, "S")) {
            return 1;
        }
        if (Objects.equals(letter, "A")) {
            return countPart1(letters, lineIndex + lineDirection, columnIndex + columnDirection, lineDirection, columnDirection, "S");
        }
        if (Objects.equals(letter, "M")) {
            return countPart1(letters, lineIndex + lineDirection, columnIndex + columnDirection, lineDirection, columnDirection, "A");
        }
        return countPart1(letters, lineIndex, columnIndex, -1, 0, "M") +
                countPart1(letters, lineIndex, columnIndex, -1, 1, "M") +
                countPart1(letters, lineIndex, columnIndex, 0, 1, "M") +
                countPart1(letters, lineIndex, columnIndex, 1, 1, "M") +
                countPart1(letters, lineIndex, columnIndex, 1, 0, "M") +
                countPart1(letters, lineIndex, columnIndex, 1, -1, "M") +
                countPart1(letters, lineIndex, columnIndex, 0, -1, "M") +
                countPart1(letters, lineIndex, columnIndex, -1, -1, "M");
    }

    private boolean isOutsideMatrix(int lineIndex, int columnIndex, int lineDirection, int columnDirection, int lineSize, int columnSize) {
        return lineIndex + lineDirection < 0 || lineIndex + lineDirection >= lineSize || columnIndex + columnDirection < 0 || columnIndex + columnDirection >= columnSize;
    }

    @Override
    protected String part2() {
        int xmasCounter = 0;
        String[][] letters = new String[140][140];
        String[] lines = getInput().split("\n");
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < lines[lineIndex].length(); columnIndex++) {
                letters[lineIndex][columnIndex] = lines[lineIndex].charAt(columnIndex) + "";
            }
        }
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < lines[lineIndex].length(); columnIndex++) {
                if (Objects.equals(letters[lineIndex][columnIndex], "M")) {
                    if (lineIndex < lines.length - 2 && Objects.equals(letters[lineIndex + 2][columnIndex], "M")) {
                        xmasCounter += countPart2(letters, lineIndex, columnIndex, 1, 1, "A")
                                + countPart2(letters, lineIndex, columnIndex, 1, -1, "A");
                    }
                    if (columnIndex < lines.length - 2 && Objects.equals(letters[lineIndex][columnIndex + 2], "M")) {
                        xmasCounter += countPart2(letters, lineIndex, columnIndex, -1, 1, "A")
                                + countPart2(letters, lineIndex, columnIndex, 1, 1, "A");
                    }
                }
            }
        }
        return String.valueOf(xmasCounter);
    }

    private int countPart2(String[][] letters, int lineIndex, int columnIndex, int lineDirection, int columnDirection, String letter) {
        if (isOutsideMatrix(lineIndex, columnIndex, lineDirection, columnDirection, letters.length, letters[lineIndex].length) ||
                !Objects.equals(letters[lineIndex + lineDirection][columnIndex + columnDirection], letter)) {
            return 0;
        }
        if (Objects.equals(letter, "S")) {
            return 1;
        }
        if (Objects.equals(letter, "A")) {
            int sCount = countPart2(letters, lineIndex + lineDirection, columnIndex + columnDirection, -1, 1, "S")
                    + countPart2(letters, lineIndex + lineDirection, columnIndex + columnDirection, 1, 1, "S")
                    + countPart2(letters, lineIndex + lineDirection, columnIndex + columnDirection, 1, -1, "S")
                    + countPart2(letters, lineIndex + lineDirection, columnIndex + columnDirection, -1, -1, "S");
            if (sCount == 2) {
                return 1;
            }
            return 0;
        }
        return countPart2(letters, lineIndex, columnIndex, lineDirection, columnDirection, "A");
    }

}
