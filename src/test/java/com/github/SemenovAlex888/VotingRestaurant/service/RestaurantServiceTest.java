package com.github.SemenovAlex888.VotingRestaurant.service;

import com.github.SemenovAlex888.VotingRestaurant.RestaurantTestData;
import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;
import com.github.SemenovAlex888.VotingRestaurant.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.RestaurantTestData.*;
import static org.junit.Assert.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAllWithDishesForDate() {
        List<Restaurant> allRestaurants = service.getAllWithDishesForDate(DATE);
        RESTAUR_MATCHER.assertMatch(allRestaurants, restaur1, restaur2, restaur3, restaur4, restaur5);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAUR1_ID);
        RESTAUR_MATCHER.assertMatch(restaurant, RestaurantTestData.restaur1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_RESTAUR));
    }

    @Test
    public void getOne() {
        Restaurant restaurant = service.getOne(RESTAUR1_ID);
        RESTAUR_MATCHER.assertMatch(restaurant, RestaurantTestData.restaur1);
    }

    @Test
    public void create() {
        Restaurant created = service.create(getNewRestaurant());
        Integer newId = created.getId();
        Restaurant newRestaurant = getNewRestaurant();
        newRestaurant.setId(newId);
        RESTAUR_MATCHER.assertMatch(created, newRestaurant);
        RESTAUR_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void delete() {
        service.delete(RESTAUR1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAUR1_ID));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdatedRestaurant();
        service.update(updated, RESTAUR1_ID);
        RESTAUR_MATCHER.assertMatch(service.get(RESTAUR1_ID), getUpdatedRestaurant());
    }

    @Test
    public void getSumVotesCurrentDay() {
    }
}