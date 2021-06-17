package com.github.SemenovAlex888.VotingRestaurant;

import com.github.SemenovAlex888.VotingRestaurant.model.Role;
import com.github.SemenovAlex888.VotingRestaurant.model.User;

import java.util.Collections;
import java.util.Date;

import static com.github.SemenovAlex888.VotingRestaurant.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator("registered", "roles", "password");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final User user1 = new User(USER_ID, "User1", "user1@yandex.ru", "{noop}password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "{noop}admin", Role.ADMIN);
    public static final User user2 = new User(USER_ID + 2, "User2", "user2@yandex.ru", "{noop}password", Role.USER);
    public static final User user3 = new User(USER_ID + 3, "User3", "user3@yandex.ru", "{noop}password", Role.USER);
    public static final User user4 = new User(USER_ID + 4, "User4", "user4@yandex.ru", "{noop}password", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
