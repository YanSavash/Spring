package ru.netrax.Repository;

import ru.netrax.Model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {
    List<Genre> getAllGenre();

    Optional<Genre> getById(long id);

    Genre insert(Genre genre);
}
