package com.github.SemenovAlex888.VotingRestaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.github.SemenovAlex888.VotingRestaurant.model.Vote;
import com.github.SemenovAlex888.VotingRestaurant.repository.DataJpaVoteRepository;

import java.time.LocalDate;

import static com.github.SemenovAlex888.VotingRestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final DataJpaVoteRepository repository;

    public VoteService(DataJpaVoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    public void update(Vote vote, int id) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.id());
    }

    public Vote getVoteCurrentDate(int userId, LocalDate date) {
        return repository.getVoteCurrentDate(userId, date);
    }
}
