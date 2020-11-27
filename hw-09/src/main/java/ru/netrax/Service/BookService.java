package ru.netrax.Service;


import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book insertBook(String title, Author author, Genre genre);

    Book getBook(long id);

    void updateBook(long id, String title);

    void updateBook(long id, Author author);

    void updateBook(long id, Genre genre);

    void deleteBook(long id);


}
