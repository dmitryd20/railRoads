CREATE TABLE "public"."station" (
    "id" serial,
    "name" varchar(100) NOT NULL,
    PRIMARY KEY ("id"),
    UNIQUE ("name")
);

CREATE TABLE "public"."route" (
    "id" integer,
    "name" varchar(100),
    "schedule" integer NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."waypoint" (
    "route_id" integer NOT NULL,
    "station_id" integer NOT NULL,
    "arrival" integer,
    "departure" integer,
    PRIMARY KEY ("route_id", "station_id"),
    FOREIGN KEY ("route_id") REFERENCES "public"."route"("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY ("station_id") REFERENCES "public"."station"("id") ON DELETE RESTRICT ON UPDATE CASCADE
);


INSERT INTO station(name) VALUES('Москва');
INSERT INTO station(name) VALUES('Санкт-Петербург');
INSERT INTO station(name) VALUES('Нижний Новгород');
INSERT INTO station(name) VALUES('Казань');
INSERT INTO station(name) VALUES('Екатеринбург');
INSERT INTO station(name) VALUES('Тюмень');
INSERT INTO station(name) VALUES('Омск');
INSERT INTO station(name) VALUES('Новосибирск');
INSERT INTO station(name) VALUES('Красноярск');
INSERT INTO station(name) VALUES('Иркутск');
INSERT INTO station(name) VALUES('Вязьма');
INSERT INTO station(name) VALUES('Смоленск');
INSERT INTO station(name) VALUES('Тула');
INSERT INTO station(name) VALUES('Воронеж');
INSERT INTO station(name) VALUES('Ростов-На-Дону');
INSERT INTO station(name) VALUES('Краснодар');
INSERT INTO station(name) VALUES('Сочи');
INSERT INTO station(name) VALUES('Тверь');

INSERT INTO route(id, name, schedule) VALUES(300, 'Сапсан', 127);
INSERT INTO route(id, name, schedule) VALUES(301, 'Сапсан', 127);
INSERT INTO route(id, name, schedule) VALUES(100, NULL, 127);
INSERT INTO route(id, name, schedule) VALUES(101, NULL, 127);
INSERT INTO route(id, name, schedule) VALUES(142, NULL, 106);

INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(300, 1, NULL, 600);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(300, 2, 920, NULL);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(301, 2, NULL, 720);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(301, 1, 1050, NULL);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(100, 1, 900, NULL);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(100, 18, 1065, 1095);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(100, 2, NULL, 1480);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(101, 2, NULL, 1090);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(101, 18, 1420, 1445);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(101, 1, 1655, NULL);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 1, NULL, 510);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 13, 700, 725);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 14, 1010, 1040);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 15, 1410, 1435);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 16, 1705, 1725);
INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(142, 17, 2020, NULL);
