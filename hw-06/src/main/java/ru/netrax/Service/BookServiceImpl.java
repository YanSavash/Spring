package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.AuthorRepositoryJpa;
import ru.netrax.Repository.BookRepositoryJpa;
import ru.netrax.Repository.CommentRepositoryJpa;
import ru.netrax.Repository.GenreRepositoryJpa;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepositoryJpa.getAllGenre();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepositoryJpa.getList();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.getAllBook();
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepositoryJpa.getAllComment();
    }

    @Override
    public void addGenre(Genre genre) {
        genreRepositoryJpa.save(genre);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepositoryJpa.save(author);
    }

    @Override
    public void insertBook(String title, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void insertBookWithComment(String title, Author author, Genre genre, long commentId, String comment) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Book insertBook = bookRepositoryJpa.save(book);

        commentRepositoryJpa.save(new Comment(commentId, comment, insertBook));
    }

    @Override
    public void insertComment(String comment, long bookId) {
        Book book = getBook(bookId);
        Comment comment1 = new Comment();
        comment1.setBook(book);
        comment1.setComment(comment);
        commentRepositoryJpa.save(comment1);
    }

    @Override
    public Book getBook(long id) {
        return bookRepositoryJpa.getById(id);
    }

    @Override
    public Comment getComment(long id) {
        return commentRepositoryJpa.getById(id);
    }

    @Override
    public Author getAuthor(long id) {
        return authorRepositoryJpa.getById(id);
    }

    @Override
    public Genre getGenre(long id) {
        return genreRepositoryJpa.getById(id);
    }

    @Override
    public void updateBook(long id, String title) {
        Book book = bookRepositoryJpa.getById(id);
        book.setTitle(title);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepositoryJpa.delete(id);
    }

    @Override
    public void updateComment(long id, String comment) {
        Comment commentRepositoryJpaById = commentRepositoryJpa.getById(id);
        commentRepositoryJpaById.setComment(comment);
        commentRepositoryJpa.save(commentRepositoryJpaById);
    }

    @Override
    public void deleteComment(long id) {
        commentRepositoryJpa.delete(id);
    }

}
