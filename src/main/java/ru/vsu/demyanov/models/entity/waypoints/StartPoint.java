package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Time;

public class StartPoint extends Waypoint {

    public StartPoint(int stationId, Time departure) {
        super(stationId, null, departure);
    }

    public StartPoint(WaypointDTO dto) {
        this(dto.getStationId(), new Time(dto.getDeparture()));
    }

    @Override
    public Time getArrival() {
        return null;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(getStationId(), null, getDeparture().toDTO());
    }
}
