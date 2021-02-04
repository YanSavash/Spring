package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netrax.Model.User;
import ru.netrax.Repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User userByUsername = userRepository.findUserByUsername(s);
            return userByUsername;
        } catch (Exception e) {
            System.out.println("User not found");
            return null;
        }
    }
}
