package ru.my_project.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.my_project.model.Restaurant;
import ru.my_project.to.SumVotes;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantRestController {

    static final String REST_URL = "/user/restaurants";

    @Override
    @GetMapping
    public List<Restaurant> getAllWithDishes() {
        return super.getAllWithDishes();
    }

    @Override
    @GetMapping("/date")
    public List<SumVotes> getSumVotesCurrentDay(@RequestParam(value = "date") LocalDate date) {
        return super.getSumVotesCurrentDay(date);
    }
}
