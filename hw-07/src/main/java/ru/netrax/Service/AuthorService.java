package ru.netrax.Service;

import ru.netrax.Model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    void addAuthor(Author author);

    Author getAuthor(long id);
}
