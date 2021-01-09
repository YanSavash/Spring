package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.netrax.Model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
