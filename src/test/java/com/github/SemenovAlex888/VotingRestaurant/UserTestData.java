package com.github.SemenovAlex888.VotingRestaurant;

import com.github.SemenovAlex888.VotingRestaurant.model.Role;
import com.github.SemenovAlex888.VotingRestaurant.model.User;

import static com.github.SemenovAlex888.VotingRestaurant.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator("registered", "roles");

    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "{noop}admin", Role.ADMIN);

}
