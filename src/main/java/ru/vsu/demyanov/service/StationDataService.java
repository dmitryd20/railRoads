package ru.vsu.demyanov.service;

import ru.vsu.demyanov.dao.Repository;
import ru.vsu.demyanov.dao.database.DbStationRepository;
import ru.vsu.demyanov.dao.database.connection.Connector;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.StationDTO;
import ru.vsu.demyanov.models.entity.Station;

import java.sql.Connection;

public class StationDataService implements StationService {

    public static class Factory {

        public StationService produce() {
            Connection connection = Connector.getConnection();
            return new StationDataService(
                    new DbStationRepository(connection)
            );
        }
    }

    final Repository<StationDTO> repository;

    private StationDataService(Repository<StationDTO> repository) {
        this.repository = repository;
    }

    @Override
    public Result<Station> getStationById(int id) {
        var dto = repository.get(id);
        if (dto.isError()) {
            return Result.fail(dto.getError());
        }
        return Result.success(new Station(dto.getValue()));
    }
}
