package ru.netrax.NoSql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "comment")
public class NoSqlComment {

    @Id
    private String id;

    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
