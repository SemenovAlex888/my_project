package ru.my_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.my_project.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DataJpaVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    Optional<Vote> findById(Integer id);

    // Java Persistence Query Language (JPQL): https://ru.wikipedia.org/wiki/Java_Persistence_Query_Language
    // join fetch: https://dou.ua/lenta/articles/jpa-fetch-types/
    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=?1 AND v.date = ?2")
    Vote getVote(int userId, LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 AND v.date = ?2")
    Vote getVoteCurrentDate(int userId, LocalDate date);
}
