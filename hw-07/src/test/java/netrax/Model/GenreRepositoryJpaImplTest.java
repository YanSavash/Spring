package netrax.Model;

import netrax.Repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса GenreDaoImpl")
public class GenreRepositoryJpaImplTest {

    @Autowired
    private GenreRepository genreRepository;

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
        assertThat(genreRepository.findAll())
                .hasSize(3)
                .doesNotContain(wrongGenre)
                .contains(rightGenre);
    }

    @DisplayName("проверка добавления жанров")
    @Test
    void checkInsertBook() {
        genreRepository.save(wrongGenre);
        assertThat(genreRepository.findAll().contains(wrongGenre));
    }

    @DisplayName("проверка получения жанра по id")
    @Test
    void checkById() {
        assertThat(genreRepository.findById(1L).orElseThrow())
                .isEqualTo(rightGenre);
    }
}
