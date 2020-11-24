package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import ru.netrax.Model.Author;
import ru.netrax.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepositoryJpa;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepositoryJpa.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepositoryJpa.save(author);
    }

    @Override
    public Author getAuthor(long id) {
        Optional<Author> optionalAuthor = authorRepositoryJpa.findById(id);
        return optionalAuthor.orElseThrow();
    }
}
