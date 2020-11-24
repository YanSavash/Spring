package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepositoryJpa;
    private final CommentRepository commentRepositoryJpa;

    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    public void insertBook(String title, Author author, Genre genre, List<Comment> commentList) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCommentList(commentList);
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
    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(id);
        return optionalBook.orElseThrow();
    }

    @Override
    public void updateBook(long id, String title) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        book.setTitle(title);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        bookRepositoryJpa.delete(book);
    }
}
