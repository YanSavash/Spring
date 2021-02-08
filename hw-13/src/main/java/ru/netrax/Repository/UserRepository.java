package ru.netrax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netrax.Model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);
}
