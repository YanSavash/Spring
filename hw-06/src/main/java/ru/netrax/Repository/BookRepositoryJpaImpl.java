package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Book> getAllBook() {
        return em.createQuery("Select b from Book b", Book.class).getResultList();
    }

    @Transactional
    @Override
    public Optional<Book> getById(long id) {
        TypedQuery<Book> query = em.createQuery("Select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Book insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional
    @Override
    public void update(long id, String title) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
