package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
