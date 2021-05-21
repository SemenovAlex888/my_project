package com.github.SemenovAlex888.VotingRestaurant.service;

import com.github.SemenovAlex888.VotingRestaurant.model.Dish;
import com.github.SemenovAlex888.VotingRestaurant.repository.DataJpaDishRepository;
import com.github.SemenovAlex888.VotingRestaurant.repository.DataJpaRestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DataJpaDishRepository dishRepository;
    private final DataJpaRestaurantRepository restaurantRepository;

    public DishService(DataJpaDishRepository dishRepository, DataJpaRestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Dish> getAll(int restaurId) {
        return dishRepository.getAll(restaurId);
    }

    public Dish get(int dishId, int restaurantId) {
        return checkNotFoundWithId(dishRepository.getByRestaurantId(dishId, restaurantId), dishId);
    }

    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            dish = null;
        } else {
            dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        }

        return dishRepository.save(dish);
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public Dish update(Dish dish, int restaurantId) {
        return checkNotFoundWithId(save(dish, restaurantId), dish.id());
    }
}