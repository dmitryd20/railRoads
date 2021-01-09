package ru.vsu.demyanov.models.dto;

public class StationDTO {

    private int id;
    private String name;

    public StationDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
