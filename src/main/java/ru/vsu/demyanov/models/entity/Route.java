package ru.vsu.demyanov.models.entity;

import ru.vsu.demyanov.models.DTOConvertible;
import ru.vsu.demyanov.models.dto.*;
import ru.vsu.demyanov.models.entity.waypoints.*;

import java.util.List;

public class Route implements DTOConvertible<RouteDTO> {

    private int number;
    private String name;
    private Schedule schedule;
    private StartPoint startPoint;
    private FinishPoint finishPoint;
    private List<Waypoint> waypoints;

    public Route(int number,
                 String name,
                 Schedule schedule,
                 StartPoint startPoint,
                 FinishPoint finishPoint,
                 List<Waypoint> waypoints) {
        this.number = number;
        this.name = name;
        this.schedule = schedule;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.waypoints = waypoints;
    }

    public Route(RouteDTO dto) {
        number = dto.getNumber();
        name = dto.getName();
        schedule = new Schedule(dto.getSchedule());
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

    public StartPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(StartPoint startPoint) {
        this.startPoint = startPoint;
    }

    public FinishPoint getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(FinishPoint finishPoint) {
        this.finishPoint = finishPoint;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
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
