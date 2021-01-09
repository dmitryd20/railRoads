package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.DTOConvertible;
import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Time;

public class Waypoint implements DTOConvertible<WaypointDTO> {

    private final int stationId;
    private final Time arrival;
    private final Time departure;

    public Waypoint(int stationId, Time arrival, Time departure) {
        this.stationId = stationId;
        this.arrival = arrival;
        this.departure = departure;
    }

    public Waypoint(WaypointDTO dto) {
        this.stationId = dto.getStationId();
        this.arrival = new Time(dto.getArrival());
        this.departure = new Time(dto.getDeparture());
    }

    public int getStationId() {
        return stationId;
    }

    public Time getArrival() {
        return arrival;
    }

    public Time getDeparture() {
        return departure;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(stationId, arrival.toDTO(), departure.toDTO());
    }
}

