package com.github.SemenovAlex888.VotingRestaurant;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {
    private final String[] fieldsToIgnore;

    private TestMatcher(String[] fieldsToIgnore) {
        this.fieldsToIgnore = fieldsToIgnore;
    }

    public static <T> TestMatcher<T> usingIgnoringFieldsComparator(String... fieldsToIgnore) {
        return new TestMatcher<>(fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields(fieldsToIgnore).isEqualTo(expected);
    }
}
