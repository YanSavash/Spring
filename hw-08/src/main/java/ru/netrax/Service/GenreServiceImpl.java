package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreMongoRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreMongoRepository.findAll();
    }

    @Override
    public void addGenre(Genre genre) {
        genreMongoRepository.save(genre);
    }

    @Override
    public Genre getGenre(String id) {
        return genreMongoRepository.findById(id).orElseThrow();
    }
}
