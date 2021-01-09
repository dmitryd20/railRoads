package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.service.RouteService;
import ru.vsu.demyanov.view.InputHandler;
import ru.vsu.demyanov.view.MenuOption;
import ru.vsu.demyanov.view.Router;
import ru.vsu.demyanov.view.ViewModule;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public class DeleteRoute extends ViewModule {

    private RouteService routeService;

    private Integer routeNumber;

    public DeleteRoute(Router router, InputHandler inputHandler, RouteService routeService) {
        super(router, inputHandler);
        this.routeService = routeService;
    }

    @Override
    protected String getTitle() {
        return Strings.DeleteRoute.TITLE;
    }

    @Override
    protected Map<Integer, MenuOption> getOptions() {
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(1, new MenuOption(Strings.DeleteRoute.DELETE, this::deleteRoute));
        options.put(0, new MenuOption(Strings.Common.GO_BACK, this::close));
        return options;
    }

    @Override
    protected void showContent() {
        System.out.println(Strings.DeleteRoute.ENTER_NUMBER);
        routeNumber = getInputHandler().readInt();
        if (routeNumber == null) {
            showError(ErrorType.INVALID_INPUT, this::showContent);
        }
        var route = routeService.getRoute(routeNumber);
        if (route.isError()) {
            showError(route.getError(), this::showContent);
            return;
        }
    }

    private void deleteRoute() {
        var result = routeService.deleteRoute(routeNumber);
        if (result.isError()) {
            showError(result.getError(), this::deleteRoute);
            return;
        }
        System.out.println(Strings.DeleteRoute.SUCCESS);
        close();
    }
}
