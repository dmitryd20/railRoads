package ru.vsu.demyanov.dao.database;

import ru.vsu.demyanov.dao.WaypointRepository;
import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.RouteDTO;
import ru.vsu.demyanov.models.dto.WaypointDTO;
import ru.vsu.demyanov.models.entity.waypoints.Waypoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbWaypointRepository implements WaypointRepository {

    /*
    C этим Dao возникли затруднения.
    Он содержит ключ, состоящий из двух полей, поэтому не соответствует интерфейсу Repository
     */

    private final Connection connection;

    public DbWaypointRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Result<List<WaypointDTO>> getFor(int routeId) {
        final List<WaypointDTO> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT station_id, arrival, departure " +
                    "FROM waypoint " +
                    "WHERE route_id = " + routeId)) {
                while (rs.next()) {
                    Integer arrival = rs.getInt("arrival");
                    if (rs.wasNull()) {
                        arrival = null;
                    }
                    Integer departure = rs.getInt("departure");
                    if (rs.wasNull()) {
                        departure = null;
                    }
                    result.add(new WaypointDTO(
                            rs.getInt("station_id"),
                            arrival,
                            departure)
                    );
                }
                return Result.success(result);
            }
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> add(WaypointDTO newEntity, int routeId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO waypoint(route_id, station_id, arrival, departure) VALUES(?, ?, ?, ?)")
        ){
            int count = 1;
            preparedStatement.setInt(count++, routeId);
            preparedStatement.setInt(count++, newEntity.getStationId());
            preparedStatement.setInt(count++, newEntity.getArrival());
            preparedStatement.setInt(count, newEntity.getDeparture());
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> change(WaypointDTO newEntity, int routeId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE waypoint " +
                        "SET arrival = ?, departure = ? " +
                        "WHERE route_id = ? AND station_id = ?"
        )) {
            int count = 1;
            preparedStatement.setInt(count++, newEntity.getArrival());
            preparedStatement.setInt(count++, newEntity.getDeparture());
            preparedStatement.setInt(count++, routeId);
            preparedStatement.setInt(count, newEntity.getStationId());
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> delete(int routeId, int station_id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM waypoint WHERE route_id = ? AND station_id = ?")
        ) {
            int count = 1;
            preparedStatement.setInt(count++, routeId);
            preparedStatement.setInt(count, routeId);
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }
}
