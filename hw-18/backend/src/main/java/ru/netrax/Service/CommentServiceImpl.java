package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;
import ru.netrax.Utils.HibernateSessionFactory;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepositoryJpa;
    private final BookRepository bookRepositoryJpa;

    @Override
    public List<Comment> getAllComments() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public List<Comment> getCommentsByBookId(long bookId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Book load = session.load(Book.class, bookId);
        List<Comment> commentList = load.getCommentList();
        session.getTransaction().commit();
        return commentList;
    }

    @Override
    public void insertComment(String comment, long bookId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        Comment comment1 = new Comment();
        comment1.setBook(book);
        comment1.setComment(comment);
        commentRepositoryJpa.save(comment1);
    }

    @Override
    public Comment getComment(long id) {
        Optional<Comment> optionalComment = commentRepositoryJpa.findById(id);
        return optionalComment.orElseThrow();
    }

    @Override
    public void updateComment(long id, String comment) {
        Comment commentRepositoryJpaById = commentRepositoryJpa.findById(id).orElseThrow();
        commentRepositoryJpaById.setComment(comment);
        commentRepositoryJpa.save(commentRepositoryJpaById);
    }

    @Override
    public void deleteComment(long id) {
        Comment comment = commentRepositoryJpa.findById(id).orElseThrow();
        commentRepositoryJpa.delete(comment);
    }
}
