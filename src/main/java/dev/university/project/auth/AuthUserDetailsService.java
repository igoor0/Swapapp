package dev.university.project.auth;

import dev.university.project.model.User;
import dev.university.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username.toLowerCase());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found" +  username);
        } else {
            return org.springframework.security.core.userdetails.User.builder()
            .username(user.get().getUsername())
            .password(user.get().getPassword())
            .roles(user.get().getRoles())
            .disabled(!user.get().isActive())
            .build();
        }
    }
}
