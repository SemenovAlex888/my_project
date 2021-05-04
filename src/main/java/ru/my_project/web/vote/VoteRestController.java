package ru.my_project.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.my_project.model.Vote;
import ru.my_project.service.RestaurantService;
import ru.my_project.service.UserService;
import ru.my_project.service.VoteService;
import ru.my_project.util.exception.NotFoundException;
import ru.my_project.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.my_project.util.ValidationUtil.LIMIT_TIME_FOR_VOTING;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/votes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/{restaurantId}")
    public void createOrUpdate(@PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote voteCurrentDate = voteService.getVoteCurrentDate(userId, LocalDate.now());

        if (voteCurrentDate == null) {
            log.info("create vote for userID {} and restaurantId {}", userId, restaurantId);
            Vote voteNew = new Vote(userService.get(userId),
                    LocalDate.now(),
                    restaurantService.get(restaurantId));
            voteService.create(voteNew);
        } else if (LocalTime.now().isAfter(LIMIT_TIME_FOR_VOTING)) {
            throw new NotFoundException("It is forbidden to vote again after 11:00");
        } else {
            log.info("update vote for userID {} restaurantId {}", userId, restaurantId);
            voteCurrentDate.setDate(LocalDate.now());
            voteCurrentDate.setRestaurant(restaurantService.get(restaurantId));
            voteService.create(voteCurrentDate);
        }
    }
}
