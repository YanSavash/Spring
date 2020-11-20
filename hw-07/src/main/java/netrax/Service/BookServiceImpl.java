package netrax.Service;

import lombok.RequiredArgsConstructor;
import netrax.Model.Author;
import netrax.Model.Book;
import netrax.Model.Comment;
import netrax.Model.Genre;
import netrax.Repository.AuthorRepository;
import netrax.Repository.BookRepository;
import netrax.Repository.CommentRepository;
import netrax.Repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepositoryJpa;
    private final AuthorRepository authorRepositoryJpa;
    private final GenreRepository genreRepositoryJpa;
    private final CommentRepository commentRepositoryJpa;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepositoryJpa.findAll();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepositoryJpa.findAll();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public void addGenre(Genre genre) {
        genreRepositoryJpa.save(genre);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepositoryJpa.save(author);
    }

    @Override
    public void insertBook(String title, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void insertBookWithComment(String title, Author author, Genre genre, long commentId, String comment) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Book insertBook = bookRepositoryJpa.save(book);

        commentRepositoryJpa.save(new Comment(commentId, comment, insertBook));
    }

    @Override
    public void insertComment(String comment, long bookId) {
        Book book = getBook(bookId);
        Comment comment1 = new Comment();
        comment1.setBook(book);
        comment1.setComment(comment);
        commentRepositoryJpa.save(comment1);
    }

    @Override
    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(id);
        return optionalBook.orElseThrow();
    }

    @Override
    public Comment getComment(long id) {
        Optional<Comment> optionalComment = commentRepositoryJpa.findById(id);
        return optionalComment.orElseThrow();
    }

    @Override
    public Author getAuthor(long id) {
        Optional<Author> optionalAuthor = authorRepositoryJpa.findById(id);
        return optionalAuthor.orElseThrow();
    }

    @Override
    public Genre getGenre(long id) {
        Optional<Genre> optionalGenre = genreRepositoryJpa.findById(id);
        return optionalGenre.orElseThrow();
    }

    @Override
    public void updateBook(long id, String title) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        book.setTitle(title);
        bookRepositoryJpa.save(book);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow();
        bookRepositoryJpa.delete(book);
    }

    @Override
    public void updateComment(long id, String comment) {
        Comment commentRepositoryJpaById = commentRepositoryJpa.findById(id).orElseThrow();
        commentRepositoryJpaById.setComment(comment);
        commentRepositoryJpa.save(commentRepositoryJpaById);
    }

    @Override
    public void deleteComment(long id) {
        Comment comment = commentRepositoryJpa.findById(id).orElseThrow();
        commentRepositoryJpa.delete(comment);
    }

}
