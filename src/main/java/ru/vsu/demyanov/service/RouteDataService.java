package ru.vsu.demyanov.service;

import ru.vsu.demyanov.dao.Repository;
import ru.vsu.demyanov.dao.database.DbRouteRepository;
import ru.vsu.demyanov.dao.database.connection.Connector;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.RouteDTO;
import ru.vsu.demyanov.models.entity.Route;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class RouteDataService implements RouteService {

    public static class Factory {

        public RouteService produce() {
            Connection connection = Connector.getConnection();
            return new RouteDataService(
                    new DbRouteRepository(connection)
            );
        }
    }

    private final Repository<RouteDTO> repository;

    private RouteDataService(Repository<RouteDTO> repository) {
        this.repository = repository;
    }

    @Override
    public Result<Route> getRoute(int id) {
        var dto = repository.get(id);
        if (dto.isError()) {
            return Result.fail(dto.getError());
        }
        return Result.success(new Route(dto.getValue()));
    }

    @Override
    public Result<List<Route>> getRoutes() {
        var dtoList = repository.getAll();
        if (dtoList.isError()) {
            return Result.fail(dtoList.getError());
        }
        return Result.success(dtoList.getValue().stream().map(Route::new).collect(Collectors.toList()));
    }

    @Override
    public Result<Void> addRoute(Route route) {
        return repository.add(route.toDTO());
    }

    @Override
    public Result<Void> deleteRoute(int id) {
        return repository.delete(id);
    }
}
