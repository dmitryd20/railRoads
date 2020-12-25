package ru.vsu.demyanov.service;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.entity.Route;

import java.util.List;

public interface RouteService {

    Result<List<Route>> getRoutes();
}
