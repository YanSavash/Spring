package ru.netrax.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;

import javax.persistence.*;
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

    @Transactional
    @Override
    public Optional<Comment> getById(long id) {
        TypedQuery<Comment> query = em.createQuery("Select c from Comment c where c.id = :id", Comment.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Transactional
    @Override
    public void update(Comment comment) {
        Query query = em.createQuery("update Comment c " +
                "set c.comment = :comment " +
                "where c.id = :id");
        query.setParameter("comment", comment.getComment());
        query.setParameter("id", comment.getId());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
