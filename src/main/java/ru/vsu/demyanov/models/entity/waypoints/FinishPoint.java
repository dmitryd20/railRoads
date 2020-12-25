package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Station;
import ru.vsu.demyanov.models.entity.Time;

public class FinishPoint extends Waypoint {

    public FinishPoint(Station station, Time arrival) {
        super(station, arrival, null);
    }

    public FinishPoint(WaypointDTO dto) {
        this(new Station(dto.getStationDTO()), new Time(dto.getArrival()));
    }

    @Override
    public Time getDeparture() {
        return null;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(getStation().getId(), getArrival().toDTO(), null);
    }
}
