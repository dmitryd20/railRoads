package ru.vsu.demyanov.view.modules;

import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.entity.Route;
import ru.vsu.demyanov.models.entity.Schedule;
import ru.vsu.demyanov.models.entity.waypoints.FinishPoint;
import ru.vsu.demyanov.models.entity.waypoints.StartPoint;
import ru.vsu.demyanov.service.RouteService;
import ru.vsu.demyanov.service.WaypointService;
import ru.vsu.demyanov.view.InputHandler;
import ru.vsu.demyanov.view.MenuOption;
import ru.vsu.demyanov.view.Router;
import ru.vsu.demyanov.view.ViewModule;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public class AddRoute extends ViewModule {

    private RouteService routeService;
    private WaypointService waypointService;

    private Integer number;
    private String name;
    private int schedule = 0;

    protected AddRoute(Router router,
                       InputHandler inputHandler,
                       RouteService routeService,
                       WaypointService waypointService) {
        super(router, inputHandler);
        this.routeService = routeService;
        this.waypointService = waypointService;
    }

    @Override
    protected String getTitle() {
        return Strings.AddRoute.TITLE;
    }

    @Override
    protected Map<Integer, MenuOption> getOptions() {
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(1, new MenuOption(Strings.Common.SAVE, this::save));
        options.put(0, new MenuOption(Strings.Common.GO_BACK, this::close));
        return options;
    }

    @Override
    protected void showContent() {
        readNumber();
        readName();
        readSchedule();
    }

    private void readName() {
        System.out.println(Strings.AddRoute.ASK_HAS_NAME);
        var hasName = getInputHandler().readChoice();
        if (hasName == null) {
            showError(ErrorType.INVALID_INPUT, this::readName);
            return;
        }
        if (hasName) {
            System.out.println(Strings.AddRoute.ENTER_NAME);
            name = getInputHandler().readString();
        }
    }

    private void readNumber() {
        System.out.println(Strings.AddRoute.ENTER_NUMBER);
        number = getInputHandler().readInt();
        if (number == null) {
            showError(ErrorType.INVALID_INPUT, this::readNumber);
        }
    }

    private void readSchedule() {
        System.out.println(Strings.AddRoute.ENTER_SCHEDULE);
        var input = getInputHandler().readString();
        if (!input.matches("[+-]{7}")) {
            showError(ErrorType.INVALID_INPUT, this::readSchedule);
            return;
        }
        for (int i = 1; i <= input.length(); i++) {
            if (input.charAt(input.length() - i) == '+') {
                schedule += 1 << (i - 1);
            }
        }
    }

    private void readStart() {
        System.out.println(Strings.AddRoute.ENTER_START_STATION);
        var startStationId = getInputHandler().readInt();
        if (startStationId == null) {
            showError(ErrorType.INVALID_INPUT, this::readStart);
            return;
        }
        System.out.println(Strings.AddRoute.ENTER_START_TIME);
        var startTime = getInputHandler().readTime(false);
        var result = waypointService.addWaypointForRoute(number,
                new StartPoint(startStationId, startTime));
        if (result.isError()) {
            showError(result.getError(), this::readStart);
        }
    }

    private void readFinish() {
        System.out.println(Strings.AddRoute.ENTER_FINISH_STATION);
        var finishStationId = getInputHandler().readInt();
        if (finishStationId == null) {
            showError(ErrorType.INVALID_INPUT, this::readFinish);
            return;
        }
        System.out.println(Strings.AddRoute.ENTER_FINISH_TIME);
        var finishTime = getInputHandler().readTime(true);
        var result = waypointService.addWaypointForRoute(number,
                new FinishPoint(finishStationId, finishTime));
        if (result.isError()) {
            showError(result.getError(), this::readFinish);
        }
    }

    private void save() {
        var route = new Route(number, name, new Schedule(schedule));
        var result = routeService.addRoute(route);
        if (result.isError()) {
            showError(result.getError(), this::save);
            return;
        }
        readStart();
        readFinish();
        System.out.println(Strings.AddRoute.SUCCESS);
        close();
    }
}
