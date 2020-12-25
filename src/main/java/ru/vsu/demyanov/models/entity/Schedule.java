package ru.vsu.demyanov.models.entity;

import ru.vsu.demyanov.models.DTOConvertible;

public class Schedule implements DTOConvertible<Integer> {

    private int bitmask;

    public Schedule(int bitmask) {
        this.bitmask = bitmask;
    }

    public Schedule(boolean monday,
                    boolean tuesday,
                    boolean wednesday,
                    boolean thursday,
                    boolean friday,
                    boolean saturday,
                    boolean sunday) {
        int mask = 0;
        if (monday)    mask += 1;
        if (tuesday)   mask += 1 << 1;
        if (wednesday) mask += 1 << 2;
        if (thursday)  mask += 1 << 3;
        if (friday)    mask += 1 << 4;
        if (saturday)  mask += 1 << 5;
        if (sunday)    mask += 1 << 6;
        bitmask = mask;
    }

    @Override
    public Integer toDTO() {
        return bitmask;
    }
}
