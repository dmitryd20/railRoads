package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.entity.Route;
import ru.vsu.demyanov.service.RouteService;
import ru.vsu.demyanov.service.StationService;
import ru.vsu.demyanov.service.WaypointService;
import ru.vsu.demyanov.view.InputHandler;
import ru.vsu.demyanov.view.MenuOption;
import ru.vsu.demyanov.view.Router;
import ru.vsu.demyanov.view.ViewModule;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public final class RouteList extends ViewModule {

    private final RouteService routeService;
    private final WaypointService waypointService;
    private final StationService stationService;

    RouteList(Router router,
              InputHandler inputHandler,
              RouteService routeService,
              WaypointService waypointService,
              StationService stationService) {
        super(router, inputHandler);
        this.routeService = routeService;
        this.waypointService = waypointService;
        this.stationService = stationService;
    }

    @Override
    protected String getTitle() {
        return Strings.RouteList.TITLE;
    }

    @Override
    protected void showContent() {
        var routes = routeService.getRoutes();
        if (routes.isError()) {
            showError(routes.getError(), this::showContent);
            return;
        }
        for (Route route : routes.getValue()) {
            System.out.println(routeInfo(route));
        }
    }

    @Override
    protected Map<Integer, MenuOption> getOptions() {
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(0, new MenuOption(Strings.Common.GO_BACK, this::close));
        return options;
    }

    private String routeInfo(Route route) {

        var start = waypointService.getStartForRoute(route.getNumber());
        var finish = waypointService.getFinishForRoute(route.getNumber());

        if (start.isError() || finish.isError()) {
            return  Strings.Error.DATA_ERROR;
        }

        var startStation = stationService.getStationById(start.getValue().getStationId());
        var finishStation = stationService.getStationById(finish.getValue().getStationId());

        if (startStation.isError() || finishStation.isError()) {
            return  Strings.Error.DATA_ERROR;
        }

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(route.getNumber())
                .append(' ')
                .append(route.getName() == null ? "" : route.getName())
                .append(" из ")
                .append(startStation.getValue().getName())
                .append(" в ")
                .append(finishStation.getValue().getName())
                .toString();

    }
}
