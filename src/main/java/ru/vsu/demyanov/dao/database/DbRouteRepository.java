package ru.vsu.demyanov.dao.database;

import ru.vsu.demyanov.dao.Repository;
import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.RouteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbRouteRepository implements Repository<RouteDTO> {

    private final Connection connection;

    public DbRouteRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Result<RouteDTO> get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, name, schedule FROM route WHERE id  = " + id)) {
                if (rs.next()) {
                    var dto = new RouteDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("schedule"));
                    return Result.success(dto);
                }
            }
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }

        return Result.fail(ErrorType.NOT_FOUND);
    }

    @Override
    public Result<List<RouteDTO>> getAll() {
        final List<RouteDTO> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, name, schedule FROM route")) {
                while (rs.next()) {
                    result.add(new RouteDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("Schedule"))
                    );
                }
                return Result.success(result);
            }
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> add(RouteDTO newEntity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO route(id, name, schedule) VALUES(?, ?, ?)")
        ){
            int count = 1;
            preparedStatement.setInt(count++, newEntity.getNumber());
            preparedStatement.setString(count++, newEntity.getName());
            preparedStatement.setInt(count, newEntity.getSchedule());
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                return Result.fail(ErrorType.ALREADY_EXISTS);
            }
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> change(int id, RouteDTO newEntity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE route SET id = ?, name = ?, schedule = ? WHERE id = ?"
        )) {
            int count = 1;
            preparedStatement.setInt(count++, newEntity.getNumber());
            preparedStatement.setString(count++, newEntity.getName());
            preparedStatement.setInt(count++, newEntity.getSchedule());
            preparedStatement.setInt(count, id);
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM route WHERE id = ?")
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }
}
