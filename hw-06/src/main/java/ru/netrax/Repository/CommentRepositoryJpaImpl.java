package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Comment> getAllComment() {
        return em.createQuery("Select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<Comment> book = Optional.ofNullable(em.find(Comment.class, id));
        book.ifPresent(e -> em.remove(e));
    }
}
