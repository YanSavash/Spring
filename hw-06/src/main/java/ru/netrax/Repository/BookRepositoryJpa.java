package ru.netrax.Repository;

import ru.netrax.Model.Book;

import java.util.List;

public interface BookRepositoryJpa {
    List<Book> getAllBook();

    Book getById(long id);

    Book save(Book book);

    void delete(long id);
}
