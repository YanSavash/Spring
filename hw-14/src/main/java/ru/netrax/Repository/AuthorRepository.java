package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
