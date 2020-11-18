package ru.netrax.Repository;

import ru.netrax.Model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {
    List<Author> getList();

    Optional<Author> getById(long id);

    Author insert(Author author);
}
