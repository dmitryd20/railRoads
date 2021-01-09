package ru.vsu.demyanov.view;

public class MenuOption {

    private final String name;
    private final Action action;

    public MenuOption(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

}
