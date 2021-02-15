package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.netrax.Model.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
