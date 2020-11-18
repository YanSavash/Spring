package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Author> getList() {
        return em.createQuery("Select a from Author a", Author.class).getResultList();
    }

    @Transactional
    @Override
    public Optional<Author> getById(long id) {
        TypedQuery<Author> query = em.createQuery("Select a from Author a where a.id = :id", Author.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Author insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }
}
