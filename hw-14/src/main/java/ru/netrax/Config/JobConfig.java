package ru.netrax.Config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.netrax.Mapper.BookMapper;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.NoSql.NoSqlAuthor;
import ru.netrax.NoSql.NoSqlBook;
import ru.netrax.NoSql.NoSqlComment;
import ru.netrax.NoSql.NoSqlGenre;
import ru.netrax.Repository.AuthorRepository;
import ru.netrax.Repository.CommentRepository;
import ru.netrax.Repository.GenreRepository;

import javax.sql.DataSource;
import java.util.*;


@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private static final int CHUNK_SIZE = 10;
    private final Logger logger = LoggerFactory.getLogger("SpringBatch");

    public static final String IMPORT_USER_JOB_NAME = "importToMongoDbJob";

    private final DataSource dataSource;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Bean
    public PagingQueryProvider authorQueryProvider(DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean provider =
                new SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("SELECT id, first_name, last_name");
        provider.setFromClause("FROM author");
        provider.setSortKeys(sortByIdAsc());
        try {
            return provider.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public PagingQueryProvider genreQueryProvider(DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean provider =
                new SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("SELECT id, title");
        provider.setFromClause("FROM genre");
        provider.setSortKeys(sortByIdAsc());
        try {
            return provider.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public PagingQueryProvider bookQueryProvider(DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean provider =
                new SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("SELECT id, title, author_id, genre_id");
        provider.setFromClause("FROM book");
        provider.setSortKeys(sortByIdAsc());
        try {
            return provider.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public PagingQueryProvider commentQueryProvider(DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean provider =
                new SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("SELECT *");
        provider.setFromClause("FROM comment");
        provider.setSortKeys(sortByIdAsc());
        try {
            return provider.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Order> sortByIdAsc() {
        Map<String, Order> sortConfiguration = new HashMap<>();
        sortConfiguration.put("id", Order.ASCENDING);
        return sortConfiguration;
    }

    @StepScope
    @Bean
    public ItemReader<Author> authorJdbcBatchItemReader(DataSource dataSource,
                                                        PagingQueryProvider authorQueryProvider) {
        return new JdbcPagingItemReaderBuilder<Author>()
                .name("authorItemReader")
                .dataSource(dataSource)
                .pageSize(1)
                .queryProvider(authorQueryProvider)
                .rowMapper(new BeanPropertyRowMapper<>(Author.class))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, NoSqlAuthor> authorItemProcessor() {
        return this::getNoSqlAuthor;
    }

    private NoSqlAuthor getNoSqlAuthor(Author author) {
        NoSqlAuthor noSqlAuthor = new NoSqlAuthor();
        noSqlAuthor.setId(Objects.toString(author.getId()));
        noSqlAuthor.setFirstName(author.getFirstName());
        noSqlAuthor.setLastName(author.getLastName());
        return noSqlAuthor;
    }

    @StepScope
    @Bean
    public MongoItemWriter<NoSqlAuthor> authorMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<NoSqlAuthor>()
                .template(mongoTemplate)
                .collection("author")
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Genre> genreJdbcBatchItemReader(DataSource dataSource,
                                                      PagingQueryProvider genreQueryProvider) {
        return new JdbcPagingItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .dataSource(dataSource)
                .pageSize(1)
                .queryProvider(genreQueryProvider)
                .rowMapper(new BeanPropertyRowMapper<>(Genre.class))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, NoSqlGenre> genreItemProcessor() {
        return this::getNoSqlGenre;
    }

    private NoSqlGenre getNoSqlGenre(Genre genre) {
        NoSqlGenre noSqlGenre = new NoSqlGenre();
        noSqlGenre.setId(Objects.toString(genre.getId()));
        noSqlGenre.setTitle(genre.getTitle());
        return noSqlGenre;
    }

    @StepScope
    @Bean
    public MongoItemWriter<NoSqlGenre> genreMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<NoSqlGenre>()
                .template(mongoTemplate)
                .collection("genre")
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Book> bookJdbcBatchItemReader(DataSource dataSource,
                                                    PagingQueryProvider bookQueryProvider) {
        return new JdbcPagingItemReaderBuilder<Book>()
                .name("bookItemReader")
                .dataSource(dataSource)
                .pageSize(1)
                .queryProvider(bookQueryProvider)
                .rowMapper(new BookMapper(authorRepository, genreRepository, commentRepository))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, NoSqlBook> bookItemProcessor() {
        return book -> {
            NoSqlBook noSqlBook = new NoSqlBook();
            noSqlBook.setId(Objects.toString(book.getId()));
            noSqlBook.setTitle(book.getTitle());
            noSqlBook.setAuthor(getNoSqlAuthor(book.getAuthor()));
            noSqlBook.setGenre(getNoSqlGenre(book.getGenre()));
            List<NoSqlComment> commentList = new ArrayList<>();
            book.getCommentList()
                    .forEach(e -> commentList.add(new NoSqlComment(Objects.toString(e.getId()), e.getComment())));
            noSqlBook.setCommentList(commentList);
            return noSqlBook;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<NoSqlBook> bookMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<NoSqlBook>()
                .template(mongoTemplate)
                .collection("book")
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Comment> commentJdbcBatchItemReader(DataSource dataSource,
                                                          PagingQueryProvider commentQueryProvider) {
        return new JdbcPagingItemReaderBuilder<Comment>()
                .name("commentItemReader")
                .dataSource(dataSource)
                .pageSize(1)
                .queryProvider(commentQueryProvider)
                .rowMapper(new BeanPropertyRowMapper<>(Comment.class))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, NoSqlComment> commentItemProcessor() {
        return comment -> {
            NoSqlComment noSqlComment = new NoSqlComment();
            noSqlComment.setId(Objects.toString(comment.getId()));
            noSqlComment.setComment(comment.getComment());
            return noSqlComment;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<NoSqlComment> commentMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<NoSqlComment>()
                .template(mongoTemplate)
                .collection("comment")
                .build();
    }

    @Bean
    public Step authorStep() {
        return stepBuilderFactory.get("authorStep")
                .<Author, NoSqlAuthor>chunk(CHUNK_SIZE)
                .reader(authorJdbcBatchItemReader(dataSource, authorQueryProvider(dataSource)))
                .processor(authorItemProcessor())
                .writer(authorMongoItemWriter(mongoTemplate))
                .build();
    }

    @Bean
    public Step genreStep() {
        return stepBuilderFactory.get("genreStep")
                .<Genre, NoSqlGenre>chunk(CHUNK_SIZE)
                .reader(genreJdbcBatchItemReader(dataSource, genreQueryProvider(dataSource)))
                .processor(genreItemProcessor())
                .writer(genreMongoItemWriter(mongoTemplate))
                .build();
    }

    @Bean
    public Step bookStep() {
        return stepBuilderFactory.get("bookStep")
                .<Book, NoSqlBook>chunk(CHUNK_SIZE)
                .reader(bookJdbcBatchItemReader(dataSource, bookQueryProvider(dataSource)))
                .processor(bookItemProcessor())
                .writer(bookMongoItemWriter(mongoTemplate))
                .build();
    }

    @Bean
    public Step commentStep() {
        return stepBuilderFactory.get("commentStep")
                .<Comment, NoSqlComment>chunk(CHUNK_SIZE)
                .reader(commentJdbcBatchItemReader(dataSource, commentQueryProvider(dataSource)))
                .processor(commentItemProcessor())
                .writer(commentMongoItemWriter(mongoTemplate))
                .build();
    }

    @Bean
    public Job migrateH2ToMongoDb() {
        return jobBuilderFactory.get(IMPORT_USER_JOB_NAME)
                .start(authorStep())
                .next(genreStep())
                .next(bookStep())
                .next(commentStep())
                .build();
    }
}
