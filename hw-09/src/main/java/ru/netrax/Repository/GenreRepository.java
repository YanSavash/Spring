package ru.netrax.Repository;

import ru.netrax.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}
