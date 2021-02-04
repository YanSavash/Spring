package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
