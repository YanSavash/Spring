package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.netrax.Model.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
