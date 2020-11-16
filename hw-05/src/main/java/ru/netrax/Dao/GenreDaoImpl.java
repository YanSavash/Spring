package ru.netrax.Dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.netrax.Domain.Genre;
import ru.netrax.Mapper.GenreMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;
    private static final String SELECT_ID = "select * from genre where id = :id";
    private static final String SELECT = "select * from genre";
    private static final String INSERT = "insert into genre (id, title) values (:id, :title)";

    @Override
    public List<Genre> getAllGenre() {
        return jdbc.query(SELECT, new GenreMapper());
    }

    @Override
    public Genre getById(long id) {
        final Map<String, Long> paramMap = new HashMap<>(1);
        paramMap.put("id", id);
        return jdbc.queryForObject(SELECT_ID, paramMap, new GenreMapper());
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", genre.getId());
        paramMap.put("title", genre.getTitle());
        jdbc.update(INSERT, paramMap);
    }
}
