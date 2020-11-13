package ru.netrax.Dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.netrax.Domain.Author;
import ru.netrax.Mapper.AuthorMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;
    private static final String INSERT = "insert into author (id, firstname, lastname) values (:id, :firstname, :lastname)";
    private static final String SELECT = "select * from author";
    private static final String SELECT_ID = "select * from author where id = :id";

    @Override
    public List<Author> getList() {
        return jdbc.query(SELECT, new AuthorMapper());
    }

    @Override
    public Author getById(long id) {
        final Map<String, Long> paramMap = new HashMap<>(1);
        paramMap.put("id", id);
        return jdbc.queryForObject(SELECT_ID, paramMap, new AuthorMapper());
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", author.getId());
        paramMap.put("firstname", author.getFirstName());
        paramMap.put("lastname", author.getLastName());
        jdbc.update(INSERT, paramMap);
    }
}
