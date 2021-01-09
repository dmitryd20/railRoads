package ru.vsu.demyanov.dao;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.WaypointDTO;

import java.util.List;

public interface WaypointRepository {

    Result<List<WaypointDTO>> getFor(int routeId);

    public Result<WaypointDTO> getStartFor(int routeId);

    public Result<WaypointDTO> getFinishFor(int routeId);

    Result<Void> add(WaypointDTO newEntity, int routeId);

    Result<Void> change(WaypointDTO newEntity, int routeId);

    Result<Void> delete(int routeId, int station_id);
}
