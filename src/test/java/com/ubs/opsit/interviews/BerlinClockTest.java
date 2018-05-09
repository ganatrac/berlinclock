package com.ubs.opsit.interviews;

import org.junit.Test;
import static org.junit.Assert.*;

public class BerlinClockTest {

    @Test(expected = RuntimeException.class)
    public void assertWrongFormat() {
        BerlinClock b1 = new BerlinClock("23:00:");
        BerlinClock b2 = new BerlinClock("23300:00");
        BerlinClock b3 = new BerlinClock("23:00445");
        BerlinClock b4 = new BerlinClock("23:00:75");

    }

    @Test
    public void assertRightFormat() {
        BerlinClock b1 = new BerlinClock("13:00:56");
        BerlinClock b2 = new BerlinClock("09:40:00");
        BerlinClock b3 = new BerlinClock("23:00:45");
        BerlinClock b4 = new BerlinClock("00:00:00");
    }

    @Test
    public void assertBerlinHour() {
        BerlinHour b1 = new BerlinHour(13);
        assertEquals("RROO"+BerlinClock.NEWLINE+"RRRO", b1.getBerlinHour());
        BerlinHour b2 = new BerlinHour(9);
        assertEquals("ROOO"+BerlinClock.NEWLINE+"RRRR", b2.getBerlinHour());
        BerlinHour b3 = new BerlinHour(5);
        assertEquals("ROOO"+BerlinClock.NEWLINE+"OOOO", b3.getBerlinHour());
        BerlinHour b4 = new BerlinHour(3);
        assertEquals("OOOO"+BerlinClock.NEWLINE+"RRRO", b4.getBerlinHour());
    }

    @Test
    public void assertBerlinMinute() {
        BerlinMinute b1 = new BerlinMinute(33);
        assertEquals("YYRYYROOOOO"+BerlinClock.NEWLINE+"YYYO", b1.getBerlinMinute());
        BerlinMinute b2 = new BerlinMinute(59);
        assertEquals("YYRYYRYYRYY"+BerlinClock.NEWLINE+"YYYY", b2.getBerlinMinute());
        BerlinMinute b3 = new BerlinMinute(45);
        assertEquals("YYRYYRYYROO"+BerlinClock.NEWLINE+"OOOO", b3.getBerlinMinute());
    }

    @Test
    public void asertBerlinSecond() {
        BerlinSecond b1 = new BerlinSecond(4);
        assertEquals("Y", b1.getBerlinSecond());
        BerlinSecond b2 = new BerlinSecond(45);
        assertEquals("O", b2.getBerlinSecond());
    }
}
