package ru.vsu.demyanov.view;

import java.util.Stack;

public class Router {

    private final Stack<ViewModule> viewModules = new Stack<>();

    public void push(ViewModule module) {
        viewModules.push(module);
        module.show();
    }

    public void pop() {
        viewModules.pop();
        if (viewModules.isEmpty()) {
            System.exit(0);
        } else {
            viewModules.peek().show();
        }
    }
}
