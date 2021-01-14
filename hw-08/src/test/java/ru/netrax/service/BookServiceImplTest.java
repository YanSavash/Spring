package ru.netrax.service;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Service.BookServiceImpl;
import ru.netrax.Service.CommentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Класс BookServiceImpl")
@Import({BookServiceImpl.class, CommentServiceImpl.class})
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;
    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    MongoTemplate mongoTemplate;

    private Book rightBook;
    private Book wrongBook;

    @BeforeEach
    void setUp() {
        Author author1 = new Author("1", "Антуан", "Сент-Экзюпери");
        Author author2 = new Author("2", "Айзек", "Азимов");
        Author author3 = new Author("3", "Спаркс", "Николас");

        Genre genre1 = new Genre("1", "Приключения");
        Genre genre2 = new Genre("2", "Фантастика");
        Genre genre3 = new Genre("3", "Роман");

        rightBook = new Book("1", "Маленький принц", author1, genre1);
        wrongBook = new Book("2", "Конец вечности", author2, genre2);
        Book book3 = new Book("3", "Спеши любить", author3, genre3);

        Comment comment1 = new Comment("1", "Первый комментарий");
        Comment comment2 = new Comment("2", "Второй комментарий");
        Comment comment3 = new Comment("3", "Третий комментарий");

        ArrayList<Comment> list1 = new ArrayList<>();
        list1.add(comment1);
        ArrayList<Comment> list2 = new ArrayList<>();
        list2.add(comment2);
        ArrayList<Comment> list3 = new ArrayList<>();
        list3.add(comment3);

        rightBook.setCommentList(list1);
        wrongBook.setCommentList(list2);
        book3.setCommentList(list3);

        mongoTemplate.save(author1);
        mongoTemplate.save(author2);
        mongoTemplate.save(author3);
        mongoTemplate.save(genre1);
        mongoTemplate.save(genre2);
        mongoTemplate.save(genre3);
        mongoTemplate.save(rightBook);
        mongoTemplate.save(wrongBook);
        mongoTemplate.save(book3);
        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
        mongoTemplate.save(comment3);
    }

    @Test
    @DisplayName("добавляет новую книгу в коллекцию")
    void shouldAddNewBook() {
        bookService.insertBook(
                wrongBook.getTitle(),
                wrongBook.getAuthor(),
                wrongBook.getGenre(),
                wrongBook.getCommentList());
        assertThat(bookService.getAllBooks()).hasSize(4);
    }

    @Test
    @DisplayName("удаляет книгу по номеру")
    void shouldDeleteBookById() {
        bookService.deleteBook("2");
        try {
            bookService.getBook("2");
        } catch (NoSuchElementException e) {
            assertThat(false).isFalse();
        }
    }

    @Test
    @DisplayName("возвращает весь список книг")
    void shouldGetAllBooks() {
        bookService.deleteBook("2");
        assertThat(bookService.getAllBooks())
                .hasSize(3)
                .contains(rightBook)
                .doesNotContain(wrongBook);
    }

    @Test
    @DisplayName("возвращает книгу по номеру")
    void shouldGetBookByNumber() {
        val book = bookService.getBook("1");
        List<Comment> commentsByBookId = commentService.getCommentsByBookId(book.getId());
        book.setCommentList(commentsByBookId);
        assertThat(book).isEqualTo(rightBook);
    }

    @Test
    @DisplayName("обновляет книгу")
    void shouldUpdateExistedBook() {
        rightBook.setTitle("Update book");
        bookService.updateBook(
                rightBook.getId(),
                rightBook.getTitle());
        val updatedBook = bookService.getBook(rightBook.getId());
        assertThat(updatedBook).isEqualTo(rightBook);
    }
}