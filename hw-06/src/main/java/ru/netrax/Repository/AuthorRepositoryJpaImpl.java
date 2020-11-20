package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Author> getList() {
        return em.createQuery("Select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }
}
