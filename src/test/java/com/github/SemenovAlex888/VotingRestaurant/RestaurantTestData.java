package com.github.SemenovAlex888.VotingRestaurant;

import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;

import java.time.LocalDate;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAUR_MATCHER = TestMatcher.usingIgnoringFieldsComparator("dishes");

    public static final int RESTAUR1_ID = 100005;
    public static final int NOT_FOUND_RESTAUR = 20;

    public static final LocalDate DATE = LocalDate.of(2021, 4, 9);

    public static final Restaurant restaur1 = new Restaurant(RESTAUR1_ID, "Mansarda", null, "Pochtamtskaya st., 3");
    public static final Restaurant restaur2 = new Restaurant(RESTAUR1_ID + 1, "Milano", null, "Moskovsky prospect, 97A");
    public static final Restaurant restaur3 = new Restaurant(RESTAUR1_ID + 2, "Italy", null, "Bolshaya Morskaya st., 14");
    public static final Restaurant restaur4 = new Restaurant(RESTAUR1_ID + 3, "Gastronomica", null, "st. Marata, 5/21");
    public static final Restaurant restaur5 = new Restaurant(RESTAUR1_ID + 4, "Moscow", null, "Nevsky prospect, 114");

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "NewRestaurant", null, "newAddress");
    }

    public static Restaurant getUpdatedRestaurant() {
        Restaurant updated = new Restaurant(restaur1);
        updated.setName("UpdatedName");
        updated.setAddress("UpdatedAddress");
        return updated;
    }

}
