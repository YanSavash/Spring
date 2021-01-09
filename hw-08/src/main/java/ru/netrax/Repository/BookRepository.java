package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.netrax.Model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
