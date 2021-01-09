package ru.vsu.demyanov.service;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.entity.waypoints.FinishPoint;
import ru.vsu.demyanov.models.entity.waypoints.StartPoint;
import ru.vsu.demyanov.models.entity.waypoints.Waypoint;

import java.util.List;

public interface WaypointService {

    Result<List<Waypoint>> getWaypointsForRoute(int id);

    Result<StartPoint> getStartForRoute(int id);

    Result<FinishPoint> getFinishForRoute(int id);

    Result<Void> addWaypointForRoute(int routeId, Waypoint waypoint);
}
