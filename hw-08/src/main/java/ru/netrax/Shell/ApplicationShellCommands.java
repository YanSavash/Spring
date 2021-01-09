package ru.netrax.Shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Service.AuthorService;
import ru.netrax.Service.BookService;
import ru.netrax.Service.CommentService;
import ru.netrax.Service.GenreService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationShellCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @ShellMethod(value = "Add genre", key = {"add_genre", "ag"})
    public void createGenre(@ShellOption("--title") String title) {
        Genre genre = new Genre();
        genre.setTitle(title);
        genreService.addGenre(genre);
    }

    @ShellMethod(value = "Add author", key = {"add_author", "aa"})
    public void createAuthor(@ShellOption("--firstName") String firstName,
                             @ShellOption("--lastName") String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorService.addAuthor(author);
    }

    @ShellMethod(value = "Add comment", key = {"add_comment", "ac"})
    public void createComment(@ShellOption("--comment") String comment) {
        commentService.insertComment(comment);
    }

    @ShellMethod(value = "List all genres from store", key = {"list_genres", "lg"})
    public String printGenres() {
        return genreService.getAllGenres().toString();
    }

    @ShellMethod(value = "List all authors from store", key = {"list_authors", "la"})
    public String printAuthors() {
        return authorService.getAllAuthors().toString();
    }

    @ShellMethod(value = "List all books from store", key = {"list_books", "lb"})
    public String printBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        allBooks.forEach(e -> {
            List<Comment> commentsByBookId = commentService.getCommentsByBookId(e.getId());
            e.setCommentList(commentsByBookId);
        });
        return allBooks.toString();
    }

    @ShellMethod(value = "List all comments from store", key = {"list_comments", "lc"})
    public String printComments() {
        return commentService.getAllComments().toString();
    }

    @ShellMethod(value = "List all comments from store by book id", key = {"list_comments_by_book_id", "lcb"})
    public String printCommentsByBookId(@ShellOption(value = "--bookId") String bookId) {
        return commentService.getCommentsByBookId(bookId).toString();
    }

    @ShellMethod(value = "Update book", key = {"update", "up"})
    public void updateBook(@ShellOption(value = "--id") String id,
                           @ShellOption(value = "--title") String title) {
        bookService.updateBook(id, title);
    }

    @ShellMethod(value = "Update comment", key = {"update com", "upc"})
    public void updateComment(@ShellOption(value = "--id") String id,
                              @ShellOption(value = "--comment") String comment) {
        commentService.updateComment(id, comment);
    }

    @ShellMethod(value = "Get book by Id", key = {"book", "b"})
    public String printBook(@ShellOption("--id") String id) {
        Book book = bookService.getBook(id);
        List<Comment> commentsByBookId = commentService.getCommentsByBookId(book.getId());
        book.setCommentList(commentsByBookId);
        return book.toString();
    }

    @ShellMethod(value = "Get comment by Id", key = {"comment", "c"})
    public String printComment(@ShellOption("--id") String id) {
        return commentService.getComment(id).toString();
    }

    @ShellMethod(value = "Insert book", key = {"insert_book", "in"})
    public void insertBook(@ShellOption(value = "--title") String title,
                           @ShellOption(value = "--authorId") String authorId,
                           @ShellOption(value = "--genreId") String genreId) {
        Author author = authorService.getAuthor(authorId);
        Genre genre = genreService.getGenre(genreId);
        bookService.insertBook(title, author, genre, new ArrayList<Comment>());
    }

    @ShellMethod(value = "Insert book with comment", key = {"insert_book_with_comment", "inwc"})
    public void insertBookWithComment(@ShellOption(value = "--title") String title,
                                      @ShellOption(value = "--authorId") String authorId,
                                      @ShellOption(value = "--genreId") String genreId,
                                      @ShellOption(value = "--commentId") String commentId,
                                      @ShellOption(value = "--comment") String comment) {
        Author author = authorService.getAuthor(authorId);
        Genre genre = genreService.getGenre(genreId);
        bookService.insertBookWithComment(title, author, genre, commentId, comment);
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete", "del"})
    public void deleteBook(@ShellOption("--id") String id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Delete comment by Id", key = {"delete_comment", "delc"})
    public void deleteComment(@ShellOption("--id") String id) {
        commentService.deleteComment(id);
    }
}
