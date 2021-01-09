package ru.vsu.demyanov.service;

import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.entity.Station;

public interface StationService {

    Result<Station> getStationById(int id);
}
