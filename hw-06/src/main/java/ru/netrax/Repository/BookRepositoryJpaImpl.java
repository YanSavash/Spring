package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<Book> book = Optional.ofNullable(em.find(Book.class, id));
        book.ifPresent(e -> em.remove(e));
    }
}
