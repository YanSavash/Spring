package ru.netrax.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Repository.GenreRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса GenreDaoImpl")
@Import(GenreRepositoryJpaImpl.class)
public class GenreRepositoryJpaImplTest {

    @Autowired
    private GenreRepositoryJpaImpl genreRepositoryJpa;

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
        assertThat(genreRepositoryJpa.getAllGenre())
                .hasSize(3)
                .doesNotContain(wrongGenre)
                .contains(rightGenre);
    }

    @DisplayName("проверка добавления жанров")
    @Test
    void checkInsertBook() {
        genreRepositoryJpa.save(wrongGenre);
        assertThat(genreRepositoryJpa.getAllGenre().contains(wrongGenre));
    }

    @DisplayName("проверка получения жанра по id")
    @Test
    void checkById() {
        assertThat(genreRepositoryJpa.getById(1))
                .isEqualTo(rightGenre);
    }
}
