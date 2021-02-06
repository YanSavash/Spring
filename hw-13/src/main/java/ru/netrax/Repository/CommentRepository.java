package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
