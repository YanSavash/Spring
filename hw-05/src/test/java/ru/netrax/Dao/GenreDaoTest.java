package ru.netrax.Dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("Тестирование класса GenreDaoImpl")
@Import(GenreDaoImpl.class)
public class GenreDaoTest {

    @Autowired
    private GenreDaoImpl genreDao;

    private Genre rightGenre;
    private Genre wrongGenre;

    @BeforeEach
    void prepare() {
        this.rightGenre = new Genre(1, "Приключения");
        this.wrongGenre = new Genre(4, "Драмма");
    }

    @DisplayName("проверка получения списка жанров")
    @Test
    void checkList() {
        assertThat(genreDao.getAllGenre())
                .hasSize(3)
                .doesNotContain(wrongGenre)
                .contains(rightGenre);
    }

    @DisplayName("проверка добавления жанров")
    @Test
    void checkInsertBook() {
        genreDao.insert(wrongGenre);
        assertThat(genreDao.getAllGenre().contains(wrongGenre));
    }

    @DisplayName("проверка получения жанра по id")
    @Test
    void checkById() {
        assertThat(genreDao.getById(1))
                .isEqualTo(rightGenre);
    }
}
