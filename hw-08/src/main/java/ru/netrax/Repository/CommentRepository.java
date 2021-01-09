package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.netrax.Model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
