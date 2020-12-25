package ru.vsu.demyanov.view;

import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.HashMap;
import java.util.Map;

public abstract class ViewModule {

    private Router router;
    private InputHandler inputHandler;

    protected ViewModule(Router router, InputHandler inputHandler) {
        this.router = router;
        this.inputHandler = inputHandler;
    }

    protected Router getRouter() {
        return router;
    }

    protected InputHandler getInputHandler() {
        return inputHandler;
    }

    void show() {
        showTitle();
        showContent();
        showOptions();
        askForOption();
    }

    protected String getTitle() {
        throw new UnsupportedOperationException("getTitle() not implemented");
    }

    protected Map<Integer, MenuOption> getOptions() {
        throw new UnsupportedOperationException("getOptions() not implemented");
    }

    protected void showContent() {
    }

    protected void showError(ErrorType error, Action onRetry) {
        System.out.println(Strings.Common.OPTIONS);
        System.out.println(error.getMessage());
        Map<Integer, MenuOption> options = new HashMap<>();
        options.put(1, new MenuOption(Strings.Common.RETRY, onRetry));
        options.put(0, new MenuOption(Strings.Common.GO_BACK, this::close));
        askForOption(options, false);
    }

    protected void close() {
        router.pop();
    }

    private void showTitle() {
        System.out.println(getTitle());
    }

    private void showOptions() {
        System.out.println(Strings.Common.OPTIONS);
        showOptions(getOptions());
    }

    private void showOptions(Map<Integer, MenuOption> options) {
        options.forEach((key, option) -> System.out.println(key + ": " + option.getName()));;
    }

    private void askForOption() {
        askForOption(getOptions(), false);
    }

    private void askForOption(Map<Integer, MenuOption> options, Boolean isRepeated) {
        if (isRepeated) {
            System.out.println(Strings.Common.INCORRECT_OPTION);
        }
        System.out.print(Strings.Common.CHOOSE_OPTION);
        Integer option = inputHandler.readInt();
        MenuOption action = options.get(option);
        if (action == null) {
            askForOption(options, true);
        } else {
            action.getAction().start();
        }
    }

}
