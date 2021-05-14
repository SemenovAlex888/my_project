package ru.my_project.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.my_project.model.Dish;
import ru.my_project.service.DishService;

import java.net.URI;
import java.util.List;

import static ru.my_project.util.ValidationUtil.assureIdConsistent;
import static ru.my_project.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String REST_URL = "/admin/restaurant/{restaurantId}/dishes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("getAll");
        return service.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int dishId, @PathVariable("restaurantId") int restaurantId) {
        log.info("get dish with dishId = {}, restaurant_id = {} ", dishId, restaurantId);
        return service.get(dishId, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        log.info("create {}", dish);
        checkNew(dish);

        Dish created = service.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable("id") int dishId, @PathVariable("restaurantId") int restaurantId) {
        log.info("update {} with id = {}, restaurant_id = {}", dish, dishId, restaurantId);
        assureIdConsistent(dish, dishId);
        service.update(dish, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int dishId) {
        log.info("delete dish with id = {}", dishId);
        service.delete(dishId);
    }
}
