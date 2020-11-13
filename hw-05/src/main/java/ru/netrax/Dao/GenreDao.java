package ru.netrax.Dao;

import ru.netrax.Domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenre();

    Genre getById(long id);

    void insert(Genre genre);
}
