package com.lavalliere.daniel.projects;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.time.temporal.TemporalAdjuster;
import java.time.zone.ZoneRules;
import java.util.Locale;

public class NewJavaTime {

    /*
     * Issues with original Date / Time implementation
     * Date does actually an actual date but a point in time in millis
     * Year start offset is 1900 while for months it is 0
     * Not thread safe and not immutable
     */

    // These are all immutable / thread safe classes
    public NewJavaTime doLocalDate() {
        // No time or TZ info for dates
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean isLeapYear = date.isLeapYear();
        LocalDate now = LocalDate.now();

        // throws DateTimeParseException
        LocalDate parsedDate = LocalDate.parse("2014-03-18");

        // Using TemporalField but can be simplified with direct method
        year = date.get(ChronoField.YEAR);
        int monthVal = date.get(ChronoField.MONTH_OF_YEAR);
        day = date.get(ChronoField.DAY_OF_MONTH);

        return this;
    }

    public NewJavaTime doGetLocalTime() {
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int seconds = time.getSecond();

        // throws DateTimeParseException
        LocalTime timeParsed = LocalTime.parse("13:45:20");
        return this;
    }

    public NewJavaTime doGetLocalDateTime() {
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13,45, 20);
        LocalDateTime dt2 = LocalDateTime.of(dateNow, timeNow);
        LocalDateTime dt3 = dateNow.atTime(13, 45, 20);
        LocalDateTime dt4 = dateNow.atTime(timeNow);
        LocalDateTime dt5 = timeNow.atDate(dateNow);

        LocalDate localDate = dt1.toLocalDate();
        LocalTime localTime = dt1.toLocalTime();
        return this;
    }

    public NewJavaTime doGetInstant() {
        // Instant is the number of seconds since epoch (Jan 1, 1970 UTC)
        // also supports nano seconds precisions
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(3, 0);

        // In Java SE 7 and later, any number of underscore characters (_) can appear anywhere between digits
        // in a numerical literal. This feature enables you, for example, to separate groups of digits in numeric literals,
        // which can improve the readability of your code.
        Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);
        Instant now = Instant.now();

        try {
            long day = now.get(ChronoField.DAY_OF_MONTH);
        } catch(UnsupportedTemporalTypeException _) {
            // Throws since instant is for machine and does not support individual human fields
            // but you can use Duration and Period classes
        }
        return this;
    }

    public NewJavaTime goGetDurationAndPeriod() {
        LocalTime nowTime = LocalTime.now();
        LocalTime thenTime = nowTime.minus(5, ChronoUnit.HOURS);
        thenTime = nowTime.minusHours(2);
        Duration d1 = Duration.between(thenTime, nowTime);

        // https://docs.oracle.com/cd/E84527_01/wcs/tag-ref/MISC/TimeZones.html
        LocalDate oldNowDate = LocalDate.now().minus(Period.ofDays(1));
        LocalDate nowDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("America/Montreal"));
        LocalDate thenDate = nowDate.minus(1, ChronoUnit.MONTHS);
        thenDate = thenDate.minusMonths(1);

        try {
            Duration d2 = Duration.between(thenDate, nowDate);
        } catch (UnsupportedTemporalTypeException _) {
            // Not supported as Duration is an amount in seconds and have nanoseconds
            // which date does not have

            // When need to model amount of time in terms of days, month, days
            Period period = Period.between(thenDate, nowDate);
            long days = period.getDays();
        }

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime thenDateTime = nowDateTime.minus(5, ChronoUnit.DAYS);
        thenDateTime = nowDateTime.minusDays(1);
        Duration d3 = Duration.between(thenDateTime, nowDateTime);

        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime dateInNewYork = nowDate.atStartOfDay(newYorkZone);
        ZonedDateTime dateInTokyo = nowDate.atStartOfDay(tokyoZone);
        Duration diff1 = Duration.between(dateInNewYork, dateInTokyo);
        long hours = diff1.get(ChronoUnit.SECONDS);
        
        Instant nowInstant = Instant.now();
        Instant thenInstant = nowInstant.minus(360, ChronoUnit.SECONDS);
        thenInstant = nowInstant.minusSeconds(60);

        try {
            Duration badDuration = Duration.between(LocalDateTime.now(), Instant.now());
        } catch (DateTimeException _) {
            // Cannot mix units meant for machine and those meant for human
        }
        return this;
    }

    public NewJavaTime goDoDateManipulations() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.withMonth(9);
        date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

        LocalDate date5 = date4.plusMonths(3);
        date5 = date4
                .minusYears(1)
                .plus(4, ChronoUnit.DAYS);
        return this;
    }

    public NewJavaTime doGetZones() {
        ZoneId amerZoneId = ZoneId.of("America/New_York");

        LocalDateTime now = LocalDateTime.now();
        ZoneId montrealZoneId = ZoneId.of("America/Montreal");
        ZoneOffset zoneOffSet = montrealZoneId.getRules().getOffset(now);

        ZoneId berlinZoneId = ZoneId.of("Europe/Berlin");
        ZonedDateTime oldDate = ZonedDateTime.now(berlinZoneId);
        ZonedDateTime newDate = oldDate.withZoneSameInstant(montrealZoneId);

        zoneOffSet = ZoneOffset.of("+02:00");
        OffsetDateTime date = OffsetDateTime.now(zoneOffSet);
        return this;
    }

    // Reusable TemporalAdjuster
    private TemporalAdjuster nextWorkingDay = (TemporalAdjuster) TemporalAdjusters.ofDateAdjuster(
            temporal -> {
                DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                int dayToAdd = 1;
                if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                return temporal.plus(dayToAdd, ChronoUnit.DAYS);
            }
    );


    public NewJavaTime doTemporalAdjusters() {
        // To perform more advanced date/time operation, you can pass to an overloaded version of the with() method,
        // a TemporalAdjuster that provides required customizations

        // Using lambda TemporalAdjuster
        LocalDate date = LocalDate.now().with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        // Using variable TemporalAdjuster
        date = date.with(nextWorkingDay);
        return this;
    }

    // NOTE all instances of DateTimeFormatter are thread safe
    // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
    public NewJavaTime doFormatAndParseDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20140318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2014-03-18

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr = LocalDate.of(2014,3, 18).format(formatter1);
        LocalDate date3 = LocalDate.parse(dateStr, formatter1);

        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        dateStr = LocalDate.of(2014, 3, 18).format(italianFormatter);
        LocalDate date4 = LocalDate.parse(dateStr, italianFormatter);

        italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        return this;
    }

    public NewJavaTime doIt() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId montrealZoneId = ZoneId.of("America/Montreal");
        ZoneOffset zoneOffSet = montrealZoneId.getRules().getOffset(now);
        ZoneRules zoneRules = ZoneRules.of(zoneOffSet);

        ZonedDateTime zonedNow = LocalDateTime.now().atZone(montrealZoneId);
        LocalDate date = LocalDate.of(2014, 3, 18);
        ZonedDateTime zonedThen = date.atStartOfDay(montrealZoneId);

        LocalDateTime thenDateTime = LocalDateTime.of(2014, 3, 18, 15,13,45);
        zonedThen = thenDateTime.atZone(montrealZoneId);
        Instant thenInstant = thenDateTime.toInstant(zoneOffSet);
        LocalDateTime setDateTime = LocalDateTime.ofInstant(thenInstant, montrealZoneId);

        Instant nowInstant = Instant.now();
        zonedNow = nowInstant.atZone(montrealZoneId);

        ZoneOffset newYorkOffset = ZoneOffset.of("-05.00");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(thenDateTime, newYorkOffset);

        // The designers of Date and time API recommend using LocalDate instead of ChronologicalDate
        // To prevent assumptions about number of days in months or number of months in a year
        JapaneseDate japaneseDate = JapaneseDate.from(date);
        Chronology japaneseChoronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate nowJapanChronology = japaneseChoronology.dateNow();
        return this;
    }



    public static void demo() {
        new NewJavaTime()
                .doGetInstant()
                .doGetLocalTime()
                .doGetLocalDateTime()
                .goGetDurationAndPeriod()
                .doLocalDate()
                .goDoDateManipulations()
                .doGetZones()
                .doTemporalAdjusters()
                .doFormatAndParseDate()
        ;
    }
}
