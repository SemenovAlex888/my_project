package com.github.SemenovAlex888.VotingRestaurant.web.vote;

import com.github.SemenovAlex888.VotingRestaurant.model.Vote;
import com.github.SemenovAlex888.VotingRestaurant.service.RestaurantService;
import com.github.SemenovAlex888.VotingRestaurant.service.UserService;
import com.github.SemenovAlex888.VotingRestaurant.service.VoteService;
import com.github.SemenovAlex888.VotingRestaurant.util.exception.BanUpdatesException;
import com.github.SemenovAlex888.VotingRestaurant.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.LIMIT_TIME_FOR_VOTING;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/restaurants/{restaurantId}/votes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public void createOrUpdate(@PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();

        if (LocalTime.now().isBefore(LIMIT_TIME_FOR_VOTING)) {
            log.info("create or update (if before 11:00) vote for userID {} and restaurantId {}", userId, restaurantId);
            Vote voteNew = new Vote(userService.get(userId),    // Todo: change access on Security context?
                    LocalDate.now(),
                    restaurantService.getOne(restaurantId));    // Difference between getOne and findById in Spring Data JPA: https://www.javacodemonk.com/difference-between-getone-and-findbyid-in-spring-data-jpa-3a96c3ff
            voteService.create(voteNew);    // Spring data: CrudRepository's save method and update: https://stackoverflow.com/questions/38893831/spring-data-crudrepositorys-save-method-and-update
        } else {
            Vote voteCurrentDate = voteService.getVoteCurrentDate(userId, LocalDate.now());

            if (voteCurrentDate != null) {
                throw new BanUpdatesException("It is forbidden to vote again after 11:00");
            } else {
                log.info("create vote for userID {} and restaurantId {}", userId, restaurantId);
                Vote voteNew = new Vote(userService.get(userId),    // Todo: change access on Security context?
                        LocalDate.now(),
                        restaurantService.getOne(restaurantId));
                voteService.create(voteNew);
            }
        }
    }
}
