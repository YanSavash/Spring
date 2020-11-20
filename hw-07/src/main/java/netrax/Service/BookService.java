package netrax.Service;


import netrax.Model.Author;
import netrax.Model.Book;
import netrax.Model.Comment;
import netrax.Model.Genre;

import java.util.List;

public interface BookService {
    List<Genre> getAllGenres();

    List<Author> getAllAuthors();

    List<Book> getAllBooks();

    List<Comment> getAllComments();

    void addGenre(Genre genre);

    void addAuthor(Author author);

    void insertBook(String title, Author author, Genre genre);

    void insertBookWithComment(String title, Author author, Genre genre, long commentId, String comment);

    void insertComment(String title, long bookId);

    Book getBook(long id);

    Comment getComment(long id);

    Author getAuthor(long id);

    Genre getGenre(long id);

    void updateBook(long id, String title);

    void deleteBook(long id);

    void updateComment(long id, String comment);

    void deleteComment(long id);
}
