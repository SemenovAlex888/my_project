package com.github.SemenovAlex888.VotingRestaurant.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;
import com.github.SemenovAlex888.VotingRestaurant.to.SumVotes;

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
    @GetMapping("/sum")     // Todo test failed in SOAP
    public List<SumVotes> getSumVotesCurrentDay(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.getSumVotesCurrentDay(date);
    }
}
