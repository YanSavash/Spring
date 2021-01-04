package ru.netrax.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.AuthorRepository;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;
import ru.netrax.Repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Проверка класса BookServiceImpl")
@Import({BookServiceImpl.class})
public class BookServiceTest {

    @Autowired
    BookServiceImpl bookService;
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
        bookService.getBook(1).setCommentList(commentList);
        this.rightBook = new Book(1, "Маленький принц", rightAuthor, rightGenre, commentList);
        this.wrongBook = new Book(4, "На западном фронте без перемен", wrongAuthor, wrongGenre, commentList);
    }

    @DisplayName("проверка получения списка книг")
    @Test
    void checkList() {
        assertThat(bookService.getAllBooks())
                .hasSize(3)
                .doesNotContain(wrongBook);
    }

    @DisplayName("проверка добавления книг")
    @Test
    void checkInsertBook() {
        authorRepository.save(wrongAuthor);
        genreRepository.save(wrongGenre);
        bookService.insertBook(wrongBook.getTitle(), wrongBook.getAuthor(), wrongBook.getGenre());
        List<Book> allBooks = bookService.getAllBooks();
        assertThat(allBooks.contains(wrongBook));
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
        bookService.deleteBook(1L);
        assertThat(bookService.getAllBooks()).doesNotContain(rightBook);
    }
}
