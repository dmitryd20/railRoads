package ru.vsu.demyanov.models.entity;

import ru.vsu.demyanov.models.DTOConvertible;
import ru.vsu.demyanov.models.dto.*;
import ru.vsu.demyanov.models.entity.waypoints.*;

import java.util.List;

public class Route implements DTOConvertible<RouteDTO> {

    private final int number;
    private final String name;
    private final Schedule schedule;

    public Route(RouteDTO dto) {
        number = dto.getNumber();
        name = dto.getName();
        schedule = new Schedule(dto.getSchedule());
    }

    public Route(int number, String name, Schedule schedule) {
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

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public RouteDTO toDTO() {
        return new RouteDTO(
                number,
                name,
                schedule.toDTO()
        );
    }
}
