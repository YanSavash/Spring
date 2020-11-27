package ru.netrax.Service;

import ru.netrax.Model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);

    Genre getGenre(long id);
}
