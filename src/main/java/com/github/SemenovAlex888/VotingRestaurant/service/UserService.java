package com.github.SemenovAlex888.VotingRestaurant.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.github.SemenovAlex888.VotingRestaurant.AuthorizedUser;
import com.github.SemenovAlex888.VotingRestaurant.model.User;
import com.github.SemenovAlex888.VotingRestaurant.repository.DataJpaUserRepository;

import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.util.UserUtil.prepareToSave;
import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNotFound;
import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final DataJpaUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(DataJpaUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, int id) {
        Assert.notNull(user, "user must not be null");
        // checkNotFoundWithId(repository.save(user), user.id()); --> checkNotFoundWithId : check works only for JDBC, disabled
        prepareAndSave(user);
    }

    public User getByMail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }
}
