package ru.netrax.Repository;

import ru.netrax.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    List<Book> getAllBook();

    Optional<Book> getById(long id);

    Book insert(Book book);

    void update(long id, String title);

    void delete(long id);
}
