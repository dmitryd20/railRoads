package ru.vsu.demyanov.service;

import ru.vsu.demyanov.dao.Repository;
import ru.vsu.demyanov.dao.database.DbRouteRepository;
import ru.vsu.demyanov.dao.database.DbStationRepository;
import ru.vsu.demyanov.dao.database.DbWaypointRepository;
import ru.vsu.demyanov.dao.WaypointRepository;
import ru.vsu.demyanov.dao.database.connection.Connector;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.RouteDTO;
import ru.vsu.demyanov.models.dto.StationDTO;
import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.Route;
import ru.vsu.demyanov.models.entity.waypoints.FinishPoint;
import ru.vsu.demyanov.models.entity.waypoints.StartPoint;
import ru.vsu.demyanov.models.entity.waypoints.Waypoint;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RouteDataService implements RouteService {

    public static class Factory {

        public RouteService produce() {
            Connection connection = Connector.getConnection();
            return new RouteDataService(
                    new DbRouteRepository(connection),
                    new DbStationRepository(connection),
                    new DbWaypointRepository(connection));
        }
    }

    private final Repository<RouteDTO> routeRepository;
    private final Repository<StationDTO> stationRepository;
    private final WaypointRepository waypointRepository;

    private RouteDataService(Repository<RouteDTO> routeRepository,
                             Repository<StationDTO> stationRepository,
                             WaypointRepository waypointRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.waypointRepository = waypointRepository;
    }

    public Result<Route> get(int id) {
        var dto = routeRepository.get(id);
        if (dto.isError()) {
            return Result.fail(dto.getError());
        }
        return Result.success(new Route(routeRepository.get(id).getValue()));
    }

    @Override
    public Result<List<Route>> getRoutes() {
        var dtoList = routeRepository.getAll();
        if (dtoList.isError()) {
            return Result.fail(dtoList.getError());
        }
        var result = dtoList.getValue().stream().map(Route::new).collect(Collectors.toList());
        for (Route route: result) {
            var waypointsDTO = waypointRepository.getFor(route.getNumber());
            if (waypointsDTO.isError()) {
                return Result.fail(waypointsDTO.getError());
            }
            for (WaypointDTO waypointDTO: waypointsDTO.getValue()) {
                var station = stationRepository.get(waypointDTO.getStationId());
                if (station.isError()) {
                    return Result.fail(station.getError());
                }
                waypointDTO.setStationDTO(station.getValue());
            }
            var waypoints = waypointsDTO.getValue().stream().map(Waypoint::new);
            var start = waypointsDTO
                    .getValue()
                    .stream()
                    .filter(waypoint -> waypoint.getArrival() == null)
                    .findFirst()
                    .get();
            var middlePoints = waypointsDTO
                    .getValue()
                    .stream()
                    .filter(waypoint -> waypoint.getArrival() != null
                            && waypoint.getDeparture() != null)
                    .sorted(Comparator.comparingInt(WaypointDTO::getArrival))
                    .map(Waypoint::new)
                    .collect(Collectors.toList());
            var finish = waypointsDTO
                    .getValue()
                    .stream()
                    .filter(waypoint -> waypoint.getDeparture()== null)
                    .findFirst()
                    .get();
            route.setStartPoint(new StartPoint(start));
            route.setWaypoints(middlePoints);
            route.setFinishPoint(new FinishPoint(finish));
        }
        return Result.success(result);
    }
}
