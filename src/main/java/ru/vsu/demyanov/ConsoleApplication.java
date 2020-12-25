package ru.vsu.demyanov;

import ru.vsu.demyanov.view.*;
import ru.vsu.demyanov.view.modules.MainMenu;

public class ConsoleApplication implements Application {

    @Override
    public void start() {
        Router router = new Router();
        InputHandler inputHandler = new InputHandler();
        router.push(new MainMenu(router, inputHandler));
    }

}
