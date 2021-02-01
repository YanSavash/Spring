package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Genre;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}
