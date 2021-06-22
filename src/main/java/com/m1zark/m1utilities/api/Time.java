/*
 * Decompiled with CFR 0.151.
 */
package com.m1zark.m1utilities.api;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Time {
    private long time;

    public Time(long millis) {
        this.time = millis;
    }

    public long getTime() {
        return this.time;
    }

    public String toString(String format) {
        if (this.time <= Instant.now().toEpochMilli()) {
            return "Expired";
        }
        long millis = this.time - Instant.now().toEpochMilli();
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24L;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60L;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60L;
        return String.format(format, days, hours, minutes, seconds);
    }
}

