package com.seasonal.misc;

import java.time.Month;
import java.util.Random;

public enum Seasons {
    HALLOWEEN(31, Month.OCTOBER, "Halloween", new String[]{"blah blah blah"}),
    XMAS(25, Month.DECEMBER, "Christmas", new String[]{"blah blah blah"});

    private int date;
    private Month month;
    private String seasonName;
    private String[] cheeseyTagLine;

    Seasons(int date, Month month, String seasonName, String[] cheeseyTagLine) {
        this.date = date;
        this.month = month;
        this.seasonName = seasonName;
        this.cheeseyTagLine = cheeseyTagLine;
    }

    public int getDate() {
        return date;
    }

    public Month getMonth() {
        return month;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getCheeseyTagLine() {
        Random random = new Random();
        int index = random.nextInt(cheeseyTagLine.length);
        return cheeseyTagLine[index];
    }
}
