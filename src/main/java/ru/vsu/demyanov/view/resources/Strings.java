package ru.vsu.demyanov.view.resources;

public class Strings {

    public class Common {
        public static final String OPTIONS = "Меню:";
        public static final String CHOOSE_OPTION = "Выберите действие: ";
        public static final String INCORRECT_OPTION = "Такого действия не существует.";
        public static final String GO_BACK = "Назад.";
        public static final String RETRY = "Повторить";
    }

    public class Error {
        public static final String TITLE = "Произошла ошибка:";
        public static final String DATA_ERROR = "Не удалось прочитать или записать данные.";
        public static final String ALREADY_EXISTS = "Такой объект уже существует.";
        public static final String CONFIG = "Неверная конфигурация подключения к базе данных";
        public static final String SQL_ERROR = "Ошибка при запросе к базе данных";
        public static final String NOT_FOUND = "Объект не найден";
    }

    public class MainMenu {
        public static final String TITLE = "Маршруты поездов";
        public static final String ROUTE_LIST = "Список маршрутов";
        public static final String SEARCH = "Поиск маршрута";
        public static final String CREATE_NEW = "Создать маршрут";
        public static final String EXIT = "Выход";
    }

    public class RouteList {
        public static final String TITLE = "Список маршрутов";
    }

}
