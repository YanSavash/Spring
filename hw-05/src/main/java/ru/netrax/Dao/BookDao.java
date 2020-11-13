package ru.netrax.Dao;

import ru.netrax.Domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBook();

    Book getById(long id);

    void insert(Book book);

    void update(Book book);

    void delete(long id);
}
