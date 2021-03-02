package ru.netrax.Service;

import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ReservedRepoServiceImpl implements ReservedRepoService {
    @Override
    public List<Book> getReservedBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(getReservedBookById());
        bookList.add(getReservedBookById());
        bookList.add(getReservedBookById());
        return bookList;
    }

    @Override
    public Book getReservedBookById() {
        Book book = new Book();
        Random random = new Random();
        book.setTitle("reserved book");
        book.setAuthor(new Author(15, "reserved", "author" + random.nextLong()));
        book.setGenre(new Genre(15, "reserved genre" + random.nextLong()));
        return book;
    }
}
