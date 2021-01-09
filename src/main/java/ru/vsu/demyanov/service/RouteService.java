package ru.vsu.demyanov.service;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.entity.Route;

import java.util.List;

public interface RouteService {

    Result<Route> getRoute(int id);

    Result<List<Route>> getRoutes();

    Result<Void> addRoute(Route route);

    Result<Void> deleteRoute(int id);


}
