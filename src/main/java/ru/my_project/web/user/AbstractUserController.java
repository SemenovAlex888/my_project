package ru.my_project.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.my_project.model.User;
import ru.my_project.repository.DataJpaUserRepository;

import java.util.List;

import static ru.my_project.util.ValidationUtil.*;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaUserRepository repository;

    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}
