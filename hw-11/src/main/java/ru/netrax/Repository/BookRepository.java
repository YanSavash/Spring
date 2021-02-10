package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.netrax.Model.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
