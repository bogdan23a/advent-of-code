package com.bogdan23a.adventOfCode;

import java.util.stream.IntStream;

import static com.bogdan23a.adventOfCode.Advent.FIRST_DAY;
import static com.bogdan23a.adventOfCode.Advent.LAST_DAY;

public class Year2024 {

    public static void main(String[] args) {
        IntStream.range(FIRST_DAY.getDay(), LAST_DAY.getDay() + 1).forEach(day -> {
            try {
                AdventDate adventDate = (AdventDate) Class.forName("com.bogdan23a.adventOfCode.year2024.Day" + day).getConstructor().newInstance();
                adventDate.printResults();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}