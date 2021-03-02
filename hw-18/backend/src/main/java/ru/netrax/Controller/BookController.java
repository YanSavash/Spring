package ru.netrax.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netrax.Dto.BookDto;
import ru.netrax.Model.Author;
import ru.netrax.Model.Genre;
import ru.netrax.Service.AuthorService;
import ru.netrax.Service.BookService;
import ru.netrax.Service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.netrax.Dto.BookDto.toDto;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> get() {
        List<BookDto> bookList = bookService.getAllBooks().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/bookById")
    public ResponseEntity<BookDto> getById(@RequestParam("id") long id) {
        BookDto bookDto = toDto(bookService.getBook(id));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTitle(@RequestBody BookDto bookDto) {
        boolean result = bookService.updateBook(bookDto.getId(), bookDto.getTitle());
        return result ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestParam("title") String title,
                                     @RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("genreTitle") String genreTitle
    ) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        Genre genre = new Genre();
        genre.setTitle(genreTitle);
        try {
            Author addAuthor = authorService.addAuthor(author);
            Genre addGenre = genreService.addGenre(genre);
            bookService.insertBook(title, addAuthor, addGenre);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
