package ru.vsu.demyanov.dao.database;

import ru.vsu.demyanov.dao.Repository;
import ru.vsu.demyanov.models.ErrorType;
import ru.vsu.demyanov.models.Result;
import ru.vsu.demyanov.models.dto.StationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbStationRepository implements Repository<StationDTO> {

    private final Connection connection;

    public DbStationRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Result<StationDTO> get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, name FROM station WHERE id  = " + id)) {
                if (rs.next()) {
                    return Result.success(new StationDTO(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }

        return Result.fail(ErrorType.NOT_FOUND);
    }

    @Override
    public Result<List<StationDTO>> getAll() {
        final List<StationDTO> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, name FROM station")) {
                while (rs.next()) {
                    result.add(new StationDTO(rs.getInt("id"),
                            rs.getString("name"))
                    );
                }
                return Result.success(result);
            }
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }

    }

    @Override
    public Result<Void> add(StationDTO newEntity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO station(name) VALUES(?)")
        ){
            preparedStatement.setString(1,newEntity.getName());
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }

    @Override
    public Result<Void> change(int id, StationDTO newEntity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE station SET name = ? WHERE id = ?"
        )) {
            int count = 1;
            preparedStatement.setString(count++, newEntity.getName());
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
                "DELETE FROM station WHERE id = ?")
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            return Result.fail(ErrorType.SQL_ERROR);
        }
    }
}
