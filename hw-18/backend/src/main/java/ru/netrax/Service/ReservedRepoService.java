package ru.netrax.Service;

import ru.netrax.Model.Book;

import java.util.List;

public interface ReservedRepoService {
    List<Book> getReservedBooks();

    Book getReservedBookById();
}
