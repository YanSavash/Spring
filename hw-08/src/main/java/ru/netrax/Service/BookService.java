package ru.netrax.Service;


import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void insertBook(String title, Author author, Genre genre, List<Comment> commentList);

    void insertBookWithComment(String title, Author author, Genre genre, String commentId, String comment);

    Book getBook(String id);

    void updateBook(String id, String title);

    void deleteBook(String id);

    void addComment(String id, Comment comment);

    void deleteComment(String id, String bookId);
}
