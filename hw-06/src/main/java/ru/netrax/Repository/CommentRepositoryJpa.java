package ru.netrax.Repository;

import ru.netrax.Model.Comment;

import java.util.List;

public interface CommentRepositoryJpa {
    List<Comment> getAllComment();

    Comment getById(long id);

    Comment save(Comment comment);

    void delete(long id);
}
