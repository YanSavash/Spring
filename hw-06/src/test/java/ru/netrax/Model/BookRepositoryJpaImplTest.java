package ru.netrax.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Repository.AuthorRepositoryJpaImpl;
import ru.netrax.Repository.BookRepositoryJpaImpl;
import ru.netrax.Repository.GenreRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса BookDaoImpl")
@Import({BookRepositoryJpaImpl.class, AuthorRepositoryJpaImpl.class, GenreRepositoryJpaImpl.class})
public class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;
    @Autowired
    private AuthorRepositoryJpaImpl authorRepositoryJpa;
    @Autowired
    private GenreRepositoryJpaImpl genreRepositoryJpa;

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
        assertThat(bookRepositoryJpa.getAllBook())
                .hasSize(3)
                .doesNotContain(wrongBook)
                .contains(rightBook);
    }

    @DisplayName("проверка добавления книг")
    @Test
    void checkInsertBook() {
        authorRepositoryJpa.insert(wrongAuthor);
        genreRepositoryJpa.insert(wrongGenre);
        bookRepositoryJpa.insert(wrongBook);
        assertThat(bookRepositoryJpa.getAllBook().contains(wrongBook));
    }

    @DisplayName("проверка получения книг по id")
    @Test
    void checkById() {
        assertThat(bookRepositoryJpa.getById(1).orElseThrow())
                .isEqualTo(rightBook);
    }

    @DisplayName("проверка удаления книг")
    @Test
    void checkDeleteAuthor() {
        bookRepositoryJpa.delete(4);
        assertThat(bookRepositoryJpa.getAllBook()).doesNotContain(wrongBook);
    }
}
