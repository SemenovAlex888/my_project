package ru.my_project.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.my_project.model.Dish;
import ru.my_project.repository.DataJpaDishRepository;

import java.net.URI;
import java.util.List;

import static ru.my_project.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String REST_URL = "/admin/restaurant/dishes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataJpaDishRepository repository;

    @GetMapping
    public List<Dish> getAll(int restaurId) {
        log.info("getAll");
        return repository.getAll(restaurId);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        Assert.notNull(dish, "dish must not be null");
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update {} with id={}", dish, id);
        assureIdConsistent(dish, id);
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.id());
    }
}
