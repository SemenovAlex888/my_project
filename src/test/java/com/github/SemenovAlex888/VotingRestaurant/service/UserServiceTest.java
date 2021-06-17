package com.github.SemenovAlex888.VotingRestaurant.service;

import com.github.SemenovAlex888.VotingRestaurant.UserTestData;
import com.github.SemenovAlex888.VotingRestaurant.model.Role;
import com.github.SemenovAlex888.VotingRestaurant.model.User;
import com.github.SemenovAlex888.VotingRestaurant.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.UserTestData.*;
import static org.junit.Assert.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void getAll() {
        List<User> allUsers = service.getAll();
        USER_MATCHER.assertMatch(allUsers, user1, admin, user2, user3, user4);
    }

    @Test
    public void get() {
        User admin = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void create() {
        User created = service.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated, USER_ID);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    public void getByMail() {
        User user = service.getByMail("user1@yandex.ru");
        USER_MATCHER.assertMatch(user, user1);
    }
}