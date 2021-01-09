package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Time;

public class FinishPoint extends Waypoint {

    public FinishPoint(int stationId, Time arrival) {
        super(stationId, arrival, null);
    }

    public FinishPoint(WaypointDTO dto) {
        this(dto.getStationId(), new Time(dto.getArrival()));
    }

    @Override
    public Time getDeparture() {
        return null;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(getStationId(), getArrival().toDTO(), null);
    }
}
