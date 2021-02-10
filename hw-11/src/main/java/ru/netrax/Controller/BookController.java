package ru.netrax.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.netrax.Model.Book;
import ru.netrax.Repository.BookRepository;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/book")
    public Flux<Book> get() {
        return bookRepository.findAll();
    }

    @GetMapping("/bookById")
    public Mono<Book> getById(@RequestParam("id") long id) {
        return bookRepository.findById(Long.toString(id));
    }

    @PutMapping("/update")
    public Mono<Book> updateTitle(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/delete")
    public Mono<Void> delete(@RequestParam("id") long id) {
        return bookRepository.deleteById(Long.toString(id));
    }

    @PostMapping("/add")
    public Mono<Book> addBook(@RequestParam("title") String title
    ) {
        Book book = new Book();
        book.setTitle(title);
        return bookRepository.save(book);
    }
}
