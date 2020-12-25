package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.entity.Route;
import ru.vsu.demyanov.service.RouteDataService;
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
        options.put(0, new MenuOption(Strings.MainMenu.EXIT, this::close));
        return options;
    }

    private void showRouteList() {
        RouteList routeList = new RouteList(getRouter(),
                getInputHandler(),
                new RouteDataService.Factory().produce());
        getRouter().push(routeList);
    }
}
