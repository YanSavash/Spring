package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentMongoRepository;
    private final BookRepository bookMongoRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentMongoRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByBookId(String bookId) {
        Book book = bookMongoRepository.findById(bookId).orElseThrow();
        return book.getCommentList();
    }

    @Override
    public void insertComment(String comment) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        commentMongoRepository.save(comment1);
    }

    @Override
    public Comment getComment(String id) {
        Optional<Comment> optionalComment = commentMongoRepository.findById(id);
        return optionalComment.orElseThrow();
    }

    @Override
    public void updateComment(String id, String comment) {
        Comment commentRepositoryJpaById = commentMongoRepository.findById(id).orElseThrow();
        commentRepositoryJpaById.setComment(comment);
        commentMongoRepository.save(commentRepositoryJpaById);
    }

    @Override
    public void deleteComment(String id) {
        Comment comment = commentMongoRepository.findById(id).orElseThrow();
        commentMongoRepository.delete(comment);
    }
}
