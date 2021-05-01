package ru.my_project.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.my_project.model.Dish;
import ru.my_project.repository.DataJpaDishRepository;

import java.util.List;

import static ru.my_project.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DataJpaDishRepository repository;

    public DishService(DataJpaDishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> getAll(int restaurId) {
        return repository.getAll(restaurId);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Dish dish, int id) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.id());
    }
}