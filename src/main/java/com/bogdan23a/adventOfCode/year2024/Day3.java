package com.bogdan23a.adventOfCode.year2024;

import com.bogdan23a.adventOfCode.AdventDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(callSuper = true)
public class Day3 extends AdventDate {

    public Day3() {
        super(3, 2024);
    }

    @Override
    public String part1() {
        return String.valueOf(getMultiplications().stream()
                .mapToLong(this::multiply)
                .sum());
    }

    private long multiply(String s) {
        String numbers = s.substring(4, s.length() - 1);
        int first = Integer.parseInt(numbers.split(",")[0]);
        int second = Integer.parseInt(numbers.split(",")[1]);
        return (long) first * second;
    }

    private List<String> getMultiplications() {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile("mul\\(\\d*,\\d*\\)").matcher(getInput());
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    @Override
    protected String part2() {
        return String.valueOf(process(getMatches()).stream()
                .mapToLong(this::multiply)
                .sum());
    }

    private List<String> process(List<String> matches) {
        boolean isEnabled = true;
        List<String> result = new ArrayList<>();
        for (String match : matches) {
            if (match.startsWith("mul") && isEnabled) {
                result.add(match);
            } else if (match.startsWith("don't")) {
                isEnabled = false;
            } else if (match.startsWith("do")) {
                isEnabled = true;
            }
        }
        return result;
    }

    private List<String> getMatches() {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile("(mul\\(\\d*,\\d*\\))|(do\\(\\))|(don't\\(\\))").matcher(getInput());
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

}
