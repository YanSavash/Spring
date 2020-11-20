package ru.netrax.Repository;

import ru.netrax.Model.Author;

import java.util.List;

public interface AuthorRepositoryJpa {
    List<Author> getList();

    Author getById(long id);

    Author save(Author author);
}
