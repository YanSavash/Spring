package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentMongoRepository;
    private final BookService bookMongoService;

    @Override
    public List<Comment> getAllComments() {
        return commentMongoRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByBookId(String bookId) {
        Book book = bookMongoService.getBook(bookId);
        return book.getCommentList();
    }

    @Override
    public void insertComment(String commentString, String bookId) {
        Comment comment = new Comment();
        comment.setComment(commentString);
        commentMongoRepository.save(comment);
        bookMongoService.addComment(bookId, comment);
    }

    @Override
    public Comment getComment(String id) {
        Optional<Comment> optionalComment = commentMongoRepository.findById(id);
        return optionalComment.orElseThrow();
    }

    @Override
    public void updateComment(String id, String comment) {
        Comment commentMongoRepositoryById = commentMongoRepository.findById(id).orElseThrow();
        commentMongoRepositoryById.setComment(comment);
        commentMongoRepository.save(commentMongoRepositoryById);
    }

    @Override
    public void deleteComment(String id, String bookId) {
        Comment comment = commentMongoRepository.findById(id).orElseThrow();
        bookMongoService.deleteComment(id, bookId);
        commentMongoRepository.delete(comment);
    }
}
