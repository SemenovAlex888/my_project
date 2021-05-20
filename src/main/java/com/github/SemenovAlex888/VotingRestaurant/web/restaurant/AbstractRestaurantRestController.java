package com.github.SemenovAlex888.VotingRestaurant.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;
import com.github.SemenovAlex888.VotingRestaurant.service.RestaurantService;
import com.github.SemenovAlex888.VotingRestaurant.to.SumVotes;

import java.time.LocalDate;
import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.assureIdConsistent;
import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public List<Restaurant> getAllWithDishes() {
        log.info("getAll");
        return service.getAllWithDishes();
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant, id);
    }

    public List<SumVotes> getSumVotesCurrentDay(LocalDate date) {
        log.info("getSumVotesCurrentDay for date={}", date);
        return service.getSumVotesCurrentDay(date);
    }
}
