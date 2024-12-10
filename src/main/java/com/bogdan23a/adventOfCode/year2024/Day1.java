package com.bogdan23a.adventOfCode.year2024;

import com.bogdan23a.adventOfCode.AdventDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(callSuper = true)
public class Day1 extends AdventDate {

    public Day1() {
        super(1, 2024);
    }
    @Override
    public String part1() {
        List<Integer> leftList = getList(0).stream().sorted().toList();
        List<Integer> rightList = getList(1).stream().sorted().toList();
        return String.valueOf(IntStream.range(0, leftList.size()).map(i -> abs(leftList.get(i) - rightList.get(i))).sum());
    }

    @Override
    protected String part2() {
        List<Integer> leftList = getList(0);
        List<Integer> rightList = getList(1);
        return String.valueOf(leftList.stream()
                .mapToLong(leftLocationId -> leftLocationId * rightList.stream().filter(rightLocationId -> Objects.equals(leftLocationId, rightLocationId)).count())
                .sum());
    }

    private List<Integer> getList(int index) {
        return Arrays.stream(getInput().split("\n")).map(line -> line.split("   ")[index]).mapToInt(Integer::parseInt).boxed().toList();
    }
}
