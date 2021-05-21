package com.github.SemenovAlex888.VotingRestaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;
import com.github.SemenovAlex888.VotingRestaurant.repository.DataJpaRestaurantRepository;
import com.github.SemenovAlex888.VotingRestaurant.to.SumVotes;

import java.time.LocalDate;
import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final DataJpaRestaurantRepository repository;

    public RestaurantService(DataJpaRestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAllWithDishesForDate(LocalDate date) {
        return repository.getAllWithDishesForDate(date);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public Restaurant getOne(int restaurantId) {
        return checkNotFoundWithId(repository.getOne(restaurantId), restaurantId);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "user must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public List<SumVotes> getSumVotesCurrentDay(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getSumVotesCurrentDay(date);
    }
}
