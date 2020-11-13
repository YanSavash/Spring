package ru.netrax.Shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.netrax.Domain.Author;
import ru.netrax.Domain.Book;
import ru.netrax.Domain.Genre;
import ru.netrax.Service.BookService;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationShellCommands {

    private final BookService bookService;

    @ShellMethod(value = "Add genre", key = {"add_genre", "ag"})
    public void createGenre(@ShellOption("--id") long id,
                            @ShellOption("--title") String title) {
        bookService.addGenre(new Genre(id, title));
    }

    @ShellMethod(value = "Add author", key = {"add_author", "aa"})
    public void createGenre(@ShellOption("--id") long id,
                            @ShellOption("--firstName") String firstName,
                            @ShellOption("--lastName") String lastName) {
        bookService.addAuthor(new Author(id, firstName, lastName));
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

    @ShellMethod(value = "Update book", key = {"update", "up"})
    public void updateBook(@ShellOption(value = "--id") long id,
                           @ShellOption(value = "--title") String title,
                           @ShellOption(value = "--authorId") long authorId,
                           @ShellOption(value = "--genreId") long genreId) {
        Author author = bookService.getAuthor(authorId);
        Genre genre = bookService.getGenre(genreId);
        Book book = new Book(id, title, author, genre);
        bookService.updateBook(book);
    }

    @ShellMethod(value = "Get book by Id", key = {"book", "b"})
    public String printBook(@ShellOption("--id") long id) {
        return bookService.getBook(id).toString();
    }

    @ShellMethod(value = "Insert book", key = {"insert_book", "in"})
    public void insertBook(@ShellOption(value = "--id") long id,
                           @ShellOption(value = "--title") String title,
                           @ShellOption(value = "--authorId") long authorId,
                           @ShellOption(value = "--genreId") long genreId) {
        Author author = bookService.getAuthor(authorId);
        Genre genre = bookService.getGenre(genreId);
        Book book = new Book(id, title, author, genre);
        bookService.insertBook(book);
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete", "del"})
    public void deleteBook(@ShellOption("--id") long id) {
        bookService.deleteBook(id);
    }
}
