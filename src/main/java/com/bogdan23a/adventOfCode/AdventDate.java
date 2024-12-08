package com.bogdan23a.adventOfCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;

import static com.bogdan23a.adventOfCode.constants.Constants.OUTPUT_MESSAGE_FORMAT;
import static java.lang.String.format;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AdventDate {
    private int year = 0;
    private int day = 0;

    public AdventDate(int day, int year) {
        this.day = day;
        this.year = year;
    }

    protected abstract String part1();
    protected abstract String part2();

    protected String getInput() {
        try {
            return Files.readString(new File("src/main/resources/inputs/" + year + "/" + day + ".txt").toPath());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected void printResults() {
        System.out.printf((OUTPUT_MESSAGE_FORMAT) + "%n", day, 1, part1());
        System.out.printf((OUTPUT_MESSAGE_FORMAT) + "%n", day, 2, part2());
    }
}
