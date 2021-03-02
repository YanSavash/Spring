package ru.netrax.Service;

import ru.netrax.Model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    List<Comment> getCommentsByBookId(long bookId);

    void insertComment(String comment, long bookId);

    Comment getComment(long id);

    void updateComment(long id, String comment);

    void deleteComment(long id);
}
