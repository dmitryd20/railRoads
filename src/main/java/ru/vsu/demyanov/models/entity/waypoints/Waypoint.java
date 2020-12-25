package ru.vsu.demyanov.models.entity.waypoints;

import ru.vsu.demyanov.models.DTOConvertible;
import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Station;
import ru.vsu.demyanov.models.entity.Time;

public class Waypoint implements DTOConvertible<WaypointDTO> {

    private Station station;
    private Time arrival;
    private Time departure;

    public Waypoint(Station station, Time arrival, Time departure) {
        this.station = station;
        this.arrival = arrival;
        this.departure = departure;
    }

    public Waypoint(WaypointDTO dto) {
        this.arrival = new Time(dto.getArrival());
        this.departure = new Time(dto.getDeparture());
        this.station = new Station(dto.getStationDTO());
    }

    public Station getStation() {
        return station;
    }

    public Time getArrival() {
        return arrival;
    }

    public Time getDeparture() {
        return departure;
    }

    @Override
    public WaypointDTO toDTO() {
        return new WaypointDTO(station.getId(), arrival.toDTO(), departure.toDTO());
    }
}

