package ru.netrax.Dao;

import ru.netrax.Domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getList();

    Author getById(long id);

    void insert(Author author);
}
