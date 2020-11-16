package ru.netrax.Service;

import ru.netrax.Domain.Author;
import ru.netrax.Domain.Book;
import ru.netrax.Domain.Genre;

import java.util.List;

public interface BookService {
    List<Genre> getAllGenres();

    List<Author> getAllAuthors();

    List<Book> getAllBooks();

    void addGenre(Genre genre);

    void addAuthor(Author author);

    void insertBook(Book book);

    Book getBook(long id);

    Author getAuthor(long id);

    Genre getGenre(long id);

    void updateBook(Book book);

    void deleteBook(long id);
}
