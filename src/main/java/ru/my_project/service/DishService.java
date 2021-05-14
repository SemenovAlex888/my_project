package ru.my_project.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.my_project.model.Dish;
import ru.my_project.repository.DataJpaDishRepository;
import ru.my_project.repository.DataJpaRestaurantRepository;

import java.util.List;

import static ru.my_project.util.ValidationUtil.checkNotFoundWithId;

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
        Dish dish = dishRepository.findById(dishId).orElse(null);
        return checkNotFoundWithId(dish != null && dish.getRestaurant().getId() == restaurantId? dish : null, dishId);
    }

    public Dish save(Dish dish, int restaurantId) {
        if(!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            dish = null;
        } else {
            dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        }

        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public Dish update(Dish dish, int restaurantId) {
        return checkNotFoundWithId(save(dish, restaurantId), dish.id());
    }
}