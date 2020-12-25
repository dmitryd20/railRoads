package ru.vsu.demyanov.view;

public class MenuOption {

    private String name;
    private Action action;

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
