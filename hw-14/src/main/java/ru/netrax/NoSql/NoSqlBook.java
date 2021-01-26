package ru.netrax.NoSql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.netrax.Model.Comment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "book")
public class NoSqlBook {

    @Id
    private String id;

    private String title;

    @DBRef
    private NoSqlAuthor author;

    @DBRef
    private NoSqlGenre genre;

    private List<NoSqlComment> commentList;
}
