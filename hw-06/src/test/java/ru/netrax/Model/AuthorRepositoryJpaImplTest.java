package ru.netrax.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Model.Author;
import ru.netrax.Repository.AuthorRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса AuthorDaoImpl")
@Import(AuthorRepositoryJpaImpl.class)
public class AuthorRepositoryJpaImplTest {

    @Autowired
    private AuthorRepositoryJpaImpl authorRepositoryJpa;

    private Author rightAuthor;
    private Author wrongAuthor;

    @BeforeEach
    void prepare() {
        this.rightAuthor = new Author(1, "Антуан", "Сент-Экзюпери");
        this.wrongAuthor = new Author(4, "Джоан", "Роулинг");
    }

    @DisplayName("проверка получения списка авторов")
    @Test
    void checkList() {
        assertThat(authorRepositoryJpa.getList())
                .hasSize(3)
                .doesNotContain(wrongAuthor)
                .contains(rightAuthor);
    }

    @DisplayName("проверка добавления автора")
    @Test
    void checkInsertAuthor() {
        authorRepositoryJpa.insert(wrongAuthor);
        assertThat(authorRepositoryJpa.getList().contains(wrongAuthor));
    }

    @DisplayName("проверка получения автора по id")
    @Test
    void checkById() {
        assertThat(authorRepositoryJpa.getById(1).orElseThrow())
                .isEqualTo(rightAuthor);
    }
}
