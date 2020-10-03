package engine.service;

import engine.entity.User;
import engine.repository.UserJpaRepository;
import engine.validation.QuizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserJpaRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserJpaRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " does not exist");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public void save(User user) {
        User temp = findByEmail(user.getEmail());
        if (temp != null) {
            throw new QuizException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        Pageable pageable = PageRequest.of(0, 10);
        return userRepository.findByEmail(email);
    }

}
