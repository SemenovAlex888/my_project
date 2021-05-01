package ru.my_project.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.my_project.model.Restaurant;
import ru.my_project.repository.DataJpaRestaurantRepository;

import java.util.List;

import static ru.my_project.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final DataJpaRestaurantRepository repository;

    public RestaurantService(DataJpaRestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAllWithDishes() {
        return repository.findAllWithDishes();
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
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
}
