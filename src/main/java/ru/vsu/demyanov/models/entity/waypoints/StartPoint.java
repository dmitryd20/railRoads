package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Station;
import ru.vsu.demyanov.models.entity.Time;

public class StartPoint extends Waypoint {

    public StartPoint(Station station, Time departure) {
        super(station, null, departure);
    }

    public StartPoint(WaypointDTO dto) {
        this(new Station(dto.getStationDTO()), new Time(dto.getDeparture()));
    }

    @Override
    public Time getArrival() {
        return null;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(getStation().getId(), null, getDeparture().toDTO());
    }
}
