package ru.netrax.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;

import java.util.ArrayList;

@ChangeLog
public class DatabaseChangelog {
    @Autowired
    MongoTemplate mongoTemplate;

    @ChangeSet(order = "001", id = "dropDb", author = "netrax", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "addData", author = "netrax", runAlways = true)
    public void addData(MongoTemplate mongoTemplate) {
        Author author1 = new Author("1", "Антуан", "Сент-Экзюпери");
        Author author2 = new Author("2", "Айзек", "Азимов");
        Author author3 = new Author("3", "Спаркс", "Николас");

        Genre genre1 = new Genre("1", "Приключения");
        Genre genre2 = new Genre("2", "Фантастика");
        Genre genre3 = new Genre("3", "Роман");

        Book book1 = new Book("1", "Маленький принц", author1, genre1);
        Book book2 = new Book("2", "Конец вечности", author2, genre2);
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

        book1.setCommentList(list1);
        book2.setCommentList(list2);
        book3.setCommentList(list3);

        mongoTemplate.save(author1);
        mongoTemplate.save(author2);
        mongoTemplate.save(author3);
        mongoTemplate.save(genre1);
        mongoTemplate.save(genre2);
        mongoTemplate.save(genre3);
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);
        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
        mongoTemplate.save(comment3);
    }
}
