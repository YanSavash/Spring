package ru.netrax.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.netrax.Repository.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса AuthorRepositoryJpaImpl")
public class AuthorRepositoryJpaImplTest {

    @Autowired
    private AuthorRepository authorRepository;

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
        assertThat(authorRepository.findAll())
                .hasSize(3)
                .doesNotContain(wrongAuthor)
                .contains(rightAuthor);
    }

    @DisplayName("проверка добавления автора")
    @Test
    void checkInsertAuthor() {
        authorRepository.save(wrongAuthor);
        assertThat(authorRepository.findAll().contains(wrongAuthor));
    }

    @DisplayName("проверка получения автора по id")
    @Test
    void checkById() {
        assertThat(authorRepository.findById(1L).orElseThrow())
                .isEqualTo(rightAuthor);
    }
}
