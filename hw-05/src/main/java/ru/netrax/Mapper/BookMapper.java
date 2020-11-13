package ru.netrax.Mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.netrax.Domain.Author;
import ru.netrax.Domain.Book;
import ru.netrax.Domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("title"),
                new Author(rs.getLong(7), rs.getString("firstname"), rs.getString("lastname")),
                        new Genre(rs.getLong(6), rs.getString(5)));
    }
}
