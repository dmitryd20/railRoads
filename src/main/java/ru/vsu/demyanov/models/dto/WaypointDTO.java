package ru.vsu.demyanov.models.dto;

public class WaypointDTO {

    private Integer station_id;
    private Integer arrival;
    private Integer departure;

    private StationDTO stationDTO;

    public WaypointDTO(Integer stationId, Integer arrival, Integer departure) {
        this.station_id = stationId;
        this.arrival = arrival;
        this.departure = departure;
    }

    public Integer getStationId() {
        return station_id;
    }

    void setStationId(Integer stationId) {
        this.station_id = stationId;
    }

    public Integer getArrival() {
        return arrival;
    }

    void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    public Integer getDeparture() {
        return departure;
    }

    void setDeparture(Integer departure) {
        this.departure = departure;
    }

    public StationDTO getStationDTO() {
        return stationDTO;
    }

    public void setStationDTO(StationDTO stationDTO) {
        this.stationDTO = stationDTO;
    }
}
