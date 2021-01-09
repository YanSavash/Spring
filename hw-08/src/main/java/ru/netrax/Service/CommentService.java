package ru.netrax.Service;

import ru.netrax.Model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    List<Comment> getCommentsByBookId(String bookId);

    void insertComment(String comment);

    Comment getComment(String id);

    void updateComment(String id, String comment);

    void deleteComment(String id);
}
