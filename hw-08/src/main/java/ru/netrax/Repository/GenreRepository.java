package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.netrax.Model.Genre;


public interface GenreRepository extends MongoRepository<Genre, String> {
}
