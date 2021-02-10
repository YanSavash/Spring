package ru.netrax.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.netrax.Model.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
