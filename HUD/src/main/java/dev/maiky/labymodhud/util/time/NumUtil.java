package dev.maiky.labymodhud.util.time;

import lombok.Getter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class NumUtil {

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    public static Time convert(String str) {
        final HashMap<String, TimeUnit> units = new HashMap<String, TimeUnit>(){{
            put("s", TimeUnit.SECONDS);
            put("m", TimeUnit.MINUTES);
            put("u", TimeUnit.HOURS);
        }};

        for (String matcher : units.keySet()) {
            TimeUnit time = units.get(matcher);
            final String substring = str.substring(0, str.length() - matcher.length());
            return new Time(time, isInt(substring) ? Integer.parseInt(substring) : 0);
        }

        return null;
    }

    public static class Time {

        private @Getter
        final TimeUnit unit;
        private @Getter
        final Integer value;

        public Time(TimeUnit unit, Integer value) {
            this.unit = unit;
            this.value = value;
        }

    }

}
