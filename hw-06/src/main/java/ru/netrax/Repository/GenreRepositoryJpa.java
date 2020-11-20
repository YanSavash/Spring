package ru.netrax.Repository;

import ru.netrax.Model.Genre;

import java.util.List;

public interface GenreRepositoryJpa {
    List<Genre> getAllGenre();

    Genre getById(long id);

    Genre save(Genre genre);
}
