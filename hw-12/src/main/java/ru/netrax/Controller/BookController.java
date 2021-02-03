package ru.netrax.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.netrax.Dto.BookDto;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Genre;
import ru.netrax.Service.AuthorService;
import ru.netrax.Service.BookService;
import ru.netrax.Service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String redirectToBooks() {
        return "redirect:/bookstore/book";
    }

    @GetMapping("/book")
    public String get(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);
        return "list";
    }

    @GetMapping("/bookById")
    public String getById(@RequestParam("id") long id,
                          Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "single";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/bookstore/book";
    }

    @PostMapping("/update")
    public String updateTitle(Book book) {
        bookService.updateBook(book.getId(), book.getTitle());
        return "redirect:/bookstore/book";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "create";
    }

    @PostMapping("/add")
    public String addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        bookService.insertBook(book.getTitle(), book.getAuthor(), book.getGenre());
        return "redirect:/bookstore/book";
    }

    @GetMapping("/createAuthor")
    public String createAuthor(@RequestParam("id") long id,
                               Model model) {
        model.addAttribute("id", id);
        return "createAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(long id, HttpServletRequest request) {
        Author author = new Author();
        author.setFirstName(request.getParameter("firstName"));
        author.setLastName(request.getParameter("lastName"));
        Author addAuthor = authorService.addAuthor(author);
        bookService.updateBook(id, addAuthor);
        return "redirect:/bookstore/book";
    }

    @GetMapping("/createGenre")
    public String createGenre(@RequestParam("id") long id,
                              Model model) {
        model.addAttribute("id", id);
        return "createGenre";
    }

    @PostMapping("/addGenre")
    public String addGenre(HttpServletRequest request, long id) {
        Genre genre = new Genre();
        genre.setTitle(request.getParameter("title"));
        Genre addGenre = genreService.addGenre(genre);
        bookService.updateBook(id, addGenre);
        return "redirect:/bookstore/book";
    }
}
