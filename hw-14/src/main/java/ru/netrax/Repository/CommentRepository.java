package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(Long bookId);
}
