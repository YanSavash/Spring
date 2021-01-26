package ru.netrax.NoSql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "author")
public class NoSqlAuthor {

    @Id
    private String id;

    private String firstName;

    private String lastName;
}
