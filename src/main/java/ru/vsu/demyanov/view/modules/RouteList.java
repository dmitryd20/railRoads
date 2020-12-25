package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.entity.Route;
import ru.vsu.demyanov.service.RouteService;
import ru.vsu.demyanov.view.InputHandler;
import ru.vsu.demyanov.view.MenuOption;
import ru.vsu.demyanov.view.Router;
import ru.vsu.demyanov.view.ViewModule;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public final class RouteList extends ViewModule {

    private RouteService service;

    RouteList(Router router, InputHandler inputHandler, RouteService service) {
        super(router, inputHandler);
        this.service = service;
    }

    @Override
    protected String getTitle() {
        return Strings.RouteList.TITLE;
    }

    @Override
    protected void showContent() {
        var routes = service.getRoutes();
        if (routes.isError()) {
            System.out.println(routes.getError().getMessage());
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
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(route.getNumber())
                .append(' ')
                .append(route.getName() == null ? "" : route.getName())
                .append(" из ")
                .append(route.getStartPoint().getStation().getName())
                .append(" в ")
                .append(route.getFinishPoint().getStation().getName())
                .toString();

    }
}
