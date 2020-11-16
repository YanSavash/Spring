package ru.netrax.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Dao.AuthorDaoImpl;
import ru.netrax.Dao.BookDaoImpl;
import ru.netrax.Dao.GenreDaoImpl;
import ru.netrax.Domain.Author;
import ru.netrax.Domain.Book;
import ru.netrax.Domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("Проверка класса BookServiceImpl")
@Import({BookServiceImpl.class, BookDaoImpl.class, AuthorDaoImpl.class,GenreDaoImpl.class})
public class BookServiceTest {

    @Autowired
    BookServiceImpl bookService;
    @Autowired
    private BookDaoImpl bookDao;
    @Autowired
    private AuthorDaoImpl authorDao;
    @Autowired
    private GenreDaoImpl genreDao;

    private Book rightBook;
    private Book wrongBook;
    private Author wrongAuthor;
    private Genre wrongGenre;

    @BeforeEach
    void prepare() {
        Author rightAuthor = new Author(1, "Антуан", "Сент-Экзюпери");
        this.wrongAuthor = new Author(4, "Джоан", "Роулинг");
        Genre rightGenre = new Genre(1, "Приключения");
        this.wrongGenre = new Genre(4, "Драмма");
        this.rightBook = new Book(1,"Маленький принц", rightAuthor, rightGenre);
        this.wrongBook = new Book(4, "На западном фронте без перемен", wrongAuthor, wrongGenre);
    }

    @DisplayName("проверка получения списка книг")
    @Test
    void checkList() {
        assertThat(bookService.getAllBooks())
                .hasSize(3)
                .doesNotContain(wrongBook)
                .contains(rightBook);
    }

    @DisplayName("проверка добавления книг")
    @Test
    void checkInsertBook() {
        authorDao.insert(wrongAuthor);
        genreDao.insert(wrongGenre);
        bookService.insertBook(wrongBook);
        assertThat(bookService.getAllBooks().contains(wrongBook));
    }

    @DisplayName("проверка получения книг по id")
    @Test
    void checkById() {
        assertThat(bookService.getBook(1))
                .isEqualTo(rightBook);
    }

    @DisplayName("проверка удаления книг")
    @Test
    void checkDeleteAuthor() {
        bookService.deleteBook(4);
        assertThat(bookService.getAllBooks()).doesNotContain(wrongBook);
    }
}
