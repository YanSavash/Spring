package netrax.Model;

import netrax.Repository.AuthorRepository;
import netrax.Repository.BookRepository;
import netrax.Repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса BookDaoImpl")
public class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

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
        this.rightBook = new Book(1, "Маленький принц", rightAuthor, rightGenre);
        this.wrongBook = new Book(4, "На западном фронте без перемен", wrongAuthor, wrongGenre);
    }

    @DisplayName("проверка получения списка книг")
    @Test
    void checkList() {
        assertThat(bookRepository.findAll())
                .hasSize(3)
                .doesNotContain(wrongBook)
                .contains(rightBook);
    }

    @DisplayName("проверка добавления книг")
    @Test
    void checkInsertBook() {
        authorRepository.save(wrongAuthor);
        genreRepository.save(wrongGenre);
        bookRepository.save(wrongBook);
        assertThat(bookRepository.findAll().contains(wrongBook));
    }

    @DisplayName("проверка получения книг по id")
    @Test
    void checkById() {
        assertThat(bookRepository.findById(1L).orElseThrow())
                .isEqualTo(rightBook);
    }

    @DisplayName("проверка удаления книг")
    @Test
    void checkDeleteAuthor() {
        Book book = bookRepository.findById(1L).orElseThrow();
        bookRepository.delete(book);
        assertThat(bookRepository.findAll()).doesNotContain(rightBook);
    }
}
