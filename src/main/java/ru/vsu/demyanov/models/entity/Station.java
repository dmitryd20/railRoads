package ru.vsu.demyanov.models.entity;

import ru.vsu.demyanov.models.DTOConvertible;
import ru.vsu.demyanov.models.dto.StationDTO;

public class Station implements DTOConvertible<StationDTO> {

    private int id;
    private String name;

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
    }

    public Station(StationDTO dto) {
        this(dto.getId(), dto.getName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public StationDTO toDTO() {
        return new StationDTO(id, name);
    }
}
