package com.bogdan23a.adventOfCode.year2024;

import com.bogdan23a.adventOfCode.AdventDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(callSuper = true)
public class Day2 extends AdventDate {

    private final IncreasingChecker increasingChecker;
    private final DecreasingChecker decreasingChecker;
    private final ThresholdChecker thresholdChecker;

    public Day2() {
        super(2, 2024);
        this.increasingChecker = new IncreasingChecker();
        this.decreasingChecker = new DecreasingChecker();
        this.thresholdChecker = new ThresholdChecker();
    }

    @Override
    public String part1() {
        return String.valueOf(getReports().stream()
                .mapToInt(report -> isSafe(report) ? 1 : 0)
                .sum());
    }

    @Override
    protected String part2() {
        return String.valueOf(getReports().stream()
                .mapToInt(report -> isSafe(report) || isSafeWhenDampened(report) ? 1 : 0)
                .sum());
    }

    private List<List<Integer>> getReports() {
        return Arrays.stream(getInput().split("\n"))
                .map(this::getLevels)
                .collect(Collectors.toList());
    }

    private List<Integer> getLevels(String report) {
        return Arrays.stream(report.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean isSafe(List<Integer> report) {
        return (increasingChecker.isSafe(report) || decreasingChecker.isSafe(report)) && thresholdChecker.isSafe(report);
    }

    private boolean isSafeWhenDampened(List<Integer> report) {
        return IntStream.range(0, report.size())
                .anyMatch(level -> isSafe(removeLevel(report, level)));
    }

    private List<Integer> removeLevel(List<Integer> report, int level) {
        List<Integer> result = new ArrayList<>(report);
        result.remove(level);
        return result;
    }

    abstract static class ReportChecker implements LevelChecker {
        boolean isSafe(List<Integer> report) {
            return IntStream.range(0, report.size() - 1)
                    .allMatch(level -> isSafe(report, level));
        }
    }

    interface LevelChecker {
        boolean isSafe(List<Integer> report, Integer level);
    }

    static class IncreasingChecker extends ReportChecker {
        public boolean isSafe(List<Integer> report, Integer level) {
            return report.get(level) < report.get(level + 1);
        }
    }

    static class DecreasingChecker extends ReportChecker {
        public boolean isSafe(List<Integer> report, Integer level) {
            return report.get(level) > report.get(level + 1);
        }
    }

    static class ThresholdChecker extends ReportChecker {
        public boolean isSafe(List<Integer> report, Integer level) {
            int difference = abs(report.get(level) - report.get(level + 1));
            return difference >= 1 && difference <= 3;
        }
    }
}
