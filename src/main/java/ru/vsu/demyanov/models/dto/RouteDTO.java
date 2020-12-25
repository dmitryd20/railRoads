package ru.vsu.demyanov.models.dto;

public class RouteDTO {

    private int number;
    private String name;
    private int schedule;

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
