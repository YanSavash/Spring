package ru.netrax.Mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.netrax.Domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int i) throws SQLException {
        return new Author(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"));
    }
}
