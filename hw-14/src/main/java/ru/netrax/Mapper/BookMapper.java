package ru.netrax.Mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.netrax.Model.Book;
import ru.netrax.Repository.AuthorRepository;
import ru.netrax.Repository.CommentRepository;
import ru.netrax.Repository.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class BookMapper implements RowMapper<Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("title"),
                authorRepository.findById(rs.getLong("author_id")).orElseThrow(),
                genreRepository.findById(rs.getLong("genre_id")).orElseThrow(),
                commentRepository.findAllByBookId(rs.getLong("id")));
    }
}
