package com.ubs.opsit.interviews;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BerlinClock {

    private static final SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
    public static final String NEWLINE = "\n";
    private final String time;


    public BerlinClock(String aTime) throws RuntimeException {
        try {
            this.time = aTime;
            validateFormat(time);
        } catch (ParseException pse) {
            throw new RuntimeException("Invalid time format, expected HH:mm:ss");
        }

    }

    private void validateFormat(String time) throws ParseException {
        HHMMSS.parse(time);
    }

    public String getTime() {
        return time;
    }

    public static String getBerlinTime(BerlinClock clock) {
        String[] timeSplit = clock.getTime().split(":");
        StringBuffer time = new StringBuffer();
        time.append(new BerlinSecond(Integer.parseInt(timeSplit[2])).getBerlinSecond()).append(NEWLINE);
        time.append(new BerlinHour(Integer.parseInt(timeSplit[0])).getBerlinHour()).append(NEWLINE);
        time.append(new BerlinMinute(Integer.parseInt(timeSplit[1])).getBerlinMinute());

        return time.toString();

    }
}

class BerlinHour {

    private final int hour;
    private static final int FIRST_ROW_HOUR = 5;
    private static final String RED_HOUR = "R";

    private String berlinHour = "";

    private String[] firstRow = new String[]{"O","O","O","O"};
    private String[] secondRow = new String[] {"O","O","O","O"};

    BerlinHour(int aHour) {
        this.hour = aHour;
        computeBerlinHour();
    }

    private void computeBerlinHour() {
        computeFirstRow();
    }

    private void computeFirstRow() {

        int hr = getHour();
        int counter = 0;
        while(hr / FIRST_ROW_HOUR >= 1) {
            firstRow[counter] = RED_HOUR;
            counter++;
            hr = hr - FIRST_ROW_HOUR;
        }

        setBerlinHour(Arrays.asList(firstRow).stream().collect(Collectors.joining()));

        computeSecondRow(hr);

    }

    private void computeSecondRow(int hour) {

        for(int i = 0; i < hour; i++) {
            secondRow[i] = RED_HOUR;
        }

        setBerlinHour(Arrays.asList(secondRow).stream().collect(Collectors.joining()));
    }

    public int getHour() {
        return hour;
    }

    public String getBerlinHour() {
        return berlinHour;
    }

    public void setBerlinHour(String hour) {
        StringBuffer buff = new StringBuffer(StringUtils.isEmpty(getBerlinHour()) ? hour : getBerlinHour() + BerlinClock.NEWLINE + hour);
        this.berlinHour = buff.toString();

    }

}

class BerlinMinute {

    private final int minute;
    private static final int FIRST_ROW_MINUTE = 5;
    private static final int BERLIN_QUATER = 3;
    private static final String RED_ZONE = "R";
    private static final String YELLOW_ZONE = "Y";
    private String berlinMinute = "";

    private String[] firstRow = new String[]{"O","O","O","O","O","O","O","O","O","O","O"};
    private String[] secondRow = new String[] {"O","O","O","O"};


    BerlinMinute(int aMinute) {
        this.minute = aMinute;
        computeBerlinMinute();
    }


    private void computeBerlinMinute() {
        computeFirstRow();
    }

    private void computeFirstRow() {

        int min = getMinute();
        int counter = 0;
        while(min / FIRST_ROW_MINUTE >= 1) {
            firstRow[counter] = YELLOW_ZONE;
            counter++;
            min = min - FIRST_ROW_MINUTE;
        }
        computeQuater();

        setBerlinMinute(Arrays.asList(firstRow).stream().collect(Collectors.joining()));

        computeSecondRow(min);

    }

    private void computeQuater() {
        int min = getMinute();
        int quarter = min / (FIRST_ROW_MINUTE * BERLIN_QUATER);

        for(int i = 1, qCounter = BERLIN_QUATER; i <= quarter;) {
            firstRow[qCounter-1] = RED_ZONE;
            qCounter = BERLIN_QUATER*++i;
        }
    }

    private void computeSecondRow(int hour) {

        for(int i = 0; i < hour; i++) {
            secondRow[i] = YELLOW_ZONE;
        }

        setBerlinMinute(Arrays.asList(secondRow).stream().collect(Collectors.joining()));
    }

    public int getMinute() {
        return minute;
    }

    public String getBerlinMinute() {
        return berlinMinute;
    }

    public void setBerlinMinute(String minute) {
        StringBuffer buff = new StringBuffer(StringUtils.isEmpty(getBerlinMinute()) ? minute : getBerlinMinute() + BerlinClock.NEWLINE + minute);
        this.berlinMinute = buff.toString();
    }


}

class BerlinSecond {

    private static final String YELLOW_ZONE = "Y";

    private final int second;
    private String berlinSecond = "O";


    BerlinSecond(int aSecond) {
        this.second = aSecond;
        computeBerlinSecond();
    }


    private void computeBerlinSecond() {
        if(getSecond() % 2 == 0) {
            setBerlinSecond(YELLOW_ZONE);
        }
    }

    public int getSecond() {
        return second;
    }

    public String getBerlinSecond() {
        return berlinSecond;
    }

    public void setBerlinSecond(String berlinSecond) {
        this.berlinSecond = berlinSecond;
    }
}
