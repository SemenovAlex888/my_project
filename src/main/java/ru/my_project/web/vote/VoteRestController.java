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
import ru.my_project.repository.DataJpaRestaurantRepository;
import ru.my_project.repository.DataJpaUserRepository;
import ru.my_project.repository.DataJpaVoteRepository;
import ru.my_project.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.my_project.util.ValidationUtil.LIMIT_TIME_FOR_VOTING;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/votes";
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final int ID = 100000;  // Todo: change on Spring security

    @Autowired
    private DataJpaVoteRepository voteRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Autowired
    private DataJpaRestaurantRepository restaurantRepository;

    @PostMapping("/{restaurantId}")
    public void createOrUpdate(@PathVariable int restaurantId) {
        int userId = ID;
        Vote voteCurrentDate = voteRepository.getVoteCurrentDate(userId, LocalDate.now());

        if (voteCurrentDate == null) {
            log.info("create vote for userID {} and restaurantId {}", ID, restaurantId);
            Vote voteNew = new Vote(userRepository.findById(ID).orElse(null),   // todo check correctness orElse(null)
                    LocalDate.now(),
                    restaurantRepository.getOne(restaurantId));
            voteRepository.save(voteNew);
        } else if (LocalTime.now().isAfter(LIMIT_TIME_FOR_VOTING)) {
            throw new NotFoundException("It is forbidden to vote again after 11:00");
        } else {
            log.info("update vote for userID {} restaurantId {}", ID, restaurantId);
            voteCurrentDate.setDate(LocalDate.now());
            voteCurrentDate.setRestaurant(restaurantRepository.getOne(restaurantId));
            voteRepository.save(voteCurrentDate);
        }
    }
}
