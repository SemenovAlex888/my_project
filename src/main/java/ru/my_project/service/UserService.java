package ru.my_project.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.my_project.model.User;
import ru.my_project.repository.DataJpaUserRepository;

import java.util.List;

import static ru.my_project.util.ValidationUtil.checkNotFound;
import static ru.my_project.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final DataJpaUserRepository repository;

    public UserService(DataJpaUserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, int id) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }

    public User getByMail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}
