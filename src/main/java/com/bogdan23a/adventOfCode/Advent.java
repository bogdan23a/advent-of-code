package com.bogdan23a.adventOfCode;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Advent {
    FIRST_DAY(1),
    LAST_DAY(25);

    private final int day;

    Advent(int day) {
        this.day = day;
    }
}
