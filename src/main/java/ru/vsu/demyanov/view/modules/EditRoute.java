package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.entity.waypoints.Waypoint;
import ru.vsu.demyanov.service.RouteService;
import ru.vsu.demyanov.service.WaypointService;
import ru.vsu.demyanov.view.InputHandler;
import ru.vsu.demyanov.view.MenuOption;
import ru.vsu.demyanov.view.Router;
import ru.vsu.demyanov.view.ViewModule;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public class EditRoute extends ViewModule {

    private RouteService routeService;
    private WaypointService waypointService;

    private Integer routeNumber;

    public EditRoute(Router router,
                     InputHandler inputHandler,
                     RouteService routeService,
                     WaypointService waypointService) {
        super(router, inputHandler);
        this.routeService = routeService;
        this.waypointService = waypointService;
    }

    @Override
    protected String getTitle() {
        return Strings.EditRoute.TITLE;
    }

    @Override
    protected Map<Integer, MenuOption> getOptions() {
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(1, new MenuOption(Strings.EditRoute.ADD_STATION, this::addStation));
        options.put(0, new MenuOption(Strings.Common.GO_BACK, this::close));
        return options;
    }

    @Override
    protected void showContent() {
        System.out.println(Strings.EditRoute.ENTER_NUMBER);
        routeNumber = getInputHandler().readInt();
        if (routeNumber == null) {
            showError(ErrorType.INVALID_INPUT, this::showContent);
        }
        var route = routeService.getRoute(routeNumber);
        if (route.isError()) {
            showError(route.getError(), this::showContent);
        }
    }

    private void addStation() {
        System.out.println(Strings.EditRoute.ENTER_STATION);
        var stationId = getInputHandler().readInt();
        if (stationId == null) {
            showError(ErrorType.INVALID_INPUT, this::addStation);
            return;
        }
        System.out.println(Strings.EditRoute.ARRIVAL_TIME);
        var arrivalTime = getInputHandler().readTime(true);
        System.out.println(Strings.EditRoute.DEPARTURE_TIME);
        var departureTime = getInputHandler().readTime(true);
        var result = waypointService.addWaypointForRoute(routeNumber,
                new Waypoint(stationId, arrivalTime, departureTime));
        if (result.isError()) {
            showError(result.getError(), this::addStation);
            return;
        }
        System.out.println(Strings.EditRoute.ADD_STATION_SUCCESS);
    }
}
