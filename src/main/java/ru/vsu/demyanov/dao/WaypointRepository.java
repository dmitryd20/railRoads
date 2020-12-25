package ru.vsu.demyanov.dao;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.RouteDTO;
import ru.vsu.demyanov.models.dto.WaypointDTO;

import java.util.List;

public interface WaypointRepository {

    public Result<List<WaypointDTO>> getFor(int routeId);

    public Result<Void> add(WaypointDTO newEntity, int routeId);

    public Result<Void> change(WaypointDTO newEntity, int routeId);

    public Result<Void> delete(int routeId, int station_id);
}
