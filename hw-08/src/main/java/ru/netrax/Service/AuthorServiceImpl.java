package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Repository.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorMongoRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorMongoRepository.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        authorMongoRepository.save(author);
    }

    @Override
    public Author getAuthor(String id) {
        return authorMongoRepository.findById(id).orElseThrow();
    }
}
