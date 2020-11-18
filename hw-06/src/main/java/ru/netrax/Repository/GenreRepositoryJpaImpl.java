package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Genre> getAllGenre() {
        return em.createQuery("Select g from Genre g", Genre.class).getResultList();
    }

    @Transactional
    @Override
    public Optional<Genre> getById(long id) {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g where g.id = :id", Genre.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }
}
