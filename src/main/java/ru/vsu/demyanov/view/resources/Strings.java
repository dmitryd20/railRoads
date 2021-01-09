package ru.vsu.demyanov.view.resources;

public class Strings {

    public static class Common {
        public static final String OPTIONS = "Меню:";
        public static final String CHOOSE_OPTION = "Выберите действие: ";
        public static final String INCORRECT_OPTION = "Такого действия не существует.";
        public static final String GO_BACK = "Назад.";
        public static final String RETRY = "Повторить";
        public static final String SAVE = "Сохранить";
    }

    public static class Input {
        public static final String ENTER_DAYS = "Введите день";
        public static final String ENTER_HOURS = "Введите часы";
        public static final String ENTER_MINUTES = "Введите минуты";
    }

    public static class Error {
        public static final String TITLE = "Произошла ошибка:";
        public static final String ALREADY_EXISTS = "Такой объект уже существует.";
        public static final String CONFIG = "Неверная конфигурация подключения к базе данных";
        public static final String DATA_ERROR = "Не удалось прочитать или записать данные.";
        public static final String INVALID_INPUT = "Введено некорректное значение";
        public static final String NOT_FOUND = "Объект не найден";
        public static final String SQL_ERROR = "Ошибка при запросе к базе данных";
    }

    public static class MainMenu {
        public static final String TITLE = "Маршруты поездов";
        public static final String ROUTE_LIST = "Список маршрутов";
        public static final String CREATE_NEW = "Создать маршрут";
        public static final String EDIT = "Изменить маршрут";
        public static final String DELETE = "Удалить маршрут";
        public static final String EXIT = "Выход";
    }

    public static class RouteList {
        public static final String TITLE = "Список маршрутов";
    }

    public static class AddRoute {
        public static final String TITLE = "Добавление маршрута";
        public static final String ASK_HAS_NAME = "Есть ли название у маршрута? (y/n)";
        public static final String ENTER_NAME = "Введите название маршрута";
        public static final String ENTER_NUMBER = "Введите номер маршрута";
        public static final String ENTER_SCHEDULE = "Введите расписание в формате +/- для каждого дня недели";
        public static final String ENTER_START_STATION = "Введите код станции отправления";
        public static final String ENTER_START_TIME = "Введите время отправления";
        public static final String ENTER_FINISH_STATION = "Введите код станции назначения";
        public static final String ENTER_FINISH_TIME = "Введите время в пути";
        public static final String SUCCESS = "Маршрут успешено добавлен";
    }

    public static class EditRoute {
        public static final String TITLE = "Изменеие маршрута";
        public static final String ADD_STATION = "Добавить остановку";
        public static final String ADD_STATION_SUCCESS = "Остановка добавлена";
        public static final String ENTER_NUMBER = "Введите номер маршрута";
        public static final String ENTER_STATION = "Введите код станции";
        public static final String ARRIVAL_TIME = "Время прибытия:";
        public static final String DEPARTURE_TIME = "Время отпраления:";
    }

    public static class DeleteRoute {
        public static final String TITLE = "Удаление маршрута";
        public static final String ENTER_NUMBER = "Введите номер маршрута";
        public static final String DELETE = "Удалить";
        public static final String SUCCESS = "Маршрут удалён";
    }

}
