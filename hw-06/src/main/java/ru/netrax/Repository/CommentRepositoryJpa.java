package ru.netrax.Repository;

import ru.netrax.Model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    List<Comment> getAllComment();

    Optional<Comment> getById(long id);

    Comment insert(Comment comment);

    void update(Comment comment);

    void delete(long id);
}
