package com.github.SemenovAlex888.VotingRestaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.github.SemenovAlex888.VotingRestaurant.model.Restaurant;
import com.github.SemenovAlex888.VotingRestaurant.to.SumVotes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DataJpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> findAllWithDishes();

    @Override
    Optional<Restaurant> findById(Integer id);

    // JPQL allows you to define a constructor call in the SELECT clause: https://thorben-janssen.com/jpql/#Grouping_8211_The_GROUP_BY_and_HAVING_clause
    // How to join unrelated entities with JPA and Hibernate: https://thorben-janssen.com/how-to-join-unrelated-entities/
    @Query("SELECT new com.github.SemenovAlex888.VotingRestaurant.to.SumVotes(r.id, r.name, count(v.id)) FROM Vote v LEFT JOIN Restaurant r ON r.id = v.restaurant.id WHERE v.date = :currentDate GROUP BY r.id ORDER BY count(v.id) DESC")
    List<SumVotes> getSumVotesCurrentDay(@Param("currentDate") LocalDate date);
    //version 2: @Query("SELECT new com.github.SemenovAlex888.VotingRestaurant.to.SumVotes(restaur.id, restaur.name, count (v)) FROM Restaurant restaur " +
    //        "LEFT JOIN Vote v ON restaur.id = v.restaurant.id WHERE v.date = ?1 GROUP BY restaur ORDER BY count (v) DESC")
}
