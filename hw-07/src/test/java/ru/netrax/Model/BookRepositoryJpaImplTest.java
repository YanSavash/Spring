package ru.netrax.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.netrax.Repository.AuthorRepository;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;
import ru.netrax.Repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование класса BookRepositoryJpaImpl")
public class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CommentRepository commentRepository;

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
        List<Comment> commentList = new ArrayList<>();
        commentList.add(commentRepository.findById(1L).orElseThrow());
        this.rightBook = new Book(1, "Маленький принц", rightAuthor, rightGenre, commentList);
        this.wrongBook = new Book(4, "На западном фронте без перемен", wrongAuthor, wrongGenre, commentList);
    }

    @DisplayName("проверка получения списка книг")
    @Test
    void checkList() {
        List<Book> all = bookRepository.findAll();
        assertThat(all)
                .hasSize(3)
                .doesNotContain(wrongBook);
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
        assertThat(bookRepository.findById(1L).orElseThrow().getTitle())
                .isEqualTo(rightBook.getTitle());
    }

    @DisplayName("проверка удаления книг")
    @Test
    void checkDeleteAuthor() {
        Book book = bookRepository.findById(1L).orElseThrow();
        bookRepository.delete(book);
        assertThat(bookRepository.findAll()).doesNotContain(rightBook);
    }
}
