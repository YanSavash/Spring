package ru.netrax.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepositoryJpa;
    private final ReservedRepoService reservedRepoService;

    @HystrixCommand(groupKey = "ReservedBooksRepo", fallbackMethod = "getReservedBooks", commandKey = "findReservedBooks")
    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    public Book insertBook(String title, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepositoryJpa.save(book);
    }

    @HystrixCommand( groupKey = "ReservedBooksRepo", fallbackMethod = "getReservedBookById", commandKey = "findReservedBookById")
    @Override
    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(id);
        return optionalBook.orElseThrow();
    }

    @Override
    public boolean updateBook(long id, String title) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        book.setTitle(title);
        try {
            bookRepositoryJpa.save(book);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public void updateBook(long id, Author author) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        book.setAuthor(author);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void updateBook(long id, Genre genre) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        book.setGenre(genre);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        bookRepositoryJpa.delete(book);
    }

    private List<Book> getReservedBooks() {
        return reservedRepoService.getReservedBooks();
    }

    private Book getReservedBookById(Long id) {
        return reservedRepoService.getReservedBookById();
    }
}
