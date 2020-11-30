package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepositoryJpa;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepositoryJpa.findAll();
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepositoryJpa.save(genre);
    }

    @Override
    public Genre getGenre(long id) {
        Optional<Genre> optionalGenre = genreRepositoryJpa.findById(id);
        return optionalGenre.orElseThrow();
    }
}
