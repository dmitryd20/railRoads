package ru.vsu.demyanov.models.entity;

import ru.vsu.demyanov.models.DTOConvertible;

public class Time implements DTOConvertible<Integer>, Comparable<Time> {

    private final int time;

    public Time(int time) {
        this.time = time;
    }

    public Time(int day, int hour, int minute) {
        this.time = day * 24 * 60 + hour * 60 + minute;
    }

    public int getDay() {
        return time / (24 * 60);
    }

    public int getHour() {
        return (time % (24 * 60)) / 60;
    }

    public int getMinute() {
        return time % 60;
    }

    public int getFullTime() {
        return time;
    }

    @Override
    public Integer toDTO() {
        return time;
    }

    @Override
    public int compareTo(Time another) {
        return Integer.compare(time, another.time);
    }
}
