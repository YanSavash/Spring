package netrax.Shell;

import lombok.RequiredArgsConstructor;
import netrax.Model.Author;
import netrax.Model.Genre;
import netrax.Service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationShellCommands {

    private final BookService bookService;

    @ShellMethod(value = "Add genre", key = {"add_genre", "ag"})
    public void createGenre(@ShellOption("--title") String title) {
        Genre genre = new Genre();
        genre.setTitle(title);
        bookService.addGenre(genre);
    }

    @ShellMethod(value = "Add author", key = {"add_author", "aa"})
    public void createAuthor(@ShellOption("--firstName") String firstName,
                             @ShellOption("--lastName") String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        bookService.addAuthor(author);
    }

    @ShellMethod(value = "Add comment", key = {"add_comment", "ac"})
    public void createComment(@ShellOption("--comment") String comment,
                              @ShellOption("--bookId") long bookId) {
        bookService.insertComment(comment, bookId);
    }

    @ShellMethod(value = "List all genres from store", key = {"list_genres", "lg"})
    public String printGenres() {
        return bookService.getAllGenres().toString();
    }

    @ShellMethod(value = "List all authors from store", key = {"list_authors", "la"})
    public String printAuthors() {
        return bookService.getAllAuthors().toString();
    }

    @ShellMethod(value = "List all books from store", key = {"list_books", "lb"})
    public String printBooks() {
        return bookService.getAllBooks().toString();
    }

    @ShellMethod(value = "List all comments from store", key = {"list_comments", "lc"})
    public String printComments() {
        return bookService.getAllComments().toString();
    }

    @ShellMethod(value = "Update book", key = {"update", "up"})
    public void updateBook(@ShellOption(value = "--id") long id,
                           @ShellOption(value = "--title") String title) {
        bookService.updateBook(id, title);
    }

    @ShellMethod(value = "Update comment", key = {"update com", "upc"})
    public void updateComment(@ShellOption(value = "--id") long id,
                           @ShellOption(value = "--comment") String comment) {
        bookService.updateComment(id, comment);
    }

    @ShellMethod(value = "Get book by Id", key = {"book", "b"})
    public String printBook(@ShellOption("--id") long id) {
        return bookService.getBook(id).toString();
    }

    @ShellMethod(value = "Get comment by Id", key = {"comment", "c"})
    public String printComment(@ShellOption("--id") long id) {
        return bookService.getComment(id).toString();
    }

    @ShellMethod(value = "Insert book", key = {"insert_book", "in"})
    public void insertBook(@ShellOption(value = "--title") String title,
                           @ShellOption(value = "--authorId") long authorId,
                           @ShellOption(value = "--genreId") long genreId) {
        Author author = bookService.getAuthor(authorId);
        Genre genre = bookService.getGenre(genreId);
        bookService.insertBook(title, author, genre);
    }

    @ShellMethod(value = "Insert book with comment", key = {"insert_book_with_comment", "inwc"})
    public void insertBookWithComment(@ShellOption(value = "--title") String title,
                                      @ShellOption(value = "--authorId") long authorId,
                                      @ShellOption(value = "--genreId") long genreId,
                                      @ShellOption(value = "--commentId") long commentId,
                                      @ShellOption(value = "--comment") String comment) {
        Author author = bookService.getAuthor(authorId);
        Genre genre = bookService.getGenre(genreId);
        bookService.insertBookWithComment(title, author, genre, commentId, comment);
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete", "del"})
    public void deleteBook(@ShellOption("--id") long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Delete comment by Id", key = {"delete_comment", "delc"})
    public void deleteComment(@ShellOption("--id") long id) {
        bookService.deleteComment(id);
    }
}
