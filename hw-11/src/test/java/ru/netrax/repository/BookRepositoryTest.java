package ru.netrax.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.BookRepository;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Класс BookRepositoryTest")
class BookRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    BookRepository bookRepository;

    private Book rightBook;
    private Book wrongBook;

    @Test
    void findAllBooks() {
        StepVerifier.create(bookRepository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findRightBookById() {
        Mono<Book> monoBook = bookRepository.findById(rightBook.getId());

        StepVerifier.create(monoBook)
                .assertNext((book -> assertEquals(book.getTitle(), rightBook.getTitle())))
                .expectComplete()
                .verify();

    }

    @Test
    void notFindWrongBookById() {
        Mono<Book> monoBook = bookRepository.findById(wrongBook.getId());

        StepVerifier.create(monoBook)
                .assertNext((book -> assertNotEquals(book.getTitle(), rightBook.getTitle())))
                .expectComplete()
                .verify();

    }

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
}