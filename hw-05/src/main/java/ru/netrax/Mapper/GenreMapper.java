package ru.netrax.Mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.netrax.Domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int i) throws SQLException {
        return new Genre(rs.getLong("id"), rs.getString("title"));
    }
}
