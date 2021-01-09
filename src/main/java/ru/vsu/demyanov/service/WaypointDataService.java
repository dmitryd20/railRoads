package ru.vsu.demyanov.service;

import ru.vsu.demyanov.dao.WaypointRepository;
import ru.vsu.demyanov.dao.database.DbWaypointRepository;
import ru.vsu.demyanov.dao.database.connection.Connector;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.entity.waypoints.FinishPoint;
import ru.vsu.demyanov.models.entity.waypoints.StartPoint;
import ru.vsu.demyanov.models.entity.waypoints.Waypoint;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class WaypointDataService implements WaypointService {

    public static class Factory {

        public WaypointService produce() {
            Connection connection = Connector.getConnection();
            return new WaypointDataService(
                    new DbWaypointRepository(connection)
            );
        }
    }

    private WaypointRepository repository;

    private WaypointDataService(WaypointRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Waypoint>> getWaypointsForRoute(int id) {
        var dtoList = repository.getFor(id);
        if (dtoList.isError()) {
            return Result.fail(dtoList.getError());
        }
        return Result.success(dtoList.getValue().stream().map(Waypoint::new).collect(Collectors.toList()));
    }

    @Override
    public Result<StartPoint> getStartForRoute(int id) {
        var dto = repository.getStartFor(id);
        if (dto.isError()) {
            return Result.fail(dto.getError());
        }
        return Result.success(new StartPoint(dto.getValue()));
    }

    @Override
    public Result<FinishPoint> getFinishForRoute(int id) {
        var dto = repository.getFinishFor(id);
        if (dto.isError()) {
            return Result.fail(dto.getError());
        }
        return Result.success(new FinishPoint(dto.getValue()));
    }

    @Override
    public Result<Void> addWaypointForRoute(int routeId, Waypoint waypoint) {
        return repository.add(waypoint.toDTO(), routeId);
    }
}
