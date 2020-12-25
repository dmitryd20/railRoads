package ru.vsu.demyanov.dao;

import ru.vsu.demyanov.models.Result;

import java.util.List;

public interface Repository<E> {

    Result<E> get(int id);

    Result<List<E>> getAll();

    Result<Void> add(E newEntity);

    Result<Void> change(int id, E newEntity);

    Result<Void> delete(int id);
}
