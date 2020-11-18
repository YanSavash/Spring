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
import java.util.Optional;

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
        genreRepositoryJpa.insert(genre);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepositoryJpa.insert(author);
    }

    @Override
    public void insertBook(String title, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepositoryJpa.insert(book);
    }

    @Override
    public void insertBookWithComment(String title, Author author, Genre genre, long commentId, String comment) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Book insertBook = bookRepositoryJpa.insert(book);

        commentRepositoryJpa.insert(new Comment(commentId, comment, insertBook));
    }

    @Override
    public void insertComment(String comment, long bookId) {
        Book book = getBook(bookId);
        Comment comment1 = new Comment();
        comment1.setBook(book);
        comment1.setComment(comment);
        commentRepositoryJpa.insert(comment1);
    }

    @Override
    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepositoryJpa.getById(id);
        return optionalBook.orElseThrow();
    }

    @Override
    public Comment getComment(long id) {
        Optional<Comment> optionalComment = commentRepositoryJpa.getById(id);
        return optionalComment.orElseThrow();
    }

    @Override
    public Author getAuthor(long id) {
        Optional<Author> optionalAuthor = authorRepositoryJpa.getById(id);
        return optionalAuthor.orElseThrow();
    }

    @Override
    public Genre getGenre(long id) {
        Optional<Genre> optionalGenre = genreRepositoryJpa.getById(id);
        return optionalGenre.orElseThrow();
    }

    @Override
    public void updateBook(long id, String title) {
        bookRepositoryJpa.update(id, title);
    }

    @Override
    public void deleteBook(long id) {
        bookRepositoryJpa.delete(id);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepositoryJpa.update(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepositoryJpa.delete(id);
    }

}
