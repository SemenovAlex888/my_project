package ru.my_project.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.my_project.model.Vote;
import ru.my_project.repository.DataJpaVoteRepository;

import java.time.LocalDate;

import static ru.my_project.util.ValidationUtil.checkNotFoundWithId;

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
