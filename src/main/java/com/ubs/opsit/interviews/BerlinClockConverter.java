package com.ubs.opsit.interviews;

public class BerlinClockConverter implements TimeConverter {
    @Override
    public String convertTime(String aTime) {
        BerlinClock clock = new BerlinClock(aTime);
        return BerlinClock.getBerlinTime(clock);
    }
}
