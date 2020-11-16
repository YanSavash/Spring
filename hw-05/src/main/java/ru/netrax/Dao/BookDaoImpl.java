package ru.netrax.Dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.netrax.Domain.Book;
import ru.netrax.Mapper.BookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao{
    private final NamedParameterJdbcOperations jdbc;

    private static final String SELECT_ID = "select b.id, b.title, a.firstName, a.lastName, g.title, g.id, a.id " +
            "from books b left join author a on authorId = a.id left join genre g on genreId = g.id " +
            "where b.id = :id";
    private static final String SELECT = "select b.id, b.title, a.firstName, a.lastName, g.title, g.id, a.id " +
            "from books b left join author a on authorId = a.id left join genre g on genreId = g.id order by b.id;";
    private static final String INSERT_QUERY = "insert into books (id, title, authorId, genreId) values (:id, :title, :authorId, :genreId)";
    private static final String UPDATE_QUERY = "update books set title = :title, authorId = :authorId, genreId = :genreId where id = :id";
    private static final String DELETE_QUERY = "delete from books where id = :id";

    @Override
    public List<Book> getAllBook() {
        return jdbc.query(SELECT, new BookMapper());
    }

    @Override
    public Book getById(long id) {
        final Map<String, Long> paramMap = new HashMap<>(1);
        paramMap.put("id", id);
        return jdbc.queryForObject(SELECT_ID, paramMap, new BookMapper());
    }

    @Override
    public void insert(Book book) {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        paramMap.put("title", book.getTitle());
        paramMap.put("authorId", book.getAuthor().getId());
        paramMap.put("genreId", book.getGenre().getId());
        jdbc.update(INSERT_QUERY, paramMap);
    }

    @Override
    public void update(Book book) {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        paramMap.put("title", book.getTitle());
        paramMap.put("authorId", book.getAuthor().getId());
        paramMap.put("genreId", book.getGenre().getId());
        jdbc.update(UPDATE_QUERY, paramMap);
    }

    @Override
    public void delete(long id) {
        final Map<String, Long> paramMap = new HashMap<>(1);
        paramMap.put("id", id);
        jdbc.update(DELETE_QUERY, paramMap);
    }
}
