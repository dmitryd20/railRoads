package ru.vsu.demyanov.dao.database.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vsu.demyanov.view.resources.Strings;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public static Connection getConnection() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File config = new File("src/main/resources/database/config.json");
            ConnectionModel model = mapper.readValue(
                    config,
                    new TypeReference<>(){});
            var connection = DriverManager.getConnection(
                    model.getUrl(),
                    model.getUser(),
                    model.getPassword());
            return connection;
        } catch (Exception e) {
            System.out.println(Strings.Error.CONFIG);
            return null;
        }
    }
}
