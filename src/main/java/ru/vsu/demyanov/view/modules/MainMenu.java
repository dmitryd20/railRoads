package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.service.RouteDataService;
import ru.vsu.demyanov.service.StationDataService;
import ru.vsu.demyanov.service.WaypointDataService;
import ru.vsu.demyanov.view.*;
import ru.vsu.demyanov.view.resources.*;

import java.util.HashMap;
import java.util.Map;

public final class MainMenu extends ViewModule {

    public MainMenu(Router router, InputHandler inputHandler) {
        super(router, inputHandler);
    }

    @Override
    protected String getTitle() {
        return Strings.MainMenu.TITLE;
    }

    @Override
    protected Map<Integer, MenuOption> getOptions() {
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(1, new MenuOption(Strings.MainMenu.ROUTE_LIST, this::showRouteList));
        options.put(2, new MenuOption(Strings.MainMenu.CREATE_NEW, this::showAddRoute));
        options.put(3, new MenuOption(Strings.MainMenu.EDIT, this::showEditRoute));
        options.put(4, new MenuOption(Strings.MainMenu.DELETE, this::showDeleteRoute));
        options.put(0, new MenuOption(Strings.MainMenu.EXIT, this::close));
        return options;
    }

    private void showRouteList() {
        ViewModule routeList = new RouteList(
                getRouter(),
                getInputHandler(),
                new RouteDataService.Factory().produce(),
                new WaypointDataService.Factory().produce(),
                new StationDataService.Factory().produce());
        getRouter().push(routeList);
    }

    private void showAddRoute() {
        ViewModule addRoute = new AddRoute(
                getRouter(),
                getInputHandler(),
                new RouteDataService.Factory().produce(),
                new WaypointDataService.Factory().produce()
        );
        getRouter().push(addRoute);
    }

    private void showEditRoute() {
        ViewModule editRoute = new EditRoute(
                getRouter(),
                getInputHandler(),
                new RouteDataService.Factory().produce(),
                new WaypointDataService.Factory().produce()
        );
        getRouter().push(editRoute);
    }

    private void showDeleteRoute() {
        ViewModule deleteRoute = new DeleteRoute(
                getRouter(),
                getInputHandler(),
                new RouteDataService.Factory().produce()
        );
        getRouter().push(deleteRoute);
    }
}
