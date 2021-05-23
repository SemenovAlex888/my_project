package com.github.SemenovAlex888.VotingRestaurant.service;

import com.github.SemenovAlex888.VotingRestaurant.UserTestData;
import com.github.SemenovAlex888.VotingRestaurant.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.SemenovAlex888.VotingRestaurant.UserTestData.ADMIN_ID;
import static com.github.SemenovAlex888.VotingRestaurant.UserTestData.USER_MATCHER;

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
    }

    @Test
    public void get() {
        User admin = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
    }

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getByMail() {
    }

    @Test
    public void loadUserByUsername() {
    }
}