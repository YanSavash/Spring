package ru.netrax.Dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("Тестирование класса AuthorDaoImpl")
@Import(AuthorDaoImpl.class)
public class AuthorDaoTest {

    @Autowired
    private AuthorDaoImpl authorDao;

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
        assertThat(authorDao.getList())
                .hasSize(3)
                .doesNotContain(wrongAuthor)
                .contains(rightAuthor);
    }

    @DisplayName("проверка добавления автора")
    @Test
    void checkInsertAuthor() {
        authorDao.insert(wrongAuthor);
        assertThat(authorDao.getList().contains(wrongAuthor));
    }

    @DisplayName("проверка получения автора по id")
    @Test
    void checkById() {
        assertThat(authorDao.getById(1))
                .isEqualTo(rightAuthor);
    }
}
