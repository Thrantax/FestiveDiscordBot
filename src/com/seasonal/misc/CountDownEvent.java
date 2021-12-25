package com.seasonal.misc;


import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;

public class CountDownEvent {

    public static int currentYear = Calendar.getInstance().get(Calendar.YEAR);


    public static void genericCountDown(CommandEvent commandEvent, Seasons selectedSeason) {
        LocalDate today = LocalDate.now(); //Current Date
        LocalDate nextSeasonDate = LocalDate.of(currentYear, selectedSeason.getMonth(), selectedSeason.getDate()); //Used for count down to Christmas this year or to check if Today is Christmas
        LocalDate nextSeason = nextSeasonDate.withYear(today.getYear());
        if (nextSeason.isEqual(today)) {
            commandEvent.reply("It's "+selectedSeason.getSeasonName()+" "+selectedSeason.getCheeseyTagLine()+"!");
            return;
        }
        if (nextSeason.isBefore(today)) {
            nextSeason = nextSeason.plusYears(1);
            Period p = Period.between(today, nextSeason);
            commandEvent.reply(selectedSeason.getSeasonName() + currentYear + " will be here in " + p.getMonths() + " Months, " + p.getDays() + " (Falls on " + nextSeason + ").");
        }
    }


    public static final int findEasterSundayDate(int year) {
        if (year <= 1582) {
            throw new IllegalArgumentException("Algorithm invalid before April 1583");
        }
        int golden, century, x, z, d, epact, n;

        golden = (year % 19) + 1; /* E1: metonic cycle */
        century = (year / 100) + 1; /* E2: e.g. 1984 was in 20th C */
        x = (3 * century / 4) - 12; /* E3: leap year correction */
        z = ((8 * century + 5) / 25) - 5; /* E3: sync with moon's orbit */
        d = (5 * year / 4) - x - 10;
        epact = (11 * golden + 20 + z - x) % 30; /* E5: epact */
        if ((epact == 25 && golden > 11) || epact == 24)
            epact++;
        n = 44 - epact;
        n += 30 * (n < 21 ? 1 : 0); /* E6: */
        n += 7 - ((d + n) % 7);
        if (n > 31) /* E7: */
            return n - 31; /* April */
        else
            return n;
    }

    public static final int findEasterSundayMonth(int year) {
        if (year <= 1582) {
            throw new IllegalArgumentException("Algorithm invalid before April 1583");
        }
        int golden, century, x, z, d, epact, n;

        golden = (year % 19) + 1; /* E1: metonic cycle */
        century = (year / 100) + 1; /* E2: e.g. 1984 was in 20th C */
        x = (3 * century / 4) - 12; /* E3: leap year correction */
        z = ((8 * century + 5) / 25) - 5; /* E3: sync with moon's orbit */
        d = (5 * year / 4) - x - 10;
        epact = (11 * golden + 20 + z - x) % 30; /* E5: epact */
        if ((epact == 25 && golden > 11) || epact == 24)
            epact++;
        n = 44 - epact;
        n += 30 * (n < 21 ? 1 : 0); /* E6: */
        n += 7 - ((d + n) % 7);
        if (n > 31) /* E7: */
            return 4 - 1; /* April */
        else
            return 3 - 1;
    }

    public static void countDownToEaster(CommandEvent commandEvent) {
        LocalDate today = LocalDate.now(); //Current Date
        LocalDate EasterSunday = LocalDate.of(currentYear, findEasterSundayMonth(currentYear), findEasterSundayDate(currentYear)); //Used for count down to Christmas this year or to check if Today is Christmas
        LocalDate nextEasterSundy = EasterSunday.withYear(today.getYear());
        if (nextEasterSundy.isEqual(today)) {
            commandEvent.reply("Oooh! It's spoopy time, It's Halloween today!");
            return;
        }
        if (nextEasterSundy.isBefore(today)) {
            nextEasterSundy = nextEasterSundy.plusYears(1);
            Period p = Period.between(today, nextEasterSundy);
            commandEvent.reply("Easter has been this year, but Easter " + currentYear + " will be here in " + p.getMonths() + " Months, " + p.getDays() + " (Falls on " + nextEasterSundy + ").");
        }
    }
}
