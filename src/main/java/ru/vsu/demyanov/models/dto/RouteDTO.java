package ru.vsu.demyanov.models.dto;

public class RouteDTO {

    private final int number;
    private final String name;
    private final int schedule;

    public RouteDTO(int number,
                    String name,
                    int schedule) {
        this.number = number;
        this.name = name;
        this.schedule = schedule;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getSchedule() {
        return schedule;
    }
}
