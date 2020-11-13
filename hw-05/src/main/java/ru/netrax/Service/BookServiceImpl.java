package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Dao.AuthorDao;
import ru.netrax.Dao.BookDao;
import ru.netrax.Dao.GenreDao;
import ru.netrax.Domain.Author;
import ru.netrax.Domain.Book;
import ru.netrax.Domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenre();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getList();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBook();
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.insert(genre);
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.insert(author);
    }

    @Override
    public void insertBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getBook(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Author getAuthor(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Genre getGenre(long id) {
        return genreDao.getById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.delete(id);
    }

}
